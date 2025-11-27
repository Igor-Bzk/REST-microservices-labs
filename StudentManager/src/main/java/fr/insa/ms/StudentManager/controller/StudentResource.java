package fr.insa.ms.StudentManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.StudentManager.model.Certificate;
import fr.insa.StudentManager.model.Student;
import fr.insa.ms.StudentManager.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentResource {
	
	private StudentRepository repository;

	@Autowired
	RestTemplate restTemplate;

	public StudentResource(StudentRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/{id}")
	private Student getStudent(@PathVariable long id){
		return repository.findById(id).get();
	}
	
	@PostMapping
	private Student postStudent(@RequestBody Student student) {
		return repository.save(student);
	}
	
	@GetMapping
	private List<Student> getAllStudents() {
		System.out.println("Getting students!");
		return repository.findAll();
	}

	@PutMapping
	private Student putStudent(@RequestBody Student student, @RequestParam("cert") String cert){
		Certificate certificate = restTemplate.getForObject("http://AuthentificationManager/certificate/"+ cert, Certificate.class);
		if (certificate != null && certificate.getStudent_id().getId() == student.getId()){
			return repository.save(student);
		} else {
			return null;
		}
	}
}