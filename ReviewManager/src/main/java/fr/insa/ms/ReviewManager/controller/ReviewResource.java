package fr.insa.ms.ReviewManager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insa.ReviewManager.model.Review;
import fr.insa.ms.ReviewManager.ReviewRepository;

@RestController
@RequestMapping("/review")
public class ReviewResource {
	
	ReviewRepository repository;
	
	@GetMapping("/{id}")
	public Review getReview(@PathVariable long id) {
		return repository.findById(id).get();
	}
	
	@PostMapping
	public Review postReview(@RequestBody Review review) {
		return repository.save(review);
	}
	
	@GetMapping
	public List<Review> getAllReviews() {
		return repository.findAll();
	}

}
