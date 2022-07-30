package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.db.Receipt;
import com.robertreed4501.chores.service.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    @PostMapping
    public String addReceipt(@RequestBody Long assignmentId){
        receiptService.addReceipt(assignmentId);
        return "receipt added.";
    }

    @GetMapping
    public List<Receipt> getAllReceipts(){
        return receiptService.getAllReceipts();
    }

}
