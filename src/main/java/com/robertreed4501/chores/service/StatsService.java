package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.Assignment;
import com.robertreed4501.chores.model.db.Receipt;
import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.http.response.GroupStatsEntry;
import com.robertreed4501.chores.model.http.response.UserResponse;
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
        List<Assignment> assignments = assignmentService.getAssignmentsByUserId(id)
                .stream()
                .filter(assignment -> assignment.isActive())
                .collect(Collectors.toList());
        User user = userService.getUserById(id);
        System.out.println(assignments.size() + " - assignments.size after filtering out inactive");

        for (int i = 0; i < numWeeks; i++) {
            int offset = i;
            LocalDateTime currentWeeksTime = LocalDateTime.of(LocalDate.now().minusWeeks(offset), LocalTime.MIDNIGHT);
            LocalDateTime start = currentWeeksTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDateTime end = currentWeeksTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusSeconds(1);

            List<Assignment> weeksAssignments = assignments
                    .stream()
                    .filter(assignment ->
                            (assignment.getStart().isBefore(LocalDateTime.now().minusWeeks(offset))
                                    && assignment.getEnd().isAfter(LocalDateTime.now().minusWeeks(offset))))
                    .collect(Collectors.toList());

            Long numComplete = weeksAssignments
                    .stream()
                    .filter(assignment -> assignment.getDone())
                    .count();
            Double percentDone;

            if (numComplete > 0) {
                percentDone = ((numComplete.doubleValue() / weeksAssignments.size()) * 100 );
            }
            else percentDone = 0.0;

            statsEntries.add(new UserStatsEntry(user.getId(), user.getFirstName(), user.getLastName(),percentDone, start.toLocalDate(), end.toLocalDate()));
        }
        return statsEntries;
    }

    public List<GroupStatsEntry> getGroupStats(Long groupId) {
        List<GroupStatsEntry> groupStats = new ArrayList<>();
        List<UserResponse> users = userService.getUsersByGroupId(groupId);
        LocalDateTime currentTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        LocalDateTime prevMonday = currentTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDateTime nextSundayNight = currentTime.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).minusSeconds(1);

        users.stream().forEach(user -> {
            List<Assignment> allAssignments = assignmentService.getAssignmentsByUserId(user.getId())
                    .stream()
                    .filter(assignment -> assignment.isActive())
                    .collect(Collectors.toList());
            int numAllAssignments = allAssignments.size();
            int numAllCompletedAssignments = allAssignments
                    .stream()
                    .filter(assignment -> assignment.getDone())
                    .collect(Collectors.toList())
                    .size();
            int percentDoneAllTime;
            if (numAllCompletedAssignments > 0){
                percentDoneAllTime = ((numAllCompletedAssignments * 100) / numAllAssignments);
            }
            else percentDoneAllTime = 0;
            List<Assignment> thisWeeksAssignments = allAssignments
                    .stream()
                    .filter(assignment -> assignment.getStart().isBefore(LocalDateTime.now())
                    && assignment.getEnd().isAfter(LocalDateTime.now()))
                    .collect(Collectors.toList());
            int numWeeksAssignments = thisWeeksAssignments.size();
            int numCompletedThisWeek = thisWeeksAssignments
                    .stream()
                    .filter(assignment -> assignment.getDone())
                    .collect(Collectors.toList())
                    .size();
            int percentDoneThisWeek;
            if (numWeeksAssignments > 0){
                percentDoneThisWeek = ((numCompletedThisWeek * 100) / numWeeksAssignments);
            }
            else percentDoneThisWeek = 0;

            groupStats.add(new GroupStatsEntry(
                    user.getFirstName() + " " + user.getLastName(),
                    percentDoneThisWeek,
                    percentDoneAllTime
            ));
        });


        return groupStats;
    }
}
