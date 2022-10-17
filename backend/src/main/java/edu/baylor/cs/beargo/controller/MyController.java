package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    MyService service;

    // TODO: remove this in production
    @PostMapping("/populate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> populate() {
        service.populate();
        return new ResponseEntity<>("Data populated", HttpStatus.OK);
    }
}