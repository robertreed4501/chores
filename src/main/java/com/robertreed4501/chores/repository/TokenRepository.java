package com.robertreed4501.chores.repository;

import com.robertreed4501.chores.model.db.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
