package com.beargo.twitterstreamintegration.Service;


import com.beargo.twitterstreamintegration.Model.TwitterModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class TwitterService {

    @Autowired
    private KafkaTemplate<String, String> tTemplate;

    public void sendTweetKafka(TwitterModel t){


        log.info("Send to kafka");
        log.info(t.getTid().toString());
        log.info(t.getTText());
        log.info(t.getTUsername());

        log.info("Get The Object {}",t);


        String topicName = "tweet";

        String data = t.getTid().toString()+"|" + t.getTUsername()+"|"+t.getTText();
        tTemplate.send(topicName,data);
    }

}
