package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.Message;

import edu.baylor.cs.beargo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Message")
public class MessageController {
    @Autowired
    MessageService msgService;

    @GetMapping("/{uid}")
    public ResponseEntity<List<Message>> getNotification(@PathVariable("uid") Long uid) {
        List<Message> msgList = msgService.getAllmsg(uid);
        return new ResponseEntity<>(msgList, HttpStatus.OK);
    }
}
