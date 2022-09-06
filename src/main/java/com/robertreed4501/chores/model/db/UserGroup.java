package com.robertreed4501.chores.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class UserGroup {

    @Id
    @SequenceGenerator(
            name = "user_group_sequence",
            sequenceName = "user_group_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_group_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(
            nullable = true,
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;
    private LocalDateTime created;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "userGroup")
    @JsonManagedReference
    List<User> users;
    @OneToMany(mappedBy = "userGroup")
    @JsonManagedReference
    List<Chore> chores;

    public UserGroup(User user, LocalDateTime created) {
        this.user = user;
        this.created = created;
    }
}
