package com.robertreed4501.chores.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.robertreed4501.chores.model.enums.ChoreLevel;
import com.robertreed4501.chores.model.enums.Frequency;
import com.robertreed4501.chores.model.enums.Scope;
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
    @JsonBackReference
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "id"
    )
    private UserGroup userGroup;

    public Chore(String name, ChoreLevel choreLevel, Frequency frequency, Scope scope, UserGroup userGroup) {
        this.name = name;
        this.choreLevel = choreLevel;
        this.frequency = frequency;
        this.scope = scope;
        this.userGroup = userGroup;
    }
}
