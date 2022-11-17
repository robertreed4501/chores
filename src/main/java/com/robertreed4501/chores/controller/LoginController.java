package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.http.requests.LoginRequest;
import com.robertreed4501.chores.model.http.response.LoginResponse;
import com.robertreed4501.chores.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request){
        return loginService.login(request);
    }
}
