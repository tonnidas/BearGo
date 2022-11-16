package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    // Get the user that is currently logged in to the system
    @GetMapping("/current")
    public ResponseEntity<UserDetails> getCurrentUser(@AuthenticationPrincipal User user) {
        UserDetails currentUser = userService.loadUserByUsername(user.getUsername());
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    // update current user
    @PostMapping("/updateProfile")
    public User updateCurrentUser(@RequestBody User updatedUser, @AuthenticationPrincipal User user) {
        return userService.updateUser(updatedUser, user);
    }
}
