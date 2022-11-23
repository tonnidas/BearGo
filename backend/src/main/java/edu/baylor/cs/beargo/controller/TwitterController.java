package edu.baylor.cs.beargo.controller;


import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.service.ProductService;
import edu.baylor.cs.beargo.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/twitter")
public class TwitterController {

    @Autowired
    TwitterService twitterService;

    @GetMapping
    public ResponseEntity<String> writeTweet() {
        twitterService.tweet("Test Tweet");
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
