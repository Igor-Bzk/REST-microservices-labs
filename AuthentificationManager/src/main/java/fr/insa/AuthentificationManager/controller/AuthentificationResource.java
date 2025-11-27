package fr.insa.AuthentificationManager.controller;

import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.AuthentificationManager.model.Certificate;
import fr.insa.AuthentificationManager.model.Student;
import fr.insa.AuthentificationManager.AuthRepository;

@RestController
@RequestMapping("/certificate")
public class AuthentificationResource {
	private AuthRepository  repository;

	@Autowired
	private RestTemplate restTemplate;

	private static String generateCertificate(Student student) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(student.toString().getBytes());

			StringBuilder sb = new StringBuilder();
			for (byte b : hash) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException("Error generating certificate", e);
		}
	}

	public AuthentificationResource(AuthRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping("/register")
	private String postCertificate(@RequestBody Student student) {
		System.out.println("Attempting to register");
		Student postedStudent = restTemplate.postForObject("http://StudentManager/student", student, Student.class);
		String certif = generateCertificate(postedStudent);
		repository.save(new Certificate(certif, postedStudent));
		return certif;
	}
	
	@GetMapping("/{certificate}")
	private Certificate getCertificate(@PathVariable String certificate) {
		return repository.findById(certificate).orElse(null);
	}
}
