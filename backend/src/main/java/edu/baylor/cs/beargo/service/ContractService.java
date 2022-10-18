package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.ContractRepository;
import edu.baylor.cs.beargo.repository.UserRepository;
import edu.baylor.cs.beargo.util.UserContracts;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ContractService {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    UserRepository userRepository;

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
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserContracts(user.getId(), user.getSenderContracts(), user.getTravelerContracts());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user exists for given id");
        }

    }
}