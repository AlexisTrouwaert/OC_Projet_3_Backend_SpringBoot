package com.ChaTop.ChaTop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ChaTop.ChaTop.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long>{

	Optional<Rental> findById(Integer id);
}
