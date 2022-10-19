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

    // get all contracts
    public List<Contract> getContracts() {
        return contractRepository.findAll();
    }

    // get contracts by contract id
    public Contract getContractById(Long id) {
        Optional<Contract> contract = contractRepository.findById(id);

        if (contract.isPresent()) {
            return contract.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No contract record exist for given contract id");
        }
    }

    // get all contracts by user id
    public UserContracts getContractByUserId(Long id) {
        User user = userService.findUserById(id);
        return new UserContracts(user.getId(), user.getSenderContracts(), user.getTravelerContracts());
    }

    public Contract confirmContract(User user, Long productPostId, Long travelerId) {
        ProductPost productPost = productPostService.findProductPostById(productPostId);
        Contract contract = productPost.getContract();

        if (!user.getId().equals(contract.getSender().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only sender of the product post can confirm traveler");
        }

        if (contract.getSender().getId().equals(travelerId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender and traveler cannot be same");
        }

        User traveler = userService.findUserById(travelerId);

        contract.setTraveler(traveler);
        contract.setDeliveryStatus(DeliveryStatus.INITIATED);
        contract.setContractStartDate(LocalDate.now());

        return contractRepository.save(contract);
    }
}