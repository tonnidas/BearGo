package edu.baylor.cs.beargo.controller;


import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getNotification(@AuthenticationPrincipal User user) {

        Long uid = user.getId();

        log.info("Retrieving Notification for user ID:" + user.getId().toString());
        List<Notification> notificationList = notificationService.getNotification(user);
        return new ResponseEntity<>(notificationList, HttpStatus.OK);
    }


    @PostMapping(value = "/{toId}")
    public ResponseEntity<Notification> setNotification(@PathVariable(value = "toId") Long toId,@AuthenticationPrincipal User user, @RequestBody Notification notification) {

        log.info("Saving Notification for User ID: " + user.getId().toString());

        Notification retVal = notificationService.saveNotification(user, notification,toId);
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}
