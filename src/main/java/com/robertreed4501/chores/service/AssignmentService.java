package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.Assignment;
import com.robertreed4501.chores.model.db.Chore;
import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.http.requests.AssignmentRequest;
import com.robertreed4501.chores.model.http.requests.DeleteAssignmentRequest;
import com.robertreed4501.chores.model.http.response.DashCard;
import com.robertreed4501.chores.repository.AssignmentRepository;
import com.robertreed4501.chores.repository.ChoreRepository;
import com.robertreed4501.chores.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final ChoreRepository choreRepository;
    private final DashboardService dashboardService;

    public List<Assignment> getAssignmentsByUserId(Long id) {
        try{
            User user = userRepository.findById(id).orElseThrow();
            List<Assignment> userAssignments = assignmentRepository.getAssignmentsByUser(user);
            return userAssignments;
        }catch (NoSuchElementException e){
            return null;
        }
    }

    @Transactional
    public void addAssignment(AssignmentRequest request) {
        User user = userRepository.findById(request.getUserId()).get();
        List<Assignment> assignments = new ArrayList<>();
        request.getChoreId().stream().forEach(choreId -> {
            Chore chore = choreRepository.findById(choreId).get();
            for ( int i = 0; i < chore.getMultiplier(); i++){
                assignments.add(new Assignment(user, chore));
            }

        });

        assignmentRepository.saveAll(assignments);
    }

    public Assignment getAssignmentById(Long id){
        return assignmentRepository.findById(id).orElseThrow();
    }

    @Transactional
    public List<DashCard> deleteAssignment(DeleteAssignmentRequest request) {
        assignmentRepository.setInactive(request.getAssignmentId());
        return dashboardService.getDashboard(request.getGroupId());
    }

    @Transactional
    public String deleteAllAssignments(Long id) {
        User user = userRepository.findById(id).get();
        List<Assignment> assignmentList = assignmentRepository
                .getAssignmentsByUserAndActiveAndStartIsBeforeAndEndIsAfter(user, true, LocalDateTime.now(), LocalDateTime.now());
        assignmentList.stream().forEach(assignment -> assignment.setActive(false));
        return "Assignments deleted";
    }

    @Transactional
    @Modifying
    public void deactivateAssignmentsByChoreId(Chore chore) {
        assignmentRepository.findAssignmentsByChore(chore)
                .stream()
                .forEach(assignment -> assignment.setActive(false));
    }


}
