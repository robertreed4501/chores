package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.Receipt;
import com.robertreed4501.chores.repository.ReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public String addReceipt(Receipt receipt){
        receiptRepository.save(receipt);
        return "receipt added.";
    }

}
