package com.ChaTop.ChaTop.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
	
	public Rental findById(Integer id) {
		return rentalRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Location non trouvée"));
	}

}
