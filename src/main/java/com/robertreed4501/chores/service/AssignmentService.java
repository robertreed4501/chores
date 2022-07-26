package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.Assignment;
import com.robertreed4501.chores.model.db.Chore;
import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.requests.AssignmentRequest;
import com.robertreed4501.chores.repository.AssignmentRepository;
import com.robertreed4501.chores.repository.ChoreRepository;
import com.robertreed4501.chores.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final ChoreRepository choreRepository;

    public List<Assignment> getAssignmentsByUserId(Long id) {
        try{
            User user = userRepository.findById(id).orElseThrow();
            return assignmentRepository.getAssignmentsByUser(user);
        }catch (NoSuchElementException e){
            return null;
        }
    }

    public void addAssignment(AssignmentRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Chore chore = choreRepository.findById(request.getChoreId()).orElseThrow();
        assignmentRepository.save(new Assignment(user, chore));
    }

}
