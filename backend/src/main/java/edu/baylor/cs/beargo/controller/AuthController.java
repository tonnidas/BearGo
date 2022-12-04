package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.EmailService;
import edu.baylor.cs.beargo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    /**
     * Logs a user
     * @param user   the user desiring to login
     * @return a token of login
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String token = userService.login(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    /**
     * Registers a user
     * @param user   the user to be registered
     * @param code   the code
     * @return a registered user
     */
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user, @RequestParam int code) {
        User registeredUser = userService.register(user, code);
        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }

    /**
     * Sends verification code to email
     * @param email the email
     * @return a confirmation of code sending
     * @throws IOException an IOException
     */
    @PostMapping("/sendCode")
    public String sendVerificationCode(@RequestParam String email) throws IOException {
        emailService.sendVerificationEmail(email);
        return "Verification email sent";
    }

    /**
     * Resets the password
     * @param email       the email
     * @param newPassword the password
     * @param code        the verification code
     * @return a user
     */
    @PostMapping("/resetPassword")
    public User resetPassword(@RequestParam String email, @RequestParam String newPassword, @RequestParam int code) {
        return userService.resetPassword(email, newPassword, code);
    }
}
