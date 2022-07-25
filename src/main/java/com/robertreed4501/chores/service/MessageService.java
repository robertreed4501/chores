package com.robertreed4501.chores.service;

import com.robertreed4501.chores.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageService {

	private final MessageRepository messageRepository;

}
