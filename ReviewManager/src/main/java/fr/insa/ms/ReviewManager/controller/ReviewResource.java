package fr.insa.ms.ReviewManager.controller;

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

import fr.insa.ReviewManager.model.Application;
import fr.insa.ReviewManager.model.Certificate;
import fr.insa.ReviewManager.model.Review;
import fr.insa.ms.ReviewManager.ReviewRepository;

@RestController
@RequestMapping("/review")
public class ReviewResource {
	
	@Autowired
	ReviewRepository repository;

	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/{id}")
	public Review getReview(@PathVariable long id) {
		return repository.findById(id).get();
	}
	
	@PostMapping
	public Review postReview(@RequestBody Review review, @RequestParam("cert") String cert) {
		Certificate certificate = restTemplate.getForObject("http://AuthentificationManager/certificate/"+cert, Certificate.class);
		Application application = restTemplate.getForObject("http://ApplicationManager/application/"+review.getApplication_id().getId(), Application.class);
		if (certificate != null && certificate.getStudent_id().getId() == application.getAsker_id().getId()){
			return repository.save(review);
		} else {
			return null;
		}
		
	}
	
	@GetMapping
	public List<Review> getAllReviews() {
		return repository.findAll();
	}

}
