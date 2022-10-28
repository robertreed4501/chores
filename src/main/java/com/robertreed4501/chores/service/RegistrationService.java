package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.UserGroup;
import com.robertreed4501.chores.model.http.requests.NewUserRequest;
import com.robertreed4501.chores.model.http.requests.RegistrationRequest;
import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.enums.UserRole;
import com.robertreed4501.chores.model.http.response.UserResponse;
import com.robertreed4501.chores.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class RegistrationService {

    private final UserService userService;
    private final UserGroupService userGroupService;
    //private final MailSenderService mailSenderService;

    public String register(RegistrationRequest request){
        if (userService.existsByEmail(request.getEmail())) return "Email already exists.";
        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), UserRole.ADMIN, request.getDob());
        UUID key = UUID.randomUUID();
        user.setApiKey(key.toString());
        System.out.println(user.getId() + " - user.getId()");
        userService.addUser(user);
        User numberedUser = userService.getUserByApiKey(key.toString());
        System.out.println(numberedUser.getId() + " - numberedUser.getId()");
        UserGroup userGroup = new UserGroup(numberedUser, LocalDateTime.now());
        userGroupService.save(userGroup);
        UserGroup numberedUserGroup = userGroupService.findByUser(numberedUser);
        numberedUser.setUserGroup(numberedUserGroup);
        //mailSenderService.send(request.getEmail(), "testing the email");
        return "registered";

    }

    public String addUser(NewUserRequest request) {

        User currUser = new User(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), UserRole.USER, request.getDob());
        currUser.setUserGroup(userGroupService.findById(request.getGroupId()));
        return userService.addUser(currUser);


    }

}
