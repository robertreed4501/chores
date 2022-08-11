package com.robertreed4501.chores.model.http.response;

import com.robertreed4501.chores.model.db.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {

    private UserResponse userResponse;
    private String error;
}
