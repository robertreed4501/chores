package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.db.Assignment;
import com.robertreed4501.chores.model.http.requests.AssignmentRequest;
import com.robertreed4501.chores.model.http.requests.DeleteAssignmentRequest;
import com.robertreed4501.chores.model.http.response.DashCard;
import com.robertreed4501.chores.service.AssignmentService;
import com.robertreed4501.chores.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@AllArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;
    private final DashboardService dashboardService;

    @PostMapping("/add")
    public List<DashCard> addAssignment(@RequestBody AssignmentRequest request){
        assignmentService.addAssignment(request);
        return dashboardService.getDashboard(request.getGroupId());
    }

    @GetMapping
    public List<Assignment> getAssignmentsByUserId(@RequestParam Long id){
        return assignmentService.getAssignmentsByUserId(id);
    }

    @PostMapping("/delete")
    public List<DashCard> deleteAssignment(@RequestBody DeleteAssignmentRequest request){
        return assignmentService.deleteAssignment(request);
    }
}
