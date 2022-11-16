package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/Message")
public class MessageController {
    @Autowired
    MessageService msgService;
}
