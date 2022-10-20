package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.UserRepository;
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
public class AdminService {

    @Autowired
    UserRepository userRepository;

    /**
     * @return A list of all admins
     */
    public List<User> getAdmins() {
        return userRepository.findByIsAdminTrue();
    }

    /**
     * @return A list of all users
     */
    public List<User> getUsers() {
        return userRepository.findByIsAdminFalse();
    }

    /**
     * @return An admin by id
     */
    public User getAdminById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            if (user.get().getIsAdmin()) {
                return user.get();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Admin record exists for given admin id (However, user record exists)");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Admin record exists for given admin id");
        }
    }

    /**
     * TODO: Promote user to admin
     * @return
     */
}