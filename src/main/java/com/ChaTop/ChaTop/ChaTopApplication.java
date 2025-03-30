package com.ChaTop.ChaTop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ChaTopApplication {

	public static void main(String[] args) {
		
		Dotenv dotenv = Dotenv.load();
		
        System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
        
		SpringApplication.run(ChaTopApplication.class, args);
	}

}
