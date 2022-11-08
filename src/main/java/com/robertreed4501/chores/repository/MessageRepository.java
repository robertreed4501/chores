package com.robertreed4501.chores.repository;


import com.robertreed4501.chores.model.db.Message;
import com.robertreed4501.chores.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByUserIdTo (Long id);

    List<Message> findAllByUserIdFrom (Long id);

    List<Message> findAllByUserIdToAndDeleted (User user, Boolean deleted);

    List<Message> findAllByUserIdFromAndDeleted (User user, Boolean deleted);

}
