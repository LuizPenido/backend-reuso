package com.inventorymanager.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.inventorymanager.project")
@EntityScan(basePackages = "com.inventorymanager.project.model")
public class InventorymanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventorymanagerApplication.class, args);
	}

}
