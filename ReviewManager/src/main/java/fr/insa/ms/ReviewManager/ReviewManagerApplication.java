package fr.insa.ms.ReviewManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@EntityScan(basePackages="fr.insa.ReviewManager.model")
@SpringBootApplication
public class ReviewManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewManagerApplication.class, args);
	}

}
