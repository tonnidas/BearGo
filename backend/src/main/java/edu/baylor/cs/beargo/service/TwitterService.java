package edu.baylor.cs.beargo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;


import org.springframework.social.twitter.api.Trends;
import org.springframework.social.twitter.api.Twitter;

import org.springframework.stereotype.Service;




@Slf4j
@Service
public class TwitterService extends SocialConfigurerAdapter {
    @Autowired
    private Twitter twitter;

    public void getTweets(){

        log.info("Tweets:",twitter.searchOperations().search("#Amazon"));


    }


}
