package edu.baylor.cs.beargo.service;


import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.NotificationRepository;
import edu.baylor.cs.beargo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class NotificationService {

    @Value("${beargo.notification.pagesize}")
    private int pageSize;

    @Autowired
    NotificationRepository repo;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private KafkaTemplate<String, Notification> notificationKafkaTemplate;

    /**
     * @param user the user
     * @return a list of notifications
     */
    public List<Notification> getNotification(User user) {
        //List<Notification> notificationList = repo.findByNotifyuser(user);


        log.info("Retrieving notifications for user");


        //Pageable pageable = PageRequest.of(0,pageSize, Sort.by(Sort.Direction.DESC,"createdAt"));
        Pageable pageable = PageRequest.of(0, pageSize);
        return repo.findByNotifyUserOrderByCreatedAtDesc(user, pageable);

    }

    /**
     * @param user         the user
     * @param notification the notification
     * @param toUserId     the user id
     * @return a notification
     */
    public Notification saveNotification(User user, Notification notification, Long toUserId) {

        User toUser = userRepository.findById(toUserId).orElse(null);

        notification.setNotifyUser(toUser);

        Notification savedNotification = repo.save(notification);
        String topicName = "newnotification";

        log.info("Sending notifications to kafka");

        notificationKafkaTemplate.send(topicName, savedNotification);
        return savedNotification;
    }
}
