package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;

    public User authorizeApiKey(String key){
        return userService.getUserByApiKey(key);
    }
}
