package com.robertreed4501.chores.repository;

import com.robertreed4501.chores.model.db.Assignment;
import com.robertreed4501.chores.model.db.Chore;
import com.robertreed4501.chores.model.db.User;
import org.hibernate.annotations.SQLUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    public List<Assignment> getAssignmentsByUser(User user);

    @Override
    void deleteById(Long aLong);

    @Modifying
    @Query(value = "UPDATE Assignment SET active=0 WHERE id=?1")
    int setInactive(Long id);

    //@Query(value = "SELECT Assignment FROM Assignment WHERE user.id=?1 AND chore.id=?2")
    Optional<List<Assignment>> findByUserAndChoreAndActive(User user, Chore chore, boolean active);


}
