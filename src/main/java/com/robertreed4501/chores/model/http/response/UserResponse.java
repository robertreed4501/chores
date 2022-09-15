package com.robertreed4501.chores.model.http.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String key;
    private Long groupId;
}
