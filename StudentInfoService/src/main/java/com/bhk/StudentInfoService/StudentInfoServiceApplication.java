package com.bhk.StudentInfoService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ComponentScan("com.bhk")
//@EnableJpaRepositories("com.bhk.dao")
@EntityScan("com.bhk.dto") 
@Configuration
@CrossOrigin(origins = "http://localhost:8080")
public class StudentInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentInfoServiceApplication.class, args);
	}

}
