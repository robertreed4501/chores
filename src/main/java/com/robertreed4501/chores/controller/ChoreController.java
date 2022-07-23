package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.*;
import com.robertreed4501.chores.service.ChoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/chores")
public class ChoreController {

    private final ChoreService choreService;

    @PostMapping
    public String addChore(@RequestBody ChoreRequest choreRequest){
        choreService.AddChore(new Chore(choreRequest.getName(), ChoreLevel.MEDIUM, Frequency.WEEKLY, Scope.PERSONAL));
        return "chore added";
    }

    @GetMapping
    public String getChores(){
        return choreService.getAllChores();
    }
}
