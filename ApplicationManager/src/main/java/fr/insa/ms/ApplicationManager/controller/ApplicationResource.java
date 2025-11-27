package fr.insa.ms.ApplicationManager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.ApplicationManager.model.Application;
import fr.insa.ApplicationManager.model.Certificate;
import fr.insa.ms.ApplicationManager.ApplicationRepository;
import jakarta.ws.rs.QueryParam;

@RestController
@RequestMapping("/application")
public class ApplicationResource {
	
	private ApplicationRepository  repository;
	RestTemplate restTemplate = new RestTemplate();
	
	public ApplicationResource(ApplicationRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/{id}")
	private Application getApplication(@PathVariable long id) {
		return repository.findById(id).get();
	}
	
	@PostMapping
	private Application postApplication(@RequestBody Application application, @QueryParam("cert") String cert) {
		Certificate certificate = restTemplate.getForObject("http://AuthentificationManager/"+application.getAsker_id().getId(), Certificate.class);
		if (certificate != null && certificate.getStudent_id().getId() == application.getAsker_id().getId()){
			return repository.save(application);
		} else {
			return null;
		}
	}
	
	@GetMapping
	private List<Application> getAllApplications() {
		return repository.findAll();
	}
}
