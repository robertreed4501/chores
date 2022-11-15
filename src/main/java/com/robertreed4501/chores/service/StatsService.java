package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.Assignment;
import com.robertreed4501.chores.model.db.Receipt;
import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.http.response.UserStatsEntry;
import jdk.jfr.Percentage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class StatsService {

    private final AssignmentService assignmentService;
    private final ReceiptService receiptService;
    private final UserService userService;

    public List<UserStatsEntry> getUserStats(Long id, Long numWeeks) {
        List<UserStatsEntry> statsEntries = new ArrayList<>();
        List<Assignment> assignments = assignmentService.getAssignmentsByUserId(id).stream().filter(assignment -> assignment.isActive()).collect(Collectors.toList());
        User user = userService.getUserById(id);


        for (int i = 0; i < numWeeks; i++) {
            int offset = i;

            LocalDateTime currentWeeksTime = LocalDateTime.of(LocalDate.now().minusWeeks(offset), LocalTime.MIDNIGHT);
            LocalDateTime start = currentWeeksTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDateTime end = currentWeeksTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusSeconds(1);


            List<Assignment> weeksAssignments = assignments.stream()
                    .filter(assignment -> (assignment.getStart().isBefore(LocalDateTime.now().minusWeeks(offset)) && assignment.getEnd().isAfter(LocalDateTime.now().minusWeeks(offset))))
                    .collect(Collectors.toList());

            Long numComplete = weeksAssignments.stream()
                    .filter(
                            assignment ->
                                    assignment.getDone())
                    .count();
            System.out.println(numComplete + " - numComplete after calculation");
            System.out.println(weeksAssignments.size() + " - assignments.size");
            Double percentDone;
            if (numComplete > 0) {
                percentDone = ((numComplete.doubleValue() / weeksAssignments.size()) * 100 );
            }
            else percentDone = 0.0;



            statsEntries.add(new UserStatsEntry(user.getId(), user.getFirstName(), user.getLastName(),percentDone, start.toLocalDate(), end.toLocalDate()));
        }




        return statsEntries;
    }
}
