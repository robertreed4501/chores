package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.RegistrationRequest;
import com.robertreed4501.chores.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@AllArgsConstructor
public class RegistrationController {


    private final RegistrationService registrationService;

    @GetMapping()
    public String test(){
        return "It is Working";
    }

    @PostMapping
    @CrossOrigin
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
}
