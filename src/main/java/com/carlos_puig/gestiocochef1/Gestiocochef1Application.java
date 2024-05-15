package com.carlos_puig.gestiocochef1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.carlos_puig.gestiocochef1.backend.business.model")
public class Gestiocochef1Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Gestiocochef1Application.class, args);
	}

}
