package com.robertreed4501.chores.model.http.requests;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UpdateUserRequest {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
