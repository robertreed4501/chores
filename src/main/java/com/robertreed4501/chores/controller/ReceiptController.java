package com.robertreed4501.chores.controller;

import com.robertreed4501.chores.model.db.Receipt;
import com.robertreed4501.chores.model.http.requests.ReceiptRequest;
import com.robertreed4501.chores.model.http.response.ReceiptResponse;
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
    public ReceiptResponse addReceipt(@RequestBody ReceiptRequest request){
        receiptService.addReceipt(request.getAssignmentId());
        return new ReceiptResponse("receipt added.",null);
    }

    @GetMapping
    public List<Receipt> getAllReceipts(){
        return receiptService.getAllReceipts();
    }

}
