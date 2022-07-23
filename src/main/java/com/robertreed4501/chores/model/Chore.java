package com.robertreed4501.chores.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@ToString
public class Chore {

    @Id
    @SequenceGenerator(
            name = "chore_sequence",
            sequenceName = "chore_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "chore_sequence"
    )
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ChoreLevel choreLevel;
    @Enumerated(EnumType.STRING)
    private Frequency frequency;
    @Enumerated(EnumType.STRING)
    private Scope scope;
    @ManyToOne
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "id"
    )
    private UserGroup userGroup;

    public Chore(String name, ChoreLevel choreLevel, Frequency frequency, Scope scope) {
        this.name = name;
        this.choreLevel = choreLevel;
        this.frequency = frequency;
        this.scope = scope;
    }
}
