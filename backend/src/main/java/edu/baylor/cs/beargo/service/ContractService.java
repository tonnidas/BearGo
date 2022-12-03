package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.DeliveryStatus;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.ContractRepository;
import edu.baylor.cs.beargo.dto.UserContractsDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ContractService {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductPostService productPostService;

    /**
     * @return all contracts
     */
    public List<Contract> getContracts() {
        return contractRepository.findAll();
    }

    /**
     * @return all contracts by contract id
     */
    public Contract getContractById(Long id) {
        Optional<Contract> contract = contractRepository.findById(id);

        if (contract.isPresent()) {
            return contract.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No contract record exist for given contract id");
        }
    }

    /**
     * @return all contracts as sender and all contracts as traveler
     */
    public UserContractsDto getContractByUserId(Long id) {
        User user = userService.getUserById(id);
        return new UserContractsDto(user.getId(), user.getSenderContracts(), user.getTravelerContracts());
    }

    /**
     * Checks if the user with the userId exists
     *
     * @param id             the user id
     * @param lookBackMonths 0 means all
     * @return all contracts as sender and all contracts as traveler for a given period (half-yearly, yearly, all)
     */
    public UserContractsDto getContractByUserIdByDate(Long id, int lookBackMonths) {
        if (lookBackMonths == 0) {
            return getContractByUserId(id);
        }

        User user = userService.getUserById(id);

        Set<Contract> senderContracts = new HashSet<>();
        for (Contract contract : user.getSenderContracts()) {
            if (contract.getContractStartDate().isAfter(LocalDate.now().minusMonths(lookBackMonths))) {
                senderContracts.add(contract);
            }
        }

        Set<Contract> travelerContracts = new HashSet<>();
        for (Contract contract : user.getTravelerContracts()) {
            if (contract.getContractStartDate().isAfter(LocalDate.now().minusMonths(lookBackMonths))) {
                travelerContracts.add(contract);
            }
        }

        return new UserContractsDto(user.getId(), senderContracts, travelerContracts);
    }

    /**
     * Checks if the sender is the logged user.
     * Checks if the sender is not the traveler.
     * Adds traveler to the contract and sets the delivery status as INITIATED and sets the time.
     *
     * @param user          the authenticated user
     * @param productPostId the corresponding product post id
     * @param travelerId    the traveler Id
     * @return created contract
     */
    public Contract confirmContract(User user, Long productPostId, Long travelerId) {
        ProductPost productPost = productPostService.getProductPostById(productPostId);
        Contract contract = productPost.getContract();

        if (!user.getId().equals(contract.getSender().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only sender of the product post can confirm traveler");
        }

        if (contract.getSender().getId().equals(travelerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender and traveler cannot be same");
        }

        User traveler = userService.getUserById(travelerId);

        contract.setTraveler(traveler);
        contract.setDeliveryStatus(DeliveryStatus.INITIATED);
        contract.setContractStartDate(LocalDate.now());
        contract.setDescription("Contract initiated");

        return contractRepository.save(contract);
    }

    /**
     * Checks if the contract exists with the contract id.
     * Checks if a traveler exists for the contract to be updated.
     * Checks if contract end date has not passed current date.
     * For status to be PICKED_UP,
     * Checks if the user is either the sender or the traveler.
     * For status to be IN_TRANSIT,
     * Checks if the user is the traveler.
     * For status to be DELIVERED,
     * Checks if the user is the sender.
     *
     * @param user       the authenticated user
     * @param contractId the contract id
     * @param newStatus  the new status
     * @return updated contract
     */
    public Contract updateContractStatus(User user, Long contractId, String newStatus) {
        Contract contract = getContractById(contractId);
        User traveler = contract.getTraveler();

        if (LocalDate.now().isAfter(contract.getContractEndDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contract status cannot be updated after contract end date");
        }

        if (!(newStatus.equals("PICKED_UP") || newStatus.equals("IN_TRANSIT") || newStatus.equals("DELIVERED"))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "newStatus have to be picked-up or in-transit or delivered");
        }

        if (traveler == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Without a traveler, contract status cannot be updated");
        }

        Long userId = user.getId();
        Long senderId = contract.getSender().getId();
        Long travelerId = contract.getTraveler().getId();

        if (newStatus.equals("PICKED_UP")) {
            if (!(userId.equals(travelerId) || userId.equals(senderId))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only traveler or sender can update the contract status to picked-up");
            }
            contract.setDeliveryStatus(DeliveryStatus.PICKED_UP);
        }

        if (newStatus.equals("IN_TRANSIT")) {
            if (!(userId.equals(travelerId))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only traveler can update the contract status to in-transit");
            }
            contract.setDeliveryStatus(DeliveryStatus.IN_TRANSIT);
        }

        if (newStatus.equals("DELIVERED")) {
            if (!(userId.equals(travelerId) || userId.equals(senderId))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only traveler or sender can update the contract status to picked-up");
            }
            contract.setDeliveryStatus(DeliveryStatus.DELIVERED);
        }

        return contractRepository.save(contract);
    }

    /**
     * Checks if the current user already completed review for this contract
     *
     * @param user       the authenticated user
     * @param contractId the contract id
     * @return the review done status
     */
    public Boolean getReviewCompletion(User user, Long contractId) {
        Contract contract = getContractById(contractId);
        if (contract == null) {
            return false;
        }
        if (contract.getSender().getId().equals(user.getId())) {
            if (contract.getReviewAndRatingBySender() == null) {
                return false;
            }
            return true;
        } else {
            if (contract.getReviewAndRatingByTraveler() == null) {
                return false;
            }
            return false;
        }
    }

    public Contract addCost(User user, Long contractId, Double cost) {
        Contract contract = getContractById(contractId);
        ProductPost p = contract.getProductPost();
        contract.setCost(cost);
        p.setContract(contract);
        System.out.println("cost " + p.getContract().getCost());
        return contractRepository.save(contract);
    }
}