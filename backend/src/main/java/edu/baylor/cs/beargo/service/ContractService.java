package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.DeliveryStatus;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.ContractRepository;
import edu.baylor.cs.beargo.util.UserContracts;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public UserContracts getContractByUserId(Long id) {
        User user = userService.getUserById(id);
        return new UserContracts(user.getId(), user.getSenderContracts(), user.getTravelerContracts());
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
     * Checks if the logged user is either sender or traveler.
     * Checks if the sender has not already rated or reviewed.
     * Checks if the traveler has not already rated or reviewed.
     *
     * @param user       the authenticated user
     * @param rating     the rating
     * @param review     the review
     * @param contractId the contract Id
     * @return created contract
     */
    public Contract reviewAndRate(User user, Long contractId, Integer rating, String review) {
        Contract contract = getContractById(contractId);

        if (contract.getTraveler() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contract is not finalized yet");
        }

        if (! (contract.getContractEndDate().isAfter(LocalDate.now())) ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review and rating can be give only after contract end date");
        }

        if (contract.getSender().getId().equals(user.getId())) {
            if (contract.getRatingBySender() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender cannot rate the traveler twice");
            }
            contract.setRatingBySender(rating);
            if (contract.getReviewBySender() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender cannot give review to the traveler twice");
            }
            contract.setReviewBySender(review);
        } else if (contract.getTraveler().getId().equals(user.getId())) {
            if (contract.getRatingByTraveler() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Traveler cannot rate the sender twice");
            }
            contract.setRatingByTraveler(rating);
            if (contract.getReviewByTraveler() != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Traveler cannot give review to the sender twice");
            }
            contract.setReviewByTraveler(review);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only sender or traveler can rate and review");
        }

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
     * @param user          the authenticated user
     * @param contractId    the contract id
     * @param newStatus     the new status
     * @return updated contract
     */
    public Contract updateContractStatus(User user, Long contractId, DeliveryStatus newStatus) {
        Contract contract = getContractById(contractId);
        User traveler = contract.getTraveler();

        if (! (contract.getContractEndDate().isAfter(LocalDate.now())) ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contract status cannot be updated after contract end date");
        } else {
            contract.setDeliveryStatus(DeliveryStatus.UNSUCCESSFULL);
        }

        if (! (newStatus.equals(DeliveryStatus.PICKED_UP) || newStatus.equals(DeliveryStatus.IN_TRANSIT) || newStatus.equals(DeliveryStatus.DELIVERED)) ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "newStatus have to be picked-up or in-transit or delivered");
        }

        if (traveler.equals(null)) { // ask Dipta, why?
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Without a traveler, contract status cannot be updated");
        }

        Long userId = user.getId();
        Long senderId = contract.getSender().getId();
        Long travelerId = contract.getTraveler().getId();

        if (newStatus.equals(DeliveryStatus.PICKED_UP)) {
            if (! (userId.equals(travelerId) || userId.equals(senderId))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only traveler or sender can update the contract status to picked-up");
            }
            contract.setDeliveryStatus(newStatus);
        }

        if (newStatus.equals(DeliveryStatus.IN_TRANSIT)) {
            if (! (userId.equals(travelerId)) ) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only traveler can update the contract status to in-transit");
            }
            contract.setDeliveryStatus(newStatus);
        }

        if (newStatus.equals(DeliveryStatus.DELIVERED)) {
            if (! (userId.equals(travelerId)) ) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only traveler can update the contract status to in-transit");
            }
            contract.setDeliveryStatus(newStatus);
        }

        return contractRepository.save(contract);
    }
}