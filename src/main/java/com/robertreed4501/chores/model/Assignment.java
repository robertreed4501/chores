package com.robertreed4501.chores.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Assignment {
    @Id
    @SequenceGenerator(
            name = "assignment_sequence",
            sequenceName = "assignment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "assignment_sequence"
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
}
