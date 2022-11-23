package edu.baylor.cs.beargo.repository;


import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, User> {

    List<Notification> findByNotifyuserOrderByCreatedAtDesc(User user, Pageable pageable);
}
