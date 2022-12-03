package edu.baylor.cs.beargo.Config;

import edu.baylor.cs.beargo.dto.MessageDto;
import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.model.TwitterModel;
import edu.baylor.cs.beargo.repository.NotificationRepository;
import edu.baylor.cs.beargo.service.TwitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;



@Component
@Slf4j
@Configuration
public class KafkaListener {


    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    TwitterService tService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    //private static SimpMessageSendingOperations messagingTemplate;


    // Listener for String
    @org.springframework.kafka.annotation.KafkaListener(topics = "string", groupId = "group_string", containerFactory = "kafkaListenerContainerFactory")
    public void listenString(String data) {

        log.info(data);
        System.out.println(data);

    }

    // Listening for new notifications


    @org.springframework.kafka.annotation.KafkaListener(topics = "newnotification", groupId = "group_notification", containerFactory = "NotificationContainerFactory")
    public void listenNotification(Notification notifications) {

        System.out.println(notifications.getNotificationMsg());
        log.info("Listening {}", notifications);
        Long uid = notifications.getId();


        String topic = "/topic/newNotification2";


        System.out.println("Received New Notification");

        // send new notification to that user
       messagingTemplate.convertAndSend(topic, notifications);


    }

    // Listener for msg


    @org.springframework.kafka.annotation.KafkaListener(topics = "newmessage", groupId = "group_msgdto", containerFactory = "MessageDtoContainerFactory")
    public void listenMessage(MessageDto message) {

        log.info("Listening {}", message);
        Long uid = message.getToid();

        String topic = "/topic/newmsg" + uid.toString();
        System.out.println(topic);
        // send new msg to that user
        messagingTemplate.convertAndSend(topic, message);


    }


    // Listener for Twitter


    @org.springframework.kafka.annotation.KafkaListener(topics = "tweet", groupId = "group_tweet", containerFactory = "TwitterModelContainerFactory")
    public void listenTweet(TwitterModel twitterModel) {

        System.out.println("Received Tweets");
        log.info("Listening {}", twitterModel);
        System.out.println("h"+twitterModel);
        System.out.println(""+twitterModel.toString());
        System.out.println(twitterModel.getTid());
        System.out.println(twitterModel.getTText());
        System.out.println(twitterModel.getTUsername());


        System.out.println("Received Tweets");


        String topic = "/topic/tweet";

        TwitterModel savedTweet = tService.saveTweet(twitterModel);
        messagingTemplate.convertAndSend(topic, savedTweet);


    }




    // More Listener to be added


}

