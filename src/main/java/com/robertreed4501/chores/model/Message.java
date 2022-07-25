package com.robertreed4501.chores.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Message {
    @Id
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id_to",
            referencedColumnName = "id"
    )
    private User userIdTo;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id_from",
            referencedColumnName = "id"
    )
    private User userIdFrom;
    private String subject;
    private String body;
    private LocalDateTime sentAt;
    private LocalDateTime readAt;
    private Boolean hasBeenRead;

    public Message(User userIdTo, User userIdFrom, String subject, String body, LocalDateTime sentAt, LocalDateTime readAt, Boolean hasBeenRead) {
        this.userIdTo = userIdTo;
        this.userIdFrom = userIdFrom;
        this.subject = subject;
        this.body = body;
        this.sentAt = sentAt;
        this.readAt = readAt;
        this.hasBeenRead = hasBeenRead;
    }
}
