package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.model.UserComplaint;
import edu.baylor.cs.beargo.repository.UserComplaintRepository;
import edu.baylor.cs.beargo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserComplaintService {

    @Autowired
    UserComplaintRepository userComplaintRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    // TODO: check it and set rest of the variable.

    /**
     * @param reportBy the user who is reporting
     * @param reportTo the user id who is reported
     * @param reason   the original report for the user
     * @return userComplaint object
     */
    public UserComplaint createUserComplaint(User reportBy, Long reportTo, String reason) {
        if (reason == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reason cannot be empty text");
        Optional<User> user = userRepository.findById(reportTo);
        User u = new User();
        if (user.isPresent())
            u = user.get();
        UserComplaint userComplaint = new UserComplaint();
        userComplaint.setComplainedByUserId(reportBy.getId());
        userComplaint.setComplainedByUserName(reportBy.getFullname());
        userComplaint.setComplainDate(LocalDate.now());
        userComplaint.setIsResolved(false);
        userComplaint.setComplainedUser(u);
        userComplaint.setReason(reason);
        return userComplaintRepository.save(userComplaint);
    }

    /**
     * @param userId the user id for whom the report is needed
     * @return List of userComplaint object for the given user id
     */
    public List<UserComplaint> getAllComplaintByUserId(Long userId) {
        User user = userService.getUserById(userId);
        return userComplaintRepository.findByComplainedUser(user);
    }

    public List<UserComplaint> getAllComplains() {
        List<UserComplaint> complaintLis = userComplaintRepository.findAll();
        List<UserComplaint> allComplaints = new ArrayList<>();
        for (UserComplaint u : complaintLis) {
            if (!u.getIsResolved()) {
                allComplaints.add(u);
                System.out.println(u.getComplainedUser().getId());
                System.out.println(u.getComplainedUser().getUsername());
            }
        }
        return allComplaints;
    }
}