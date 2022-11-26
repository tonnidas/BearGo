package edu.baylor.cs.beargo.repository;

import edu.baylor.cs.beargo.model.Message;
import edu.baylor.cs.beargo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByfromUser(Long uid);
    List<Message> findByToUser(Long uid);

    List<Message> findByFromUserOrToUser(User fromUser, User toUser);

    List<Message> findMyMsg(@Param("aUser") User auser, @Param("bUser") User buser);
    List<Message> findMyMsgList(@Param("aUser") User user);





}
