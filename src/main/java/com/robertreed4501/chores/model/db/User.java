package com.robertreed4501.chores.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.robertreed4501.chores.model.enums.UserRole;
import com.robertreed4501.chores.model.http.response.UserResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

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
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dob;
    @Enumerated(EnumType.STRING)
    private UserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = true;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(

            nullable = true,
            name = "group_id",
            referencedColumnName = "id"
    )
    private UserGroup userGroup;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user")
    private List<Assignment> assignments;

    private String apiKey;


    public User(String firstName,
                String lastName,
                String email,
                String password,
                UserRole appUserRole,
                String dob
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
        this.dob = dob;
        this.locked = locked;
        this.enabled = enabled;
    }

    public UserResponse getUserResponse(){
        return new UserResponse(
                this.id,
                this.firstName,
                this.lastName,
                this.email,
                this.appUserRole.name(),
                this.apiKey,
                this.getUserGroup().getId()
                );
    }
}

