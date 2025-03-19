package com.ChaTop.ChaTop.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	// Definition des type dans ma table users
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		@Column(length = 255)
		private String email;
		
		@Column(length = 255)
		private String name;
		
		@Column(length = 255)
		private String password;
		
		@CreationTimestamp
		@Column(name = "created_at")
		private LocalDateTime created_at;
		
		@UpdateTimestamp
		@Column(name = "updated_at")
		private LocalDateTime updated_at;
		
		@OneToMany(mappedBy = "user") // Relation avec Rental
		@JsonIgnore
	    private List<Rental> rentals;
	    
	    @OneToMany(mappedBy = "user") // Relation avec Message
	    private List<Message> messages;
	    
	    @PrePersist
	    protected void onCreate() {
	        created_at = LocalDateTime.now();
	        updated_at = LocalDateTime.now();
	    }

	    @PreUpdate
	    protected void onUpdate() {
	    	updated_at = LocalDateTime.now();
	    }
	    
	    //Definition des getter / setter
		
		public Integer getId() {return id;}
		public void setId(Integer id) {this.id = id;}
		
		public String getEmail() {return email;}
		public void setEmail(String email) {this.email = email;}
		
		public String getName() {return name;}
		public void setName(String name) {this.name = name;}
		
		public String getPassword() {return password;}
		public void setPassword(String password) {this.password = password;}
		
		public LocalDateTime getCreatedAt() {return created_at;}
		public void setCreatedAt(LocalDateTime created_at) {this.created_at = created_at;}
		
		public LocalDateTime getUpdatedAt() {return updated_at;}
		public void setUpdatedAt(LocalDateTime updated_at) {this.updated_at = updated_at;}
		
		public List<Rental> getRentals() { return rentals; }
	    public void setRentals(List<Rental> rentals) { this.rentals = rentals; }

	    public List<Message> getMessages() { return messages; }
	    public void setMessages(List<Message> messages) { this.messages = messages; }
}
