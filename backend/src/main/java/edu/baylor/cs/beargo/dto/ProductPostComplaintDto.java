package edu.baylor.cs.beargo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPostComplaintDto {
    private Long id;
    private String reason;
    private Boolean isResolved;
    private LocalDate complainDate;
    private LocalDate resolveDate;
    private UserDto complainedBy;
    private UserDto resolvedBy;
}
