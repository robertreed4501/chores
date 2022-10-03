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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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

    public void addAssignment(AssignmentRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Chore chore = choreRepository.findById(request.getChoreId()).orElseThrow();
        assignmentRepository.save(new Assignment(user, chore));
    }

    public Assignment getAssignmentById(Long id){
        return assignmentRepository.findById(id).orElseThrow();
    }

    @Transactional
    public List<DashCard> deleteAssignment(DeleteAssignmentRequest request) {
        /*Assignment assignment = assignmentRepository.findById(request.getAssignmentId()).orElseThrow();
        assignment.setActive(false);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        assignmentRepository.setInactive(request.getAssignmentId());

        return dashboardService.getDashboard(request.getGroupId());
    }
}
