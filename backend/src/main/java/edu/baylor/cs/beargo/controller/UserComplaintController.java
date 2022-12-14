package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.dto.UserComplaintDto;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.model.UserComplaint;
import edu.baylor.cs.beargo.service.UserComplaintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/userComplaint")
public class UserComplaintController {
    @Autowired
    UserComplaintService userComplaintService;

    /**
     * @param user   the logged user
     * @param userId the user id to complaint
     * @param reason the reason of complaint
     * @return the complaint
     */
    @PostMapping("/reportUser")
    public ResponseEntity<UserComplaint> createComplain(@AuthenticationPrincipal User user,
                                                        @RequestParam(value = "reportTo") Long userId,
                                                        @RequestParam(value = "reason") String reason) {

        return new ResponseEntity<>(userComplaintService.createUserComplaint(user, userId, reason), HttpStatus.OK);
    }

    /**
     * @param userId the user id
     * @return a list of complaints by the user id
     */
    @GetMapping("/getAllComplaintByUserId/{userId}")
    public ResponseEntity<List<UserComplaint>> getAllComplaintByUserId(@PathVariable(value = "userId") Long userId) {
        return new ResponseEntity<>(userComplaintService.getAllComplaintByUserId(userId), HttpStatus.OK);
    }

    /**
     * @return a list of user complaints
     */
    @GetMapping("/allUserComplaints")
    public ResponseEntity<List<UserComplaintDto>> getComplains() {
        List<UserComplaintDto> allComplains = userComplaintService.getAllComplaints();
        return new ResponseEntity<>(allComplains, HttpStatus.OK);
    }

    /**
     * @param user the logged user
     * @param id   the complaint id
     * @return the resolved complaint
     */
    @PostMapping("/resolveComplaint/{complaintid}")
    public ResponseEntity<UserComplaint> resolveComplaint(@AuthenticationPrincipal User user,
                                                          @PathVariable("complaintid") Long id) {
        UserComplaint userComplaint = userComplaintService.resolveComplaint(user, id);
        return new ResponseEntity<>(userComplaint, HttpStatus.OK);
    }
}
