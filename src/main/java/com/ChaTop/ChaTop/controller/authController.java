package com.ChaTop.ChaTop.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ChaTop.ChaTop.dto.LoginRequest;
import com.ChaTop.ChaTop.dto.UserDTO;
import com.ChaTop.ChaTop.model.User;
import com.ChaTop.ChaTop.repository.UserRepository;
import com.ChaTop.ChaTop.services.JWTServices;
import com.ChaTop.ChaTop.services.userService;

@RestController
@RequestMapping("/api/auth")
public class authController {

	private final userService userService;
	private final JWTServices jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public authController(userService userService, JWTServices jwtService, UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            User newUser = userService.registerUser(userDTO);
            
         // Authentifie l'utilisateur
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    newUser.getEmail(),
                    newUser.getPassword(),
                    Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
            );

            // Génère le token JWT
            String token = jwtService.generateToken(authentication);
            
            return ResponseEntity.ok(Map.of(
                    "message", "Utilisateur enregistré avec succès !" + newUser,
                    "token", token
                ));
        } catch (IllegalArgumentException e) {
        	return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
    	if(authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    	}
    	
    	String email = authentication.getName();
    	Optional<User> user = userRepository.findByEmail(email);
    	
    	return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
    
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        String token = jwtService.generateToken(authentication);

        return Map.of("token", token);
    }
}
