package com.robertreed4501.chores.repository;

import com.robertreed4501.chores.model.db.Chore;
import com.robertreed4501.chores.model.db.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoreRepository extends JpaRepository<Chore, Long> {

    public List<Chore> getChoresByUserGroup(UserGroup group);

}
