package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.ReviewAndRating;
import edu.baylor.cs.beargo.repository.ReviewAndRatingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReviewAndRatingServiceTest {

    @Autowired
    ReviewAndRatingService reviewAndRatingService;

    @MockBean
    ReviewAndRatingRepository reviewAndRatingRepository;

    @Test
    void getReviewRatingById() {
        ReviewAndRating reviewAndRating = new ReviewAndRating();
        reviewAndRating.setReview("Test");
        reviewAndRating.setRating(4);
        reviewAndRating.setId(1L);
        Mockito.when(reviewAndRatingRepository.findById(1L)).thenReturn(Optional.of(reviewAndRating));
        assertEquals(reviewAndRating, reviewAndRatingService.getReviewRatingById(1L));
    }
}
