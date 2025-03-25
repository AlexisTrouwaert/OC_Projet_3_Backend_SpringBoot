package com.ChaTop.ChaTop.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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

	    @CreationTimestamp
	    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	    @JsonProperty("created_at")
	    private LocalDateTime createdAt;

	    @UpdateTimestamp
	    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	    @JsonProperty("updated_at")
	    private LocalDateTime updatedAt;

	    @ManyToOne
	    @JsonProperty("owner_id")
	    @JoinColumn(name = "owner_id", referencedColumnName = "id")
	    private User owner; // Relation avec la table users
	    
	    
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

	    public String getPicture()  {return (picture != null) ? "http://localhost:9000/" + picture : null;}
	    public void setPicture(String picture) { this.picture = picture; }

	    public String getDescription() { return description; }
	    public void setDescription(String description) { this.description = description; }

	    public LocalDateTime getCreatedAt() { return createdAt; }
	    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

	    public LocalDateTime getUpdatedAt() { return updatedAt; }
	    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

	    public Integer getOwner() { return owner != null ? owner.getId() : null; }
	    public void setOwner(User owner) { this.owner = owner; }
	    
	    public List<Message> getMessages() { return messages; }
	    public void setMessages(List<Message> messages) { this.messages = messages; }
	    
}
