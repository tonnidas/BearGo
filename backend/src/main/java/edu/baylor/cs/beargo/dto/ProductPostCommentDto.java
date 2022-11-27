package edu.baylor.cs.beargo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPostCommentDto {
    private Long id;
    private String comment;
    private LocalDateTime commentTime;
    private UserDto commentedBy;
}
