package com.robertreed4501.chores.service;

import com.robertreed4501.chores.model.db.Message;
import com.robertreed4501.chores.model.db.User;
import com.robertreed4501.chores.model.http.requests.MessageRequest;
import com.robertreed4501.chores.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

	private final MessageRepository messageRepository;
	private final UserService userService;

	public List<Message> getMessagesByUserId (Long id) {
		User user = userService.getUserById(id);
		List<Message> messages = messageRepository.findAllByUserIdToAndDeleted(user, false);
		messages.addAll(messageRepository.findAllByUserIdFromAndDeleted(user, false));
		return messages;
	}

	public void sendMessage(MessageRequest message) {
		messageRepository.save(new Message(
				userService.getUserById(message.getUserIdTo()),
				userService.getUserById(message.getUserIdFrom()),
				message.getSubject(),
				message.getBody(),
				LocalDateTime.now(),
				null,
				false,
				false));
	}
}
