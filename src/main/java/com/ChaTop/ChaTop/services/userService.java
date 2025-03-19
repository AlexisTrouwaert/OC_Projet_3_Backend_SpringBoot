package com.ChaTop.ChaTop.services;

import com.ChaTop.ChaTop.dto.UserDTO;
import com.ChaTop.ChaTop.model.User;
import com.ChaTop.ChaTop.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class userService {

	private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    
    public userService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public User registerUser(UserDTO userDTO) {
    	Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
    	if (existingUser.isPresent()) {
    		throw new IllegalArgumentException("Cet email existe déjà");
    	}
    	
    	User user = new User();
    	user.setEmail(userDTO.getEmail());
    	user.setName(userDTO.getName());
    	user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    	
    	return userRepository.save(user);
    }
    
    public User findById(Integer id) {
    	return userRepository.findById(id)
    			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Location non trouvée"));
    }
}
