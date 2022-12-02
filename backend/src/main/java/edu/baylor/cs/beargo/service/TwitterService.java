package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.TwitterModel;
import edu.baylor.cs.beargo.repository.TwitterModelRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class TwitterService{
    @Autowired
    private TwitterModelRepository repo;

   public List<TwitterModel> getAllTweets(){

       return repo.findAll();
   }

   public TwitterModel saveTweet(TwitterModel t){

        String tUrl = "https://twitter.com/" + t.getTUsername() + "/status/" + t.getTid().toString();
        t.setTweeturl(tUrl);
        t.setTidstring(t.getTid().toString());
        TwitterModel savedTweet = repo.save(t);
        return savedTweet;
   }
}
