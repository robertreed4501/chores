package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.db.UserGroup;
import com.robertreed4501.chores.repository.UserGroupRepository;
import com.robertreed4501.chores.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/mygroup")
    public List<User> getUsersByGroupId(Long id){
        return userService.getUsersByGroupId(id);
    }
}
