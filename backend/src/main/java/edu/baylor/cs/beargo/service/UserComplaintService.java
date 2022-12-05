package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.dto.UserComplaintDto;
import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.model.UserComplaint;
import edu.baylor.cs.beargo.repository.UserComplaintRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserComplaintService {

    @Autowired
    UserComplaintRepository userComplaintRepository;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    // TODO: check it and set rest of the variable.

    /**
     * @param reportBy the user who is reporting
     * @param reportTo the user id who is reported
     * @param reason   the original report for the user
     * @return userComplaint object
     */
    public UserComplaint createUserComplaint(User reportBy, Long reportTo, String reason) {
        User user = userService.getUserById(reportTo);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User does not exist");
        }
        UserComplaint userComplaint = new UserComplaint();
        userComplaint.setComplainedByUserId(reportBy.getId());
        userComplaint.setComplainedByUserName(reportBy.getFullname());
        userComplaint.setComplainDate(LocalDate.now());
        userComplaint.setIsResolved(false);
        userComplaint.setComplainedUser(user);
        userComplaint.setReason(reason);

        /*
            Sending Notification to User
         */
        try {
            Notification notification = new Notification();
            notification.setNotificationMsg(reportBy.getUsername() + " reported " + user.getUsername() + " - " + reason);
            notificationService.saveNotification(reportBy, notification, reportTo);
        } catch (Exception e) {
            log.error("Can not send notification, reason: " + e);
        }

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

    public List<UserComplaintDto> getAllComplaints() {
        List<UserComplaint> complaintLis = userComplaintRepository.findAll();
        List<UserComplaintDto> allComplaints = new ArrayList<>();
        for (UserComplaint u : complaintLis) {
            UserComplaintDto userComplaintDto = new UserComplaintDto();
            userComplaintDto = userComplaintDto.getUserComplaintDto(u);
            if (!u.getIsResolved()) {
                User user = userService.getUserById(u.getComplainedUser().getId());
                userComplaintDto.setComplainedUser(user);
                allComplaints.add(userComplaintDto);
            }
        }
        return allComplaints;
    }

    public UserComplaint resolveComplaint(User user, Long complaintid) {
        Optional<UserComplaint> opt = userComplaintRepository.findById(complaintid);
        UserComplaint userC = new UserComplaint();
        if (opt.isPresent()) {
            UserComplaint userComplaint = opt.get();
            userComplaint.setIsResolved(true);
            userComplaint.setId(complaintid);
            userComplaint.setResolvedBy(user);
            userComplaint.setComplainDate(LocalDate.now());
            return userComplaintRepository.save(userComplaint);
        }
        return null;
    }
}