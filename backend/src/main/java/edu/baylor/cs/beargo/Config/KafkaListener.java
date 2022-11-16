package edu.baylor.cs.beargo.Config;

import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.repository.NotificationRepository;
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

    //@Autowired
    //private SimpMessageSendingOperations messagingTemplate;



    // Listener for String
    @org.springframework.kafka.annotation.KafkaListener(topics="msgstring",groupId = "group_string",containerFactory = "kafkaListenerContainerFactory")
    public void listenString(String data) {

        log.info(data);

    }

    // Listening for new notifications


    @org.springframework.kafka.annotation.KafkaListener(topics="notification",groupId = "group_notification",containerFactory = "NotificationContainerFactory")
    public void listenNotification(Notification notifications) {

        log.info("Listening {}"+ notifications);
        Long uid = notifications.getId();

        String topic = "/topic/newNotification/"+uid.toString();

        // send new notification to that user
        //messagingTemplate.convertAndSend(topic, notifications);


    }



    // More Listener to be added


}
