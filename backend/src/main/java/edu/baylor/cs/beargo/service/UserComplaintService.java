package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Message;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.model.UserComplaint;
import edu.baylor.cs.beargo.repository.MessageRepository;
import edu.baylor.cs.beargo.repository.UserComplaintRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserComplaintService {

    @Autowired
    UserComplaintRepository userComplaintRepository;


    public UserComplaint createUserComplaint(User user, UserComplaint userComplaint)
    {
        if(userComplaint.getReason() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reason cannot be empty text");
        userComplaint.setComplainedByUser(user);
        userComplaint.setComplainDate(LocalDate.now());
        userComplaint.setIsResolved(false);
        return userComplaintRepository.save(userComplaint);
    }

}

