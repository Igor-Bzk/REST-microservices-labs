package fr.insa.ms.HelperManager;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.HelperManager.model.Helper;

public interface HelperRepository extends JpaRepository<Helper,Long>{

}
