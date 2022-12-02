package edu.baylor.cs.beargo.Service;


import edu.baylor.cs.beargo.Model.TwitterModel;
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
    //private KafkaTemplate<String, String> tTemplate;
    private KafkaTemplate<String, TwitterModel> tTemplate;

    public void sendTweetKafka(TwitterModel t){


        log.info("Send to kafka");
        //og.info(t.getId().toString());
        log.info(t.getTText());
        log.info(t.getTUsername());

        log.info("Get The Object {}",t);


        String topicName = "tweet";

        //String data = t.getId().toString()+"|" + t.getTUsername()+"|"+t.getTText();
        //tTemplate.send(topicName,data);
        tTemplate.send(topicName,t);
    }

}
