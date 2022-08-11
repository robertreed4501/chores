package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.http.requests.LoginRequest;
import com.robertreed4501.chores.model.http.response.LoginResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class LoginService {

    private final UserService userService;

    public LoginResponse Login(LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        LoginResponse response;
        User user = userService.getUserByUsername(username);
        if (user == null) {
            response = new LoginResponse(null, "username not found");
            return response;
        }


        if (!password.equals(user.getPassword())) {
            response = new LoginResponse(null, "invalid password");
        } else{
            String key = UUID.randomUUID().toString();
            user.setApiKey(key);
            response = new LoginResponse(user.getUserResponse(), null );

        }


        return response;
    }
}
