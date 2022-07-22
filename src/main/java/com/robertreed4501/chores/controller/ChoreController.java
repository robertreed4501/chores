package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.Chore;
import com.robertreed4501.chores.model.ChoreLevel;
import com.robertreed4501.chores.model.Frequency;
import com.robertreed4501.chores.model.Scope;
import com.robertreed4501.chores.service.ChoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
