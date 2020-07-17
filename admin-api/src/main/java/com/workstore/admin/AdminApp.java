package com.workstore.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.workstore.common.*")
@EnableJpaRepositories("com.workstore.common.*")
public class AdminApp {
	public static void main(String[] args) {
		SpringApplication.run(AdminApp.class, args);
	}
}
