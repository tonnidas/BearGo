package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.ReviewAndRating;
import edu.baylor.cs.beargo.service.ReviewAndRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reviewAndRating")
public class ReviewAndRatingController {

    @Autowired
    ReviewAndRatingService reviewAndRatingService;

    @GetMapping("/getAllReviewAndRating")
    public ResponseEntity<List<ReviewAndRating>> getAllReviewAndRating() {
        List<ReviewAndRating> reviewAndRatings = reviewAndRatingService.getReviewAndRatings();
        return new ResponseEntity<>(reviewAndRatings, HttpStatus.OK);
    }
}
