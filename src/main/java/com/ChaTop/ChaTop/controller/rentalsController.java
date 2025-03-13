package com.ChaTop.ChaTop.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ChaTop.ChaTop.dto.rentalDTO;
import com.ChaTop.ChaTop.model.Rental;
import com.ChaTop.ChaTop.repository.RentalRepository;
import com.ChaTop.ChaTop.repository.UserRepository;
import com.ChaTop.ChaTop.services.rentalsService;

@RestController
@RequestMapping("/api/rentals")
public class rentalsController {

	private final rentalsService rentalService;
	private final UserRepository userRepository;
	private final RentalRepository rentalRepository;

	
	public rentalsController(rentalsService rentalService, UserRepository userRepository, RentalRepository rentalRepository) {
        this.rentalService = rentalService;
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }
	
	@GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return ResponseEntity.ok(rentals);
    }
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> createRental(
	        @RequestPart("rental") rentalDTO rentalDTO, // 📌 Le DTO envoyé en JSON
	        @RequestPart(value = "picture", required = false) MultipartFile file) { // 📌 L'image en fichier

	    // Mapper DTO → Entité
	    Rental rental = new Rental();
	    rental.setName(rentalDTO.getName());
	    rental.setSurface(rentalDTO.getSurface());
	    rental.setPrice(rentalDTO.getPrice());
	    rental.setDescription(rentalDTO.getDescription());
	    rental.setCreatedAt(LocalDateTime.now());
	    rental.setUpdatedAt(LocalDateTime.now());

	    // Si un fichier est fourni, stocker le nom
	    if (file != null && !file.isEmpty()) {
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	        rental.setPicture(fileName);
	    }

	    // Sauvegarde en base
	    Rental savedRental = rentalRepository.save(rental);
	    return ResponseEntity.ok(savedRental);
	}
}
