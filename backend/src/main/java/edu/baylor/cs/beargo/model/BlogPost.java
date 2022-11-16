package edu.baylor.cs.beargo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate postedTime;

    @Column(nullable = false)
    private LocalDate lastEditedTime;

    @Column
    private String imageFilePath;

    @Column(nullable = false)
    private String description;

    @ManyToOne //owning-side
    @JoinColumn(name = "userId")
    @JsonIdentityReference(alwaysAsId = true)
    private User postedBy;
}
