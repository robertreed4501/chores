package com.robertreed4501.chores.repository;

import com.robertreed4501.chores.model.db.Assignment;
import com.robertreed4501.chores.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    public List<Assignment> getAssignmentsByUser(User user);
}
