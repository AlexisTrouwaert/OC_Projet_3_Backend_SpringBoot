package com.ChaTop.ChaTop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ChaTop.ChaTop.dto.MessageRequest;
import com.ChaTop.ChaTop.model.Message;
import com.ChaTop.ChaTop.services.messageService;

@RestController
@RequestMapping("/api/messages")
public class messageController {
	
	private final messageService messageService;
	
	public messageController(messageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping
	public ResponseEntity<Message> createMessage(@RequestBody MessageRequest request){
		Message newMessage = messageService.CreateMessage(request);
		return ResponseEntity.status(201).body(newMessage);
	}
}
