package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.requests.RegistrationRequest;
import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.enums.UserRole;
import com.robertreed4501.chores.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;

    public String register(RegistrationRequest request){

        userRepository.save(new User(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), UserRole.ADMIN, request.getDob()));
        mailSenderService.send(request.getEmail(), "testing the email");
        return "registered";
    }

}
