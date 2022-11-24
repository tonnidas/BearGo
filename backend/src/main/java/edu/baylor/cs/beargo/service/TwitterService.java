package edu.baylor.cs.beargo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class TwitterService extends SocialConfigurerAdapter {
    @Autowired
    private Twitter twitter;

    @Autowired
    private TwitterTemplate twitterTemplate;

    public void getTweets() {
        log.info("Tweets:", twitter.searchOperations().search("#Amazon"));
    }

    public void tweet(String tweetText) {
        Twitter t = new TwitterTemplate("1KHoEzRifBOlpOJvogBxMo39b", "8pMQpULFLjE8ADhHqT5HTuDhc46iUqGOI733NB9M7F1h4AQJXS",
                "1114229184-UL1GND9Z2ZchbVN9jOW1AbO1u6as5xBW7DqtFgI",
                "kr15AoOC7khbOb5TuEuutqqc3Ma7hciIyTwiPg0spKmfO");

        try {
            TwitterProfile p = t.userOperations().getUserProfile();
            SearchResults ts = t.searchOperations().search("#Amazon");
            log.info(ts.toString());
        } catch (RuntimeException ex) {
            log.error("Unable to tweet" + tweetText, ex);
        }
    }
}
