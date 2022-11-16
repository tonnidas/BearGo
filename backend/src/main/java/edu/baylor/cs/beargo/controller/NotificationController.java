package edu.baylor.cs.beargo.controller;


import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getNotification(@AuthenticationPrincipal User user) {

        Long uid = user.getId();
        List<Notification> notificationList = notificationService.getNotification(uid);
        return new ResponseEntity<>(notificationList, HttpStatus.OK);
    }
}
