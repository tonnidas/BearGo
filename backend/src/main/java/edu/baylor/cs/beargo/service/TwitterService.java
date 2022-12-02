package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.TwitterModel;
import edu.baylor.cs.beargo.repository.TwitterModelRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class TwitterService{

    @Value("${beargo.twitter.pagesize}")
    private int pageSize;

    @Autowired
    private TwitterModelRepository repo;

   public List<TwitterModel> getAllTweets(){
       //Pageable pageable = PageRequest.of(0, pageSize);
       Pageable sortedCreatedDeate = PageRequest.of(0, pageSize, Sort.by("createdAt").descending());
       Page<TwitterModel> tweetList =  repo.findAll(sortedCreatedDeate);

       if(tweetList.hasContent()) {
           return tweetList.getContent();
       } else {
           return new ArrayList<TwitterModel>();
       }

   }

   public TwitterModel saveTweet(TwitterModel t){

        String tUrl = "https://twitter.com/" + t.getTUsername() + "/status/" + t.getTid().toString();
        t.setTweeturl(tUrl);
        t.setTidstring(t.getTid().toString());
        TwitterModel savedTweet = repo.save(t);
        return savedTweet;
   }
}
