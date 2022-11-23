package edu.baylor.cs.beargo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserComplaint {
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
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User complainedByUser;

    @OneToMany  //
    @JoinColumn(name = "user_id")
    @JsonIdentityReference(alwaysAsId = true)
    private List<User> complainedUser;

    @ManyToOne // owning-side
    @JoinColumn(name = "admin_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User resolvedBy;
}