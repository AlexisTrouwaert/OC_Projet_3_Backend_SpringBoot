package com.ChaTop.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.ChaTop.Model.Rental;
import com.ChaTop.Model.User;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 2000)
    private String message;
	
	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Relation avec la table users
    
    @ManyToOne
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental; // Relation avec la table rentals
    
  //Definition des getter / setter
    
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Rental getRental() { return rental; }
    public void setRental(Rental rental) { this.rental = rental; }
}
