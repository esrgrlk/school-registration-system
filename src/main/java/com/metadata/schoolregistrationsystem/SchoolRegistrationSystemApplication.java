package com.metadata.schoolregistrationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SchoolRegistrationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolRegistrationSystemApplication.class, args);
	}

}
