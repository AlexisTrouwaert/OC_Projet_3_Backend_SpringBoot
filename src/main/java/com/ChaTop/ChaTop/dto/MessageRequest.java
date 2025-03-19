package com.ChaTop.ChaTop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageRequest {

	private String message;

    @JsonProperty("user_id")  // Permet à Jackson de mapper user_id du JSON à userId
    private Integer userId;

    @JsonProperty("rental_id")  // Permet à Jackson de mapper rental_id du JSON à rentalId
    private Integer rentalId;

    // Getters et Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRentalId() {
        return rentalId;
    }

    public void setRentalId(Integer rentalId) {
        this.rentalId = rentalId;
    }
}
