package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.Receipt;
import com.robertreed4501.chores.repository.ReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReceiptService {

    private final AssignmentService assignmentService;
    private final ReceiptRepository receiptRepository;

    public String addReceipt(Long assignmentId){
        receiptRepository.save(
                new Receipt(
                        assignmentService.getAssignmentById(assignmentId),
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
