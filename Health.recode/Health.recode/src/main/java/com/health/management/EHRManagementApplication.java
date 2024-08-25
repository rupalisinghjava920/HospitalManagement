package com.health.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EHRManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EHRManagementApplication.class, args);
		System.out.println("run success");
	}

}
