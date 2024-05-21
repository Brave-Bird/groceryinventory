package com.pad.springboot.groceryinventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GroceryinventoryApplication {

	public static void main(String[] args) {
		
		
		SpringApplication.run(GroceryinventoryApplication.class, args);
		/*
		 * SpringApplication.run(GroceryinventoryApplication.class, args);
		 * 
		 * 
		 * 
		 * SpringApplication app = new
		 * SpringApplication(GroceryinventoryApplication.class); app.addListeners(new
		 * ShutdownListener()); app.run(args);
		 */
	}

}
