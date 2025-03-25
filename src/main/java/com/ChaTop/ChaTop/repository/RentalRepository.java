package com.ChaTop.ChaTop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ChaTop.ChaTop.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer>{

	Optional<Rental> findById(Integer id);
}
