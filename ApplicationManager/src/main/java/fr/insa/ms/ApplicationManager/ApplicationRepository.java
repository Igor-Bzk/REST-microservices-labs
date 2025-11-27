package fr.insa.ms.ApplicationManager;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.ApplicationManager.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{

}
