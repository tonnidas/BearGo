package edu.baylor.cs.beargo.controller;


import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @GetMapping("/{uid}")
    public ResponseEntity<List<Notification>> getNotification(@PathVariable("uid") Long uid) {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
