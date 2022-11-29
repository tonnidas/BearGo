package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.dto.ContractFrequencyDto;
import edu.baylor.cs.beargo.dto.RatingDto;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // Get the user that is currently logged in to the system
    @GetMapping("/findByFullname")
    public ResponseEntity<List<User>> findByFullname(@RequestParam(value = "name") String name) {
        List<User> userList = userService.findByFullname(name);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    // update current user
    @PostMapping("/updateProfile")
    public User updateCurrentUser(@RequestBody User updatedUser, @AuthenticationPrincipal User user) {
        return userService.updateUser(updatedUser, user);
    }

    @GetMapping("/getRatingByUserId")
    public ResponseEntity<RatingDto> getRatingByUserId(@RequestParam(value = "userId") Long userId) {
        return new ResponseEntity<>(userService.getRatingByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/getContractFrequency")
    public ResponseEntity<ContractFrequencyDto> getContractFrequency(@RequestParam(value = "userId") Long userId) {
        return new ResponseEntity<>(userService.getContractFrequency(userId), HttpStatus.OK);
    }
}
