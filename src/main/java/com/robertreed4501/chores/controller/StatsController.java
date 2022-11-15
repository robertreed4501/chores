package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.http.response.UserStatsEntry;
import com.robertreed4501.chores.service.StatsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stats")
@AllArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @GetMapping
    public List<UserStatsEntry> getUserStats (@RequestParam Long userId, @RequestParam Long numWeeks) {
        return statsService.getUserStats(userId, numWeeks);
    }
}
