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
public class Chore {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ChoreLevel choreLevel;
    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    @Enumerated(EnumType.STRING)
    private Scope scope;

    public Chore(String name, ChoreLevel choreLevel, Frequency frequency, Scope scope) {
        this.name = name;
        this.choreLevel = choreLevel;
        this.frequency = frequency;
        this.scope = scope;
    }
}
