package com.ChaTop.ChaTop.controller;

import com.ChaTop.ChaTop.dto.UserDTO;
import com.ChaTop.ChaTop.model.User;
import com.ChaTop.ChaTop.services.userService;

import java.util.Collections;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.ChaTop.ChaTop.services.JWTServices;

@RestController
@RequestMapping("/api/auth")
public class authController {

	private final userService userService;
	private final JWTServices jwtService;
    private final AuthenticationManager authenticationManager;

    public authController(userService userService, JWTServices jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        try {
            User newUser = userService.registerUser(userDTO);
            
         // Authentifie l'utilisateur
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    newUser.getEmail(), // Utilisation de l'email
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
}
