package edu.baylor.cs.beargo.service;


import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class NotificationService {

    @Value("${beargo.notification.pagesize}")
    private int pagesize;

    @Autowired
    NotificationRepository repo;

    public List<Notification> getNotification(User user) {
        //List<Notification> notificationList = repo.findByNotifyuser(user);


        log.info("Retrieving notifications for user");
        Pageable pageable = PageRequest.of(0,pagesize);
        List<Notification> notificationList = repo.findByNotifyuserOrderByCreatedAtDesc(user,pageable);
        return notificationList;

    }

    public Notification saveNotification(User user,Notification notification){

        notification.setNotifyuser(user);
        Notification savedNotification = repo.save(notification);
        return savedNotification;

    }
}
