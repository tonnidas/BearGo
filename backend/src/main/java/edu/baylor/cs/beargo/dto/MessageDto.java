package edu.baylor.cs.beargo.dto;

import edu.baylor.cs.beargo.model.Message;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {



    private Long id;
    private String msg;
    private Long fromid;
    private String fromname;
    private Long toid;
    private String toName;
    private Date createdAt;

    public static List<MessageDto> getMessageDto(List<Message> msgList){
        List<MessageDto> retval = new ArrayList<>();
        for(Message msg: msgList){


            MessageDto dto = new MessageDto();

            dto.setId(msg.getId());
            dto.setCreatedAt(msg.getCreatedAt());

            dto.setToid(msg.getId());
            dto.setMsg(msg.getMsg());

            dto.setFromid(msg.getFromUser().getId());
            dto.setFromname(msg.getFromUser().getFullname());

            dto.setToid(msg.getToUser().getId());
            dto.setToName(msg.getToUser().getFullname());

            retval.add(dto);


        }
        return retval;


    }

    public static MessageDto getMessageDtodata(Message msg){

            MessageDto dto = new MessageDto();

            dto.setId(msg.getId());
            dto.setCreatedAt(msg.getCreatedAt());

            dto.setToid(msg.getId());
            dto.setMsg(msg.getMsg());

            dto.setFromid(msg.getFromUser().getId());
            dto.setFromname(msg.getFromUser().getFullname());

            dto.setToid(msg.getToUser().getId());
            dto.setToName(msg.getToUser().getFullname());


        return dto;


    }

}
