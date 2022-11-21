package com.robertreed4501.chores.model.http.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class LastWeeksDashboard {

    @Id
    private Long id;
    private String firstName;
    private Long userId;
    private Long groupId;
    private Long assignmentId;
    private String name;
    private String description;
    private int multiplier;
    private Boolean done;

}
