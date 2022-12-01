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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        String token = userService.login(user);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user, @RequestParam int code) {
        User registeredUser = userService.register(user, code);
        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }

    @PostMapping("/register/sendCode")
    public String sendVerificationCode(@RequestParam String email) throws IOException {
        emailService.sendVerificationEmail(email);
        return "Verification email sent";
    }
}
