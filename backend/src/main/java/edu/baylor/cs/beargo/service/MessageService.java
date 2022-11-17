package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Message;
import edu.baylor.cs.beargo.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MessageService {

    @Autowired
    MessageRepository msgRepo;

    public Message saveMsg(Message m){

        Message savedMsg = msgRepo.save(m);
        return savedMsg;

    }

    public List<Message> getAllmsg(Long uid){

        List<Message> msgList = msgRepo.findByfromUser(uid);
        return msgList;


    }
}
