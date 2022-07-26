package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.db.Chore;
import com.robertreed4501.chores.model.http.requests.ChoreRequest;
import com.robertreed4501.chores.model.http.requests.UpdateChoreRequest;
import com.robertreed4501.chores.model.http.response.DashCard;
import com.robertreed4501.chores.service.ChoreService;
import com.robertreed4501.chores.service.DashboardService;
import com.robertreed4501.chores.service.UserGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/chores")
@CrossOrigin
public class ChoreController {

    private final ChoreService choreService;
    private final UserGroupService userGroupService;
    private final DashboardService dashboardService;

    @PostMapping
    public List<DashCard> addChore(@RequestBody ChoreRequest choreRequest){
        choreService.AddChore(
                new Chore(
                        choreRequest.getName(),
                        choreRequest.getDescription(),
                        choreRequest.getMultiplier(),
                        userGroupService.findById(choreRequest.getUserGroupId())
                )
        );
        return dashboardService.getDashboard(choreRequest.getUserGroupId());
    }

    @GetMapping("/mygroup")
    public List<Chore> getChoresByGroupId(@RequestParam Long id){
            return choreService.getChoresByGroupId(id);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteChore(@PathVariable Long id) {

        Long groupId = choreService.deleteChore(id);

        return new ResponseEntity("deleted", HttpStatus.OK);

    }

    @PostMapping("/loadsamples/{groupId}")
    public List<Chore> loadSampleChores (@PathVariable Long groupId){
        choreService.loadSampleChores(groupId);
        return getChoresByGroupId(groupId);
    }

    @PostMapping("/update")
    public List<Chore> updateChore (@RequestBody UpdateChoreRequest request) {
        choreService.updateChore(request);
        return choreService.getChoresByGroupId(request.getUserGroupId());
    }
}
