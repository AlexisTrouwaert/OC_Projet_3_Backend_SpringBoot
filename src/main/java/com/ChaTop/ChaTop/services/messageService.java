package com.ChaTop.ChaTop.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ChaTop.ChaTop.dto.MessageRequest;
import com.ChaTop.ChaTop.model.Message;
import com.ChaTop.ChaTop.model.Rental;
import com.ChaTop.ChaTop.model.User;
import com.ChaTop.ChaTop.repository.MessageRepository;
import com.ChaTop.ChaTop.repository.RentalRepository;
import com.ChaTop.ChaTop.repository.UserRepository;

@Service
public class messageService {

	private final UserRepository userRepository;
	private final MessageRepository messageRepository;
	private final RentalRepository rentalRepository;
	
	public messageService(UserRepository userRepository, MessageRepository messageRepository, RentalRepository rentalRepository) {
		this.messageRepository = messageRepository;
		this.userRepository = userRepository;
		this.rentalRepository = rentalRepository;
	}
	
	public Message CreateMessage(MessageRequest request) {
		Rental rental = rentalRepository.findById(request.getRentalId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Location non trouvée"));
		
		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur non trouvé"));
		
		Message message = new Message();
		message.setRental(rental);
		message.setUser(user);
		message.setMessage(request.getMessage());
		
		return messageRepository.save(message);
	}
}
