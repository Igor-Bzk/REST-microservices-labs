package fr.insa.ReviewManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String text;
	@ManyToOne
	@JoinColumn(name = "application_id", nullable = false)
	Application application_id;
	int rating;
	
	public Review(){}
	
	public Review(Integer id, String text, Application application_id, int rating) {
		super();
		this.id = id;
		this.text = text;
		this.application_id = application_id;
		this.rating = rating;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Application getApplication_id() {
		return application_id;
	}
	public void setApplication_id(Application application_id) {
		this.application_id = application_id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

}
