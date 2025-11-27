package fr.insa.HelperManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	String surname;
	String email;
	String school;
	String course;
	String skills;
	String availabilities;
	Integer year;

	public Student(){}
	
	public Student(Integer id, String name, String surname, String email, String school, String course, String skills,
			String availabilities, Integer year) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.school = school;
		this.course = course;
		this.skills = skills;
		this.availabilities = availabilities;
		this.year = year;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getavailabilities() {
		return availabilities;
	}
	public void setavailabilities(String availabilities) {
		this.availabilities = availabilities;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}

}
