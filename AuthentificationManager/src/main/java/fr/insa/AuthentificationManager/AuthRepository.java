package fr.insa.AuthentificationManager;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.AuthentificationManager.model.Certificate;

public interface AuthRepository extends JpaRepository<Certificate,String>{

}
