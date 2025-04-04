package com.ChaTop.ChaTop.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ChaTop.ChaTop.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByName(String name);
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findById(Integer id);
}
