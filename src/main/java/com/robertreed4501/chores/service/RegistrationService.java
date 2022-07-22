package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.RegistrationRequest;
import com.robertreed4501.chores.model.User;
import com.robertreed4501.chores.model.UserRole;
import com.robertreed4501.chores.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    public String register(RegistrationRequest request){

        userRepository.save(new User(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), UserRole.ADMIN, request.getDob()));



        return "registered";
    }
}
