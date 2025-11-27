package fr.insa.HelperManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="helpers")
public class Helper {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@ManyToOne
	@JoinColumn(name = "asker_id", nullable = false)
	Student student_id;
	
	public Helper(){}
	
	public Helper(Integer id, Student student_id) {
		super();
		this.id = id;
		this.student_id = student_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Student getStudent_id() {
		return student_id;
	}
	public void setStudent_id(Student student_id) {
		this.student_id = student_id;
	}
}
