package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.UserGroup;
import com.robertreed4501.chores.model.http.requests.RegistrationRequest;
import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.enums.UserRole;
import com.robertreed4501.chores.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Transactional
public class RegistrationService {

    private final UserService userService;
    private final UserGroupService userGroupService;
    //private final MailSenderService mailSenderService;

    public String register(RegistrationRequest request){

        User user = new User(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPassword(), UserRole.ADMIN, request.getDob());
        System.out.println(user.getId() + " - user.getId()");
        userService.addUser(user);
        User numberedUser = userService.findUserByEmail(request.getEmail());
        System.out.println(numberedUser.getId() + " - numberedUser.getId()");
        UserGroup userGroup = new UserGroup(numberedUser, LocalDateTime.now());
        userGroupService.save(userGroup);
        UserGroup numberedUserGroup = userGroupService.findByUser(numberedUser);
        numberedUser.setUserGroup(numberedUserGroup);
        //mailSenderService.send(request.getEmail(), "testing the email");
        return "registered";

        //TODO create new user first, then create new group and assign new user to new group


    }

}
