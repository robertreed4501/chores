package com.robertreed4501.chores.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
            name = "assignment_id",
            referencedColumnName = "id"
    )
    @JsonBackReference
    private Assignment assignment;
    private LocalDateTime timestamp;
    private boolean confirmed;
    private boolean paid;

    public Receipt(Assignment assignment, LocalDateTime timestamp, boolean confirmed, boolean paid) {
        this.assignment = assignment;
        this.timestamp = timestamp;
        this.confirmed = confirmed;
        this.paid = paid;
    }
}
