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
public class ProductPostComplaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reason; // reason of complain

    @Column(nullable = false)
    private Boolean isResolved;

    @Column(nullable = false)
    private LocalDate complainDate;

    @Column
    private LocalDate resolveDate;

    @ManyToOne // owning-side
    @JoinColumn(name = "product_post_id")
    @JsonIdentityReference(alwaysAsId = true)
    private ProductPost productPost;

    @ManyToOne // owning-side
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User complainedBy;

    @ManyToOne // owning-side
    @JoinColumn(name = "admin_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User resolvedBy;
}