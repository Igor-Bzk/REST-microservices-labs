package fr.insa.ms.ReviewManager;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.insa.ReviewManager.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

}
