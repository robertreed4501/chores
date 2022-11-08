package com.robertreed4501.chores.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.robertreed4501.chores.model.enums.ChoreLevel;
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

    private int multiplier;
    @Enumerated(EnumType.STRING)
    private Scope scope;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "id"
    )
    private UserGroup userGroup;
    private boolean enabled = true;
    private String description;

    public Chore(String name, String description, ChoreLevel choreLevel, int multiplier, Scope scope, UserGroup userGroup) {
        this.name = name;
        this.description = description;
        this.choreLevel = choreLevel;
        this.multiplier = multiplier;
        this.scope = scope;
        this.userGroup = userGroup;
    }
}
