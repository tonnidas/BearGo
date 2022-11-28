package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.dto.RatingDto;
import edu.baylor.cs.beargo.model.ReviewAndRating;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.ReviewAndRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/reviewAndRate")
    public ResponseEntity<ReviewAndRating> reviewAndRate(@AuthenticationPrincipal User user, @RequestParam Long contractId,
                                                         @RequestParam Integer rating, @RequestParam String review) {
        ReviewAndRating reviewAndRating = reviewAndRatingService.createReviewAndRating(user, contractId, rating, review);
        return new ResponseEntity<>(reviewAndRating, HttpStatus.OK);
    }

    @GetMapping("/getRatingByUserId")
    public ResponseEntity<RatingDto> getRatingByUserId(@RequestParam(value = "userId") Long userId) {
        return new ResponseEntity<>(reviewAndRatingService.getRatingByUserId(userId), HttpStatus.OK);
    }
}
