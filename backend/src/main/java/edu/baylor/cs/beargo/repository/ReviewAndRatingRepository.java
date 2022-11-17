package edu.baylor.cs.beargo.repository;

import edu.baylor.cs.beargo.model.ReviewAndRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewAndRatingRepository extends JpaRepository<ReviewAndRating, Long> {
}
