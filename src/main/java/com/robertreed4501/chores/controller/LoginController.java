package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.http.requests.LoginRequest;
import com.robertreed4501.chores.model.http.response.LoginResponse;
import com.robertreed4501.chores.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request){
        return loginService.Login(request);
    }
}
