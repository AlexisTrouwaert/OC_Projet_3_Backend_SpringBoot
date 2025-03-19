package com.ChaTop.ChaTop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChaTop.ChaTop.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{

	Optional<Message> findById(Integer id);
}
