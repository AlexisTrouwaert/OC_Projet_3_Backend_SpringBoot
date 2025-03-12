package com.ChaTop.ChaTop.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChaTop.ChaTop.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByName(String name);
	
	Optional<User> findByEmail(String email);
}
