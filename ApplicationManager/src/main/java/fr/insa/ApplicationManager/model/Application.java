package fr.insa.ApplicationManager.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="applications")
public class Application {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@ManyToOne
	@JoinColumn(name = "asker_id", nullable = false)
	Student asker_id;
	String title;
	String description;
	String keywords;
	@Enumerated(EnumType.STRING)
	ApplicationStatus status;
	LocalDateTime date;
	
	public Application() {}
	
	public Application(Integer id, Student asker_id, String title, String description, String keywords,
			ApplicationStatus status, LocalDateTime date) {
		super();
		this.id = id;
		this.asker_id = asker_id;
		this.title = title;
		this.description = description;
		this.keywords = keywords;
		this.status = status;
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Student getAsker_id() {
		return asker_id;
	}
	public void setAsker_id(Student asker_id) {
		this.asker_id = asker_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
