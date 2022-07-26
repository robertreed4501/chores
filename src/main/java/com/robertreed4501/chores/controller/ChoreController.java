package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.db.Chore;
import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.enums.ChoreLevel;
import com.robertreed4501.chores.model.enums.Frequency;
import com.robertreed4501.chores.model.enums.Scope;
import com.robertreed4501.chores.model.requests.ChoreRequest;
import com.robertreed4501.chores.service.ChoreService;
import com.robertreed4501.chores.service.UserGroupService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/chores")
public class ChoreController {

    private final ChoreService choreService;
    private final UserGroupService userGroupService;

    @PostMapping
    public String addChore(@RequestBody ChoreRequest choreRequest){
        choreService.AddChore(
                new Chore(
                        choreRequest.getName(),
                        ChoreLevel.MEDIUM,
                        Frequency.WEEKLY,
                        Scope.PERSONAL,
                        userGroupService.findById(choreRequest.getUserGroupId())
                )
        );
        return "chore added";
    }

    @GetMapping("/mygroup")
    public List<Chore> getChoresByGroupId(@RequestParam Long id){
            return choreService.getChoresByGroupId(id);

    }


}
