package edu.baylor.cs.beargo.dto;

import edu.baylor.cs.beargo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserComplaintDto {
    private Long id;
    private String username;
    private User complainedByUser;
    private Long complainedUserId;
    Set<User> usersComplained = new HashSet<>();
}
