package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.model.UserComplaint;
import edu.baylor.cs.beargo.repository.UserComplaintRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserComplaintService {

    @Autowired
    UserComplaintRepository userComplaintRepository;

    // TODO: check it and set rest of the variable.
    /**
     * @param reportBy the user who is reporting
     * @param reportTo the user id who is reported
     * @param reason the original report for the user
     * @return userComplaint object
     */
    public UserComplaint createUserComplaint(User reportBy, Long reportTo, String reason) {
        if (reason == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reason cannot be empty text");
        UserComplaint userComplaint = new UserComplaint();
        userComplaint.setComplainedByUser(reportBy);
        userComplaint.setComplainDate(LocalDate.now());
        userComplaint.setIsResolved(false);
        userComplaint.setComplainedUserId(reportTo);
        userComplaint.setReason(reason);
        return userComplaintRepository.save(userComplaint);
    }
}