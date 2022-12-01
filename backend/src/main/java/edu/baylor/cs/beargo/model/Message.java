package edu.baylor.cs.beargo.model;


import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

@NamedQuery(name = "Message.findMyMsg", query = "SELECT a FROM Message a WHERE (a.fromUser = :aUser AND a.toUser = :bUser) OR (a.fromUser = :bUser AND a.toUser = :aUser) order by createdAt")

// Query to get latest distinct message List
@NamedQuery(name = "Message.findMyMsgList", query = "SELECT m FROM Message m LEFT JOIN Message m1 ON ("+
        " ("+
        "(m.fromUser  = m1.fromUser  AND m.toUser   = m1.toUser  ) "+
        "OR"+
        " (m.fromUser  = m1.toUser   AND m.toUser  = m1.fromUser  )"+
        " )"+
        " AND m.createdAt  < m1.createdAt "+
        " )"+
        " WHERE m1.id IS NULL AND (m.fromUser = :aUser OR m.toUser =:aUser)"+
        " ORDER BY m.createdAt DESC")


public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String msg;

    @Column
    private Date createdAt = new Date();

    @ManyToOne(fetch = FetchType.EAGER) // owning-side
    @JoinColumn(name = "fromUserId")
    @JsonIdentityReference(alwaysAsId = true)
    //@JsonIgnore
    private User fromUser;

    @ManyToOne // owning-side
    @JoinColumn(name = "toUserId")
    @JsonIdentityReference(alwaysAsId = true)
    //@JsonIgnore
    private User toUser;

    @Enumerated
    private Status status;


    public enum Status {
        SENT, READ, SEEN
    }





}
