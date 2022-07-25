package com.robertreed4501.chores.service;

import com.robertreed4501.chores.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
@Setter
public class MessageService {

	private final MessageRepository messageRepository;

}
