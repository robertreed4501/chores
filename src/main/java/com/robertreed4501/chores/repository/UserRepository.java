package com.robertreed4501.chores.repository;

import com.robertreed4501.chores.model.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    User findFirstByApiKey(String apiKey);

    //@Query(value = "SELECT * FROM user WHERE api_key = :apiKey", nativeQuery = true)
    User findUserByApiKey(String apiKey);

}
