package edu.baylor.cs.beargo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductPostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment; // comment text

    @Column(nullable = false)
    private LocalDateTime commentTime;

    @ManyToOne // owning-side
    @JoinColumn(name = "product_post_id")
    @JsonIdentityReference(alwaysAsId = true)
    private ProductPost commentedProductPost;

    @ManyToOne // owning-side
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User commentedBy;
}