package fr.insa.ms.ApplicationManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@EntityScan(basePackages="fr.insa.ApplicationManager.model")
@SpringBootApplication
public class ApplicationManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationManagerApplication.class, args);
	}

}
