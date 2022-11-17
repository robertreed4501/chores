package com.robertreed4501.chores.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
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


    private int multiplier;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(
            name = "group_id",
            referencedColumnName = "id"
    )
    private UserGroup userGroup;
    private boolean enabled = true;
    private String description;

    public Chore(String name, String description, int multiplier, UserGroup userGroup) {
        this.name = name;
        this.description = description;
        this.multiplier = multiplier;
        this.userGroup = userGroup;
    }
}
