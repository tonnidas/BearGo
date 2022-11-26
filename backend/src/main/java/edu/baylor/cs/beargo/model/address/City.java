package edu.baylor.cs.beargo.model.address;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public String city;

    @ManyToOne // owning-side
    @JoinColumn
    @JsonIdentityReference(alwaysAsId = true)
    private State state;

    @OneToMany(mappedBy = "city") // inverse-side
    @JsonIdentityReference(alwaysAsId = true)
    private List<Zip> zipList;
}
