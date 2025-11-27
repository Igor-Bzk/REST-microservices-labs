package fr.insa.ms.HelperManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.HelperManager.model.Certificate;
import fr.insa.HelperManager.model.Helper;
import fr.insa.ms.HelperManager.HelperRepository;

@RestController
@RequestMapping("/helper")
public class HelperResource {
	private HelperRepository  repository;

	@Autowired
	RestTemplate restTemplate;

	public HelperResource(HelperRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/{id}")
	private Helper getHelper(@PathVariable long id){
		return repository.findById(id).get();
	}
	
	@PostMapping
	private Helper postHelper(@RequestBody Helper helper, @RequestParam("cert") String cert) {
		Certificate certificate =  restTemplate.getForObject("http://AuthentificationManager/certificate/"+ cert, Certificate.class);
		if (certificate != null && certificate.getStudent_id().getId() == helper.getStudent_id().getId()){
			return repository.save(helper);
		} else {
			return null;
		}
	}
	
	@GetMapping
	private List<Helper> getAllHelpers(){
		return repository.findAll();
	}
}
