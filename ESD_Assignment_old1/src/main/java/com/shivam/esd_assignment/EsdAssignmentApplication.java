package com.shivam.esd_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class EsdAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsdAssignmentApplication.class, args);
	}

}
