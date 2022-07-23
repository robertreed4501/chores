package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.User;
import com.robertreed4501.chores.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String save(User user){
        userRepository.save(user);
        return "user saved";
    }
}
