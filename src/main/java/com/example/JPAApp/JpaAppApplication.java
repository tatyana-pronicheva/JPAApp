package com.example.JPAApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JpaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaAppApplication.class, args);
	}

}
