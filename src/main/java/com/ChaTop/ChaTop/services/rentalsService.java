package com.ChaTop.ChaTop.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ChaTop.ChaTop.model.Rental;
import com.ChaTop.ChaTop.repository.RentalRepository;

@Service
public class rentalsService {

	private final RentalRepository rentalRepository;
	
	public rentalsService(RentalRepository rentalRepository) {
		this.rentalRepository = rentalRepository;
	}
	
	public List<Rental> getAllRentals(){
		return rentalRepository.findAll();
	}
}
