package com.ChaTop.ChaTop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ChaTop.ChaTop.dto.MessageRequest;
import com.ChaTop.ChaTop.services.messageService;

@RestController
@RequestMapping("/api/messages")
public class messageController {
	
	private final messageService messageService;
	
	public messageController(messageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping
	public ResponseEntity<Map<String, String>> createMessage(@RequestBody MessageRequest request){
		messageService.CreateMessage(request);
		Map<String, String> response = new HashMap<>();
	    response.put("message", "Message sent with success");

	    return ResponseEntity.status(201).body(response);
	}
}
