package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.db.Assignment;
import com.robertreed4501.chores.model.requests.AssignmentRequest;
import com.robertreed4501.chores.service.AssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@AllArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public String addAssignment(@RequestBody AssignmentRequest request){
        assignmentService.addAssignment(request);
        return "assignment added";
    }

    @GetMapping
    public List<Assignment> getAssignmentsByUserId(@RequestParam Long id){
        return assignmentService.getAssignmentsByUserId(id);
    }
}
