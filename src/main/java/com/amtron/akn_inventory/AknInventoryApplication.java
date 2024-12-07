package com.amtron.akn_inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AknInventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AknInventoryApplication.class, args);
	}

}
