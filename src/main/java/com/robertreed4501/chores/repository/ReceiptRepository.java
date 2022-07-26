package com.robertreed4501.chores.repository;

import com.robertreed4501.chores.model.db.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {


}
