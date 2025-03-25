package com.ChaTop.ChaTop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.ChaTop.ChaTop.model.Rental;
import com.ChaTop.ChaTop.model.User;
import com.ChaTop.ChaTop.repository.RentalRepository;
import com.ChaTop.ChaTop.repository.UserRepository;
import com.ChaTop.ChaTop.services.rentalsService;

@RestController
@RequestMapping("/api/rentals")
public class rentalsController {

	//private static final String UPLOAD_DIR = "uploads/";
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
	    System.out.println(rentals);
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

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
        	try {
        		Path uploadDir = Paths.get("uploads");
        		if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
        		}
        		String fileName = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        		Path filePath = uploadDir.resolve(fileName);
        		Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        		
        		rental.setPicture(fileName);
        	} catch (IOException e) {
                return ResponseEntity.internalServerError().body("Erreur lors de l'enregistrement de l'image");
            }
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            rental.setPicture(fileName);
        }
        
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
	
	@GetMapping("/{id}")
	public ResponseEntity<Rental> findById(@PathVariable Integer id) {
		Rental rental = rentalService.findById(id);
		return ResponseEntity.ok(rental);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, String>> updateRental(
	        @PathVariable Integer id,
	        @RequestParam(required = false) String name,
	        @RequestParam(required = false) BigDecimal surface,
	        @RequestParam(required = false) BigDecimal price,
	        @RequestParam(required = false) String description,
	        @RequestParam(required = false) MultipartFile picture // Si tu veux gérer une image
	) {
	    Rental rental = rentalRepository.findById(id)
	            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Location non trouvée"));

	    if (name != null) rental.setName(name);
	    if (surface != null) rental.setSurface(surface);
	    if (price != null) rental.setPrice(price);
	    if (description != null) rental.setDescription(description);
	    
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Rental updated !");
	    rentalRepository.save(rental);

	    return ResponseEntity.ok(response);
	}
	
	
}
