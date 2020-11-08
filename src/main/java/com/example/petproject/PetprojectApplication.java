package com.example.petproject;

import com.example.petproject.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PetprojectApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(PetprojectApplication.class);

	public static void main(String[] args) {

		System.out.println(AppConfig.helloString);

		SpringApplication.run(PetprojectApplication.class, args);
	}

}
