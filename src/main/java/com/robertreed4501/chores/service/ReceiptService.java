package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.Assignment;
import com.robertreed4501.chores.model.db.Receipt;
import com.robertreed4501.chores.repository.ReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class ReceiptService {

    private final AssignmentService assignmentService;
    private final ReceiptRepository receiptRepository;

    @Modifying
    public String addReceipt(Long assignmentId){
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        assignment.setDone(true);
        receiptRepository.save(
                new Receipt(
                        assignment,
                        LocalDateTime.now(),
                        false,
                        false
                )
        );
        return "receipt added.";
    }

    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

}
