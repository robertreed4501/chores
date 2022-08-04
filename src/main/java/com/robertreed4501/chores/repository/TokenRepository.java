package com.robertreed4501.chores.repository;

import com.robertreed4501.chores.model.db.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<ConfirmationToken, Long> {
}
