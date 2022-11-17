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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String msg;

    @ManyToOne // owning-side
    @JoinColumn(name = "fromUserId")
    @JsonIdentityReference(alwaysAsId = true)
    private User fromUser;

    @ManyToOne // owning-side
    @JoinColumn(name = "toUserId")
    @JsonIdentityReference(alwaysAsId = true)
    private User toUser;

    @Enumerated
    private Status status;


    public enum Status{
        SENT, READ, SEEN
    }


}
