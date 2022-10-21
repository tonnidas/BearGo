package edu.baylor.cs.beargo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column
    private LocalDate contractStartDate;

    @Column(nullable = false)
    private LocalDate contractEndDate;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "contract") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private ProductPost productPost;

    @ManyToOne // owning-side
    @JoinColumn(name = "sender_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User sender;

    @ManyToOne // owning-side
    @JoinColumn(name = "traveler_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User traveler;

    @Column
    @Max(5)
    private Integer ratingBySender; // The rating that the sender gave to the traveler

    @Column
    @Max(5)
    private Integer ratingByTraveler; // The rating that the traveler gave to the sender

    @Column
    private String reviewBySender; // The review that the sender gave to the traveler

    @Column
    private String reviewByTraveler; // The review that the traveler gave to the sender
}