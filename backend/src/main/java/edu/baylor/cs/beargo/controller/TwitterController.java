package edu.baylor.cs.beargo.controller;


import edu.baylor.cs.beargo.model.TwitterModel;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/twitter")
public class TwitterController {

    @Autowired
    TwitterService twitterService;

    /**
     * @param user the logged user
     * @return a list of twitter
     */
    @GetMapping
    public ResponseEntity<List<TwitterModel>> getNotification(@AuthenticationPrincipal User user) {

        Long uid = user.getId();

        log.info("Retrieving all tweets");
        List<TwitterModel> tweetList = twitterService.getAllTweets();
        return new ResponseEntity<>(tweetList, HttpStatus.OK);
    }

    /**
     * @param user the logged user
     * @param t    the twitter
     * @return the twitter
     */
    @PostMapping
    public ResponseEntity<TwitterModel> setTestTweet(@AuthenticationPrincipal User user, @RequestBody TwitterModel t) {

        Long uid = user.getId();

        log.info("Saving Tweets");
        TwitterModel tweet = twitterService.saveTweet(t);
        return new ResponseEntity<>(tweet, HttpStatus.OK);
    }


}
