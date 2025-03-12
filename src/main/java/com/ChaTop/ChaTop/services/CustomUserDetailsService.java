package com.ChaTop.ChaTop.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ChaTop.ChaTop.model.User;
import com.ChaTop.ChaTop.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Optional<User> userOpt = userRepository.findByName(name);
		
		if(userOpt.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		
		User user = userOpt.get();
		
		return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")) // Adapté à Spring Security
        );
	}
}
