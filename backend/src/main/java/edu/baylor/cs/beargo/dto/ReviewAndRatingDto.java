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
public class ReviewAndRatingDto {
    private String review;
    private Integer rating;
    private LocalDateTime reviewDateTime;
    private String reviewer;
}
