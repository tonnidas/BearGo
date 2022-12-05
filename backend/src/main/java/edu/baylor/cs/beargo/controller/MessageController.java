package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.dto.MessageDto;
import edu.baylor.cs.beargo.model.Message;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.AdminService;
import edu.baylor.cs.beargo.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/Message")
public class MessageController {
    @Autowired
    MessageService msgService;

    @Autowired
    AdminService adminService;

    /**
     * Selects all the msgs of user
     *
     * @param toId the toId
     * @param user The logged user
     * @return
     */
    @GetMapping(value = "/{toId}")
    public ResponseEntity<List<Message>> getMyMsg(@PathVariable(value = "toId") Long toId, @AuthenticationPrincipal User user) {

        //Long uid = user.getId();
        log.info("Retrieving Message for User: " + user.getId().toString());
        List<Message> msgList = msgService.getMyMsg(user, toId);
        return new ResponseEntity<>(msgList, HttpStatus.OK);
    }

    /**
     * Selects all msgs of user
     *
     * @param user the logged user
     * @return
     */
    @GetMapping(path = "/all")
    public ResponseEntity<List<Message>> getAllMsg(@AuthenticationPrincipal User user) {

        //Long uid = user.getId();
        log.info("Listing all Message for ");
        List<Message> msgList = msgService.getAllmsg();
        return new ResponseEntity<>(msgList, HttpStatus.OK);
    }

    /**
     * @param user the logged user
     * @return a list of msg Dto
     */
    @GetMapping(path = "/msngrList")
    public ResponseEntity<List<MessageDto>> getAllmsngrList(@AuthenticationPrincipal User user) {

        //Long uid = user.getId();
        log.info("Retrieving latest Message List");
        List<Message> msgList = msgService.getAllmsngrList(user);
        List<MessageDto> msgListdto = MessageDto.getMessageDto(msgList);
        return new ResponseEntity<>(msgListdto, HttpStatus.OK);
    }

    /**
     * @param toId the to id
     * @param user the logged user
     * @param m    the msg
     * @return the saved msg
     */
    @PostMapping(value = "/{toId}")
    public ResponseEntity<Message> saveMsg(@PathVariable(value = "toId") Long toId, @AuthenticationPrincipal User user, @RequestBody Message m) {

        System.out.println("------------------------------");
        System.out.println(m.getMsg());
        System.out.println("------------------------------");

        log.info("Saving Message for User: " + user.getId().toString());
        Message msg = msgService.saveMsg(m, user, toId);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    /**
     * @param user the logged user
     * @return a list of users
     */
    @GetMapping(path = "/all/users")
    public ResponseEntity<List<User>> getAllUsers(@AuthenticationPrincipal User user) {

        //Long uid = user.getId();
        log.info("Getting all users ");
        List<User> userList = adminService.getUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }


}
