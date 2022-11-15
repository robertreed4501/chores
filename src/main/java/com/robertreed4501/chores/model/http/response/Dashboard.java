package com.robertreed4501.chores.model.http.response;

import com.robertreed4501.chores.model.enums.Frequency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Dashboard {

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
