package com.robertreed4501.chores.model.db;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Receipt {
    @Id
    @SequenceGenerator(
            name = "receipt_sequence",
            sequenceName = "receipt_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "receipt_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "chore_id",
            referencedColumnName = "id"
    )
    private Chore chore;
    private LocalDateTime timestamp;
    private boolean confirmed;
    private boolean paid;

    public Receipt(User user, Chore chore, LocalDateTime timestamp, boolean confirmed, boolean paid) {
        this.user = user;
        this.chore = chore;
        this.timestamp = timestamp;
        this.confirmed = confirmed;
        this.paid = paid;
    }
}
