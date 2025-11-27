package fr.insa.ms.GeneralManager.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.insa.GeneralManager.model.Application;
import fr.insa.GeneralManager.model.Certificate;
import fr.insa.GeneralManager.model.Helper;
import fr.insa.GeneralManager.model.Student;
import jakarta.ws.rs.QueryParam;

@RestController
@RequestMapping("/general")
public class ApplicationMatcher {
	
	@Autowired
	RestTemplate restTemplate;
	
	private List<String> stringToList(String tags) {
		return Arrays.asList(tags.split(tags));
	}
	
	private List<Student> matchHelpersByTags(List<String> applicationTags){
		Helper[] helpers = restTemplate.getForObject("http://HelperManager/helper", Helper[].class);
	    List<Student> students = Arrays.stream(helpers)
	        .map(helper -> helper.getStudent_id())
	        .toList();
	    return students.stream()
	        .filter(student -> applicationTags.stream().allMatch(tag -> stringToList(student.getSkills()).contains(tag)))
	        .toList();
	}
	
	@PostMapping("/submitApplication")
	private List<Student> submitApplication(@RequestBody Application application, @QueryParam("cert") String cert){
		Certificate certificate = restTemplate.getForObject("http://AuthentificationManager/certificate/"+cert, Certificate.class);
		if (certificate != null && certificate.getStudent_id().getId() == application.getAsker_id().getId()){
			Application postedApplication = restTemplate.postForObject("http://ApplicationManager/application?cert="+cert, application, Application.class);
			return matchHelpersByTags(stringToList(postedApplication.getKeywords()));
		} else {
			System.out.println("Authentification failed");
			return null;
		}
		
	}
}