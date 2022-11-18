package edu.baylor.cs.beargo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReviewAndRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String review;

    @Column
    @Min(value=0, message="Rating must be equal or greater than 0")
    @Max(value=5, message="Rating must be equal or less than 5")
    private Integer rating;

    @Column(nullable = false)
    private LocalDate reviewTime;

    @ManyToOne // owning-side
    @JoinColumn(name = "reviewedBy")
    @JsonIdentityReference(alwaysAsId = true)
    private User reviewedBy;

    @ManyToOne // owning-side
    @JoinColumn(name = "reviewedTo")
    @JsonIdentityReference(alwaysAsId = true)
    private User reviewedTo;
}
