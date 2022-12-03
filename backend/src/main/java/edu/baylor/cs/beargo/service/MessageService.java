package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.dto.MessageDto;
import edu.baylor.cs.beargo.model.Message;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.MessageRepository;
import edu.baylor.cs.beargo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MessageService {

    @Autowired
    EntityManager entityManager;

    @Autowired
    MessageRepository msgRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;


    @Autowired
    private KafkaTemplate<String, MessageDto> kafkaTemplate;

    public Message saveMsg(Message m, User fromUser, Long toId) {

        User toUser = userRepo.findById(toId).orElse(null);

        m.setFromUser(fromUser);
        m.setToUser(toUser);

        Message savedMsg = msgRepo.save(m);
        System.out.println(m.getMsg());


        MessageDto saveddto = MessageDto.getMessageDtodata(savedMsg);
        String topicName = "newmessage";
        kafkaTemplate.send(topicName, saveddto);



        // Sent to websocket
        //String topic = "/topic/newmsg" + savedMsg.getId().toString();
        //messagingTemplate.convertAndSend(topic, savedMsg);

        return savedMsg;
    }

    public List<Message> getMyMsg(User user, Long toId) {
        User toUser = userRepo.findById(toId).orElse(null);
        return msgRepo.findMyMsg(user,toUser);
    }



    public List<Message> getAllmsg() {
        return msgRepo.findAll();
    }

    public List<Message> getAllmsngrList(User user) {


        return msgRepo.findMyMsgList(user);
    }

    public List<User> getUsers() {


        return userRepo.findAll();
    }
}
