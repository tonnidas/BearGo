package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.ReviewAndRating;
import edu.baylor.cs.beargo.repository.ReviewAndRatingRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class ReviewAndRatingService {

    @Autowired
    ReviewAndRatingRepository reviewAndRatingRepository;

    /**
     * @return all ReviewAndRatings
     */
    public List<ReviewAndRating> getReviewAndRatings() {
        return reviewAndRatingRepository.findAll();
    }

    /**
     * Checks if the review rating exists
     *
     * @param id the review rating id
     * @return review rating
     */
    public ReviewAndRating getReviewRatingById(Long id) {
        Optional<ReviewAndRating> optionalReviewAndRating = reviewAndRatingRepository.findById(id);
        if (optionalReviewAndRating.isPresent()) {
            return optionalReviewAndRating.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No review rating record exist for given id");
        }
    }
}
