package edu.baylor.cs.beargo.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

// This entity holds all the notification data
@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private NotificationType type;

    // Defines different notification type
    public enum NotificationType {
        MSG,CONTRACT,DELIVERYTRACKING,CONTRACTSIGNED
    }

    @Column
    private String notficationMsg;

    @Column
    private String details;

    @ManyToOne // owning-side
    @JoinColumn(name = "notification_to_id")
    @JsonIdentityReference(alwaysAsId = true)
    private User notifyuser;




}