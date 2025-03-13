package com.ChaTop.ChaTop.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ChaTop.ChaTop.model.Rental;
import com.ChaTop.ChaTop.model.User;
import com.ChaTop.ChaTop.repository.RentalRepository;
import com.ChaTop.ChaTop.services.rentalsService;
import com.ChaTop.ChaTop.repository.UserRepository;

@RestController
@RequestMapping("/api/rentals")
public class rentalsController {

	private final rentalsService rentalService;
	private final RentalRepository rentalRepository;
	private final UserRepository userRepository;

	
	public rentalsController(rentalsService rentalService, RentalRepository rentalRepository, UserRepository userRepository) {
        this.rentalService = rentalService;
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }
	
	@GetMapping
	public ResponseEntity<Map<String, List<Rental>>> getAllRentals() {
	    List<Rental> rentals = rentalService.getAllRentals();
	    Map<String, List<Rental>> response = new HashMap<>();
	    response.put("rentals", rentals);
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createRental(
            @RequestParam("name") String name,
            @RequestParam("surface") BigDecimal surface,
            @RequestParam("price") BigDecimal price,
            @RequestParam("description") String description,
            @RequestParam(value = "picture", required = false) MultipartFile file) {

        // 🔥 1. Récupérer l'utilisateur authentifié
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // L'email de l'utilisateur depuis le token

        // 🔥 2. Chercher l'utilisateur en base
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // 🔥 3. Création de l'entité Rental avec owner_id
        Rental rental = new Rental();
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);
        rental.setDescription(description);
        rental.setOwner(user);
        rental.setCreatedAt(LocalDateTime.now());
        rental.setUpdatedAt(LocalDateTime.now());

        // Gérer l'image si elle est présente
        if (file != null && !file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            rental.setPicture(fileName);
        }

        // Sauvegarde en base
        Rental savedRental = rentalRepository.save(rental);

        return ResponseEntity.ok(Map.of(
        	    "id", savedRental.getId(),
        	    "name", savedRental.getName(),
        	    "surface", savedRental.getSurface(),
        	    "price", savedRental.getPrice(),
        	    "picture", savedRental.getPicture(),
        	    "description", savedRental.getDescription(),
        	    "createdAt", savedRental.getCreatedAt(),
        	    "updatedAt", savedRental.getUpdatedAt()
        	));
    }
}
