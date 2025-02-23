package com.eclock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EntityScan("com.eclock.model")
@EnableJpaAuditing(auditorAwareRef = "auditingImpl")
public class EclockApplication {

	public static void main(String[] args) {
		SpringApplication.run(EclockApplication.class, args);
	}

}
