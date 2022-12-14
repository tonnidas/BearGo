package edu.baylor.cs.beargo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String description;

    @Column
    private Long imageId;

    @Column(nullable = false)
    private LocalDate expectedPickupDate;

    @Column(nullable = false)
    private LocalDate expectedDeliveryDate;

    @Column(nullable = false)
    private boolean isBlocked;

    @OneToOne // owning-side
    @JoinColumn(name = "source_address_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Address source;

    @OneToOne // owning-side
    @JoinColumn(name = "destination_address_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Address destination;

    @OneToOne // owning-side
    @JoinColumn(name = "product_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Product product;

    @OneToOne // owning-side
    @JoinColumn(name = "contract_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Contract contract;

    @OneToMany(mappedBy = "productPost") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private Set<ProductPostComplaint> complaints = new HashSet<>();

    @OneToMany(mappedBy = "commentedProductPost") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private List<ProductPostComment> comments = new ArrayList<>();

    @ManyToMany // owning-side
    @JoinTable(
            name = "Post_interestedPeople",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "interestedPeople_id"))
    private Set<User> interestedPeoples = new HashSet<>();
}