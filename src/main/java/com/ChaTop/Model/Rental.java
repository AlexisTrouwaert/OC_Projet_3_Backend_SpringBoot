package com.ChaTop.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.ChaTop.Model.Message;
import com.ChaTop.Model.User;

@Entity
@Table(name = "rentals")
public class Rental {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(length = 255)
	    private String name;

	    @Column(precision = 10, scale = 0)
	    private BigDecimal surface;

	    @Column(precision = 10, scale = 0)
	    private BigDecimal price;

	    @Column(length = 255)
	    private String picture;

	    @Column(length = 2000)
	    private String description;

	    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	    private LocalDateTime updatedAt;

	    @ManyToOne
	    @JoinColumn(name = "user_id", nullable = false)
	    private User user; // Relation avec la table users
	    
	    @OneToMany(mappedBy = "rental") // Relation avec Message
	    private List<Message> messages;

	    //Definition des getter / setter
	    
	    public Integer getId() { return id; }
	    public void setId(Integer id) { this.id = id; }

	    public String getName() { return name; }
	    public void setName(String name) { this.name = name; }

	    public BigDecimal getSurface() { return surface; }
	    public void setSurface(BigDecimal surface) { this.surface = surface; }

	    public BigDecimal getPrice() { return price; }
	    public void setPrice(BigDecimal price) { this.price = price; }

	    public String getPicture() { return picture; }
	    public void setPicture(String picture) { this.picture = picture; }

	    public String getDescription() { return description; }
	    public void setDescription(String description) { this.description = description; }

	    public LocalDateTime getCreatedAt() { return createdAt; }
	    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

	    public LocalDateTime getUpdatedAt() { return updatedAt; }
	    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

	    public User getUsers() { return user; }
	    public void setOwner(User user) { this.user = user; }
	    
	    public List<Message> getMessages() { return messages; }
	    public void setMessages(List<Message> messages) { this.messages = messages; }
	    
}
