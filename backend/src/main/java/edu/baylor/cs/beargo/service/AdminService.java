package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AdminService {

    @Autowired
    UserRepository userRepository;

    // get all admins
    public List<User> getAdmins() {
        return userRepository.findByIsAdminTrue();
    }

    // get all users
    public List<User> getUsers() {
        return userRepository.findByIsAdminFalse();
    }

//    // get contracts by contract id
//    public Contract getContractById(Long id) {
//        Optional<Contract> contract = contractRepository.findById(id);
//
//        if (contract.isPresent()) {
//            return contract.get();
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No contract record exist for given contract id");
//        }
//    }
}