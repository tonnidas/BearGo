package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.model.UserComplaint;
import edu.baylor.cs.beargo.service.UserComplaintService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/userComplaint")
public class UserComplaintController {
    @Autowired
    UserComplaintService userComplaintService;

    @PostMapping("/reportUser")
    public ResponseEntity<UserComplaint> createComplain(@AuthenticationPrincipal User user,
                                                        @RequestParam(value = "reportTo") Long userId,
                                                        @RequestParam(value = "reason") String reason) {

        return new ResponseEntity<>(userComplaintService.createUserComplaint(user, userId, reason), HttpStatus.OK);
    }
}
