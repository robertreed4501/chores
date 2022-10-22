package com.robertreed4501.chores.repository;

import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.db.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    UserGroup findByUser(User user);
}
