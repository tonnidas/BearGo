package edu.baylor.cs.beargo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String zip;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @OneToOne(mappedBy = "source") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private ProductPost sourceProductPost;

    @OneToOne(mappedBy = "destination") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private ProductPost destinationProductPost;
}