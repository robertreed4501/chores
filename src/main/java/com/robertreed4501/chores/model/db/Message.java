package com.robertreed4501.chores.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id_to",
            referencedColumnName = "id"
    )
    private User userIdTo;
    @JsonBackReference
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
    private Boolean deleted;

    public Message(User userIdTo, User userIdFrom, String subject, String body, LocalDateTime sentAt, LocalDateTime readAt, Boolean hasBeenRead, Boolean deleted) {
        this.userIdTo = userIdTo;
        this.userIdFrom = userIdFrom;
        this.subject = subject;
        this.body = body;
        this.sentAt = sentAt;
        this.readAt = readAt;
        this.hasBeenRead = hasBeenRead;
        this.deleted = deleted;
    }
}
