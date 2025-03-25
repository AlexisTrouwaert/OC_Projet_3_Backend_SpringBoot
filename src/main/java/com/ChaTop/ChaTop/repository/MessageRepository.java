package com.ChaTop.ChaTop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ChaTop.ChaTop.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	Optional<Message> findById(Integer id);
}
