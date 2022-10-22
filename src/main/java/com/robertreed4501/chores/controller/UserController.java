package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.db.UserGroup;
import com.robertreed4501.chores.model.http.response.LoginResponse;
import com.robertreed4501.chores.model.http.response.UserResponse;
import com.robertreed4501.chores.model.http.response.UsersInGroupResponse;
import com.robertreed4501.chores.repository.UserGroupRepository;
import com.robertreed4501.chores.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/mygroup")
    public List<UserResponse> getUsersByGroupId(@RequestParam Long id){
        return userService.getUsersByGroupId(id);
    }

    @GetMapping
    public LoginResponse getUserByKey(@RequestHeader("key") String key) {
        return userService.getUserLoginResponseByApiKey(key);
    }
}
