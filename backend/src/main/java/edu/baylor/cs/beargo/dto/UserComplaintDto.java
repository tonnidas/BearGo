package edu.baylor.cs.beargo.dto;

import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.model.UserComplaint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserComplaintDto {
    private Long id;
    private String reason;
    private Boolean isResolved;
    private LocalDate complainDate;
    private LocalDate resolveDate;
    private User complainedUser;
    private Long complainedByUserId;
    private String complainedByUserName;
    private User resolvedBy;

    public static UserComplaintDto getUserComplaintDto(UserComplaint userComplaint) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userComplaint, UserComplaintDto.class);
    }
}
