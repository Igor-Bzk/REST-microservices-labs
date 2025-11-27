package fr.insa.StudentManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="certificates")
public class Certificate {
    @Id
    String certificate;
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	Student student_id;
	
	public Certificate() {}

	public Certificate(String certificate, Student student_id) {
		super();
		this.certificate = certificate;
		this.student_id = student_id;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public Student getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Student student_id) {
		this.student_id = student_id;
	}
}