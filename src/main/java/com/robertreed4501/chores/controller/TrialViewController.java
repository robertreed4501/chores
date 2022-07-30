package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.TrialView;
import com.robertreed4501.chores.model.http.response.Dashboard;
import com.robertreed4501.chores.repository.DashboardRepository;
import com.robertreed4501.chores.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/testing")
public class TrialViewController {

    UserRepository userRepository;
    DashboardRepository dashboardRepository;

    @GetMapping
    public List<TrialView> tryView(){

        List<TrialView> view = new ArrayList<>();
        for (int i = 3; i < 6; i++) {
            userRepository.getReferenceById((long) i).getAssignments().forEach(
                    assignment -> {
                        if (assignment.getReceipts().isEmpty()) {
                            view.add(new TrialView(
                                    assignment.getUser().getFirstName(),
                                    assignment.getChore().getName(),
                                    false
                                    )
                            );
                        } else {
                            view.add(new TrialView(
                                    assignment.getUser().getFirstName(),
                                    assignment.getChore().getName(),
                                    true
                                    )
                            );
                        }
                    }
            );
        }
        return view;
    }

    @GetMapping("/view")
    public List<Dashboard> viewDashboard (){
        return dashboardRepository.findAll();
    }
}
