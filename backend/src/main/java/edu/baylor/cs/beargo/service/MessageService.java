package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Message;
import edu.baylor.cs.beargo.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MessageService {

    @Autowired
    MessageRepository msgRepo;

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public Message saveMsg(Message m) {
        Message savedMsg = msgRepo.save(m);

        String topicName = "/topic/msg/message";
        kafkaTemplate.send(topicName, savedMsg);

        return savedMsg;
    }

    public List<Message> getAllmsg(Long uid) {
        return msgRepo.findByfromUser(uid);
    }
}
