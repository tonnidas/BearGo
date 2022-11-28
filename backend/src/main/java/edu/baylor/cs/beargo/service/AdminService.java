package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.ProductPostComplaint;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.ProductPostComplaintRepository;
import edu.baylor.cs.beargo.repository.ProductPostRepository;
import edu.baylor.cs.beargo.repository.UserRepository;
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
public class AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductPostComplaintRepository productPostComplaintRepository;

    @Autowired
    ProductPostRepository productPostRepository;

    @Autowired
    ProductPostService productPostService;

    /**
     * @return A list of all admins
     */
    public List<User> getAdmins() {
        return userRepository.findByIsAdminTrue();
    }

    /**
     * @return A list of all users (excluding admins)
     */
    public List<User> getUsers() {
        return userRepository.findByIsAdminFalse();
    }

    /**
     * @return A list of all users (including admins)
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
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
     * TODO: Promote user by id to be an admin
     *
     * @return promoted User
     */
    public User promoteUser(Long id) {
        Optional<User> candidateUser = userRepository.findById(id);

        if (candidateUser.isPresent()) {
            if (candidateUser.get().getIsAdmin()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is already an Admin");
            } else {
                User promotedUser = candidateUser.get();
                promotedUser.setIsAdmin(Boolean.TRUE);
                return userRepository.save(promotedUser);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User record exists for given user id");
        }
    }

    /**
     * Checks if verdict parameter is "blocked"
     *
     * @param user          the authenticated user
     * @param productPostId the corresponding product post id
     * @return created contract
     */
    public Set<ProductPostComplaint> resolvePostComplaint(User user, Long productPostId, String verdict) {
        ProductPost productPost = productPostService.getProductPostById(productPostId);
        Set<ProductPostComplaint> complaints = productPost.getComplaints();

        if (verdict.equals("blocked")) {
            productPost.setBlocked(true);
            productPostRepository.save(productPost);
        }

        Set<User> users = new HashSet<>(); // for notification

        for (ProductPostComplaint c : complaints) {
            c.setIsResolved(Boolean.TRUE);
            c.setResolveDate(LocalDate.now());
            c.setResolvedBy(user);
            users.add(c.getComplainedBy()); // for notification
            productPostComplaintRepository.save(c);
        }

        // TODO: send notification to users - Saad vai

        return complaints;
    }
}