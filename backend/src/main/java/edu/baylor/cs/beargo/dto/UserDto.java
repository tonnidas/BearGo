package edu.baylor.cs.beargo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private Boolean isAdmin;
    private String fullname;
    private Long imageId;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
}
