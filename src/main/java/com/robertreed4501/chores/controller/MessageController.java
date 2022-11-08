package com.robertreed4501.chores.controller;


import com.robertreed4501.chores.model.db.Message;
import com.robertreed4501.chores.model.http.requests.MessageRequest;
import com.robertreed4501.chores.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/messages")
@CrossOrigin
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public List<Message> getMessages (@RequestParam Long id){
        return messageService.getMessagesByUserId(id);
    }

    @PostMapping
    public String sendMessage (@RequestBody MessageRequest message) {
        messageService.sendMessage(message);
        return "message sent";
    }

}
