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
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        List<Chore> chores = request.getChoreId().stream().map(choreID -> {
            //for each choreID, check if an inactive assignment exists
            //if it does, check if list size = multiplier
            //for multiplier - list size, add assignments, reactivate the rest
            return choreRepository.findById(choreID).orElseThrow();
        }).collect(Collectors.toList());

        chores.stream().forEach(chore -> {
            Optional<List<Assignment>> possibleInactiveAssignments = assignmentRepository.findByUserAndChoreAndActive(
                    user,
                    chore,
                    false
            );



            if (possibleInactiveAssignments.isPresent()){
                List<Assignment> inactiveAssignments = possibleInactiveAssignments.get();
                int numInactiveAssignments = inactiveAssignments.size();
                int multiplier = chore.getMultiplier();

                if (numInactiveAssignments >= multiplier) {
                    for (int i = 0; i < multiplier; i++){
                        inactiveAssignments.get(i).setActive(true);
                    }
                }
                else {
                    int newAssignmentsNeeded = multiplier - numInactiveAssignments;
                    for (int i = 0; i < newAssignmentsNeeded; i++){
                        assignmentRepository.save(new Assignment(user, chore));
                    }
                    inactiveAssignments.stream().forEach(assignment -> assignment.setActive(true));
                }
            }
        });
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

    @Transactional
    public String deleteAllAssignments(Long id) {
        assignmentRepository.setAllInactive(id);
        return "assignments deleted";
    }
}
