package edu.baylor.cs.beargo.service;


import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class NotificationService {

    @Autowired
    NotificationRepository repo;

    public List<Notification> getNotification(User user) {
        List<Notification> notificationList = repo.findByNotifyuser(user);
        return notificationList;

    }

    public Notification saveNotification(User user,Notification notification){

        notification.setNotifyuser(user);
        Notification savedNotification = repo.save(notification);
        return savedNotification;

    }
}
