package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.Message;

import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/Message")
public class MessageController {
    @Autowired
    MessageService msgService;

    @GetMapping("/{uid}")
    public ResponseEntity<List<Message>> getMsgList(@AuthenticationPrincipal User user) {

        Long uid = user.getId();
        log.info("{}" + user);
        List<Message> msgList = msgService.getAllmsg(uid);
        return new ResponseEntity<>(msgList, HttpStatus.OK);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<Message> saveMsg(@AuthenticationPrincipal User user, Message m) {

        log.info("{}" + user);
        Message msg = msgService.saveMsg(m);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }


}
