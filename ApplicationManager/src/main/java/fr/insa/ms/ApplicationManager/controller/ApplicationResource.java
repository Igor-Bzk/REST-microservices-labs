package fr.insa.ms.ApplicationManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.ApplicationManager.model.Application;
import fr.insa.ApplicationManager.model.ApplicationStatus;
import fr.insa.ApplicationManager.model.Certificate;
import fr.insa.ms.ApplicationManager.ApplicationRepository;
import jakarta.ws.rs.QueryParam;

@RestController
@RequestMapping("/application")
public class ApplicationResource {
	
	private ApplicationRepository  repository;

	@Autowired
	RestTemplate restTemplate;
	
	public ApplicationResource(ApplicationRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/{id}")
	private Application getApplication(@PathVariable long id) {
		return repository.findById(id).get();
	}
	
	@PostMapping
	private Application postApplication(@RequestBody Application application, @QueryParam("cert") String cert) {
		Certificate certificate = restTemplate.getForObject("http://AuthentificationManager/certificate/"+ cert, Certificate.class);
		if (certificate != null && certificate.getStudent_id().getId() == application.getAsker_id().getId()){
			if (application.getStatus() == null){
				application.setStatus(ApplicationStatus.waiting);
			}
			return repository.save(application);
		} else {
			return null;
		}
	}
	
	@GetMapping
	private List<Application> getAllApplications() {
		return repository.findAll();
	}

	@GetMapping("/status")
	private Application changeStatus(@QueryParam("id") long id, @QueryParam("val") String status){
		System.out.println(id + status);
		Application application = repository.findById(id).get();
		application.setStatus(ApplicationStatus.valueOf(status));
		return repository.save(application);
	}
}
