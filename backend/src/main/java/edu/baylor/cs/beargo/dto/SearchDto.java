package edu.baylor.cs.beargo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {
    private Long id;
    private Date startDate;
    private Date endDate;
    private String sourceCity;
    private String sourceState;
    private String destCity;
    private String destState;
}
