package com.duth.engapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EngAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(EngAppApplication.class, args);
	}

}
