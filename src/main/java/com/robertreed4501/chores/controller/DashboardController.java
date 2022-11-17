package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.enums.UserRole;
import com.robertreed4501.chores.model.http.response.DashCard;
import com.robertreed4501.chores.service.AuthService;
import com.robertreed4501.chores.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    private final DashboardService dashboardService;
    private final AuthService authService;

    @GetMapping
    public List<DashCard> viewDashboard (@RequestHeader("key") String key){

        final User user = authService.authorizeApiKey(key);
        if (user == null){
            return null;
        }
        else if(user.getAppUserRole() == UserRole.USER){
            return null;
        }
        else return dashboardService.getDashboard(user.getUserGroup().getId());
    }

    @GetMapping("/user")
    public DashCard getUserDashCard(@RequestParam Long userId){
        return dashboardService.getUserDashCard(userId);
    }
}
