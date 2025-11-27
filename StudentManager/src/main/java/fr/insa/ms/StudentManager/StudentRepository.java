package fr.insa.ms.StudentManager;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.StudentManager.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
