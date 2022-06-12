package com.Elchemy.Assignment.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ContactCrmAssignmentElchemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactCrmAssignmentElchemyApplication.class, args);
	}

}
