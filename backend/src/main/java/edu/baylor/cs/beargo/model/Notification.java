package edu.baylor.cs.beargo.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

// This entity holds all the notification data
@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Notification  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private NotificationType type;

    // Defines different notification type
    public enum NotificationType {
        MSG, CONTRACT, DELIVERY_TRACKING, REPORT_USER, REPORT_COMMENT
    }

    @Column
    private String notificationMsg;

    @Column
    private String details;

    @Column
    private Date createdAt = new Date();


    @ManyToOne(fetch = FetchType.LAZY) // owning-side
    @JoinColumn(name = "notification_to_id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnore
    private User notifyUser;
}
