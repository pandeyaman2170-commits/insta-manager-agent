package com.clipgrowth.clipgrowth_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableScheduling
@SpringBootApplication
public class ClipgrowthBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClipgrowthBackendApplication.class, args);
	}

}
