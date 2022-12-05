package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.*;
import edu.baylor.cs.beargo.repository.ReviewAndRatingRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class ReviewAndRatingService {

    @Autowired
    ReviewAndRatingRepository reviewAndRatingRepository;

    @Autowired
    ContractService contractService;

    @Autowired
    NotificationService notificationService;

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

    /**
     * Checks if the logged user is either sender or traveler.
     * Checks if the sender has not already rated or reviewed.
     * Checks if the traveler has not already rated or reviewed.
     *
     * @param user       the authenticated user
     * @param rating     the rating
     * @param review     the review
     * @param contractId the contract Id
     * @return created review and rating
     */
    public ReviewAndRating createReviewAndRating(User user, Long contractId, Integer rating, String review) {
        Contract contract = contractService.getContractById(contractId);

        if (contract.getTraveler() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contract is not finalized yet");
        }

        if (contract.getDeliveryStatus() != DeliveryStatus.DELIVERED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product is not delivered yet");
        }

        if (!contract.getSender().getId().equals(user.getId())
                && !contract.getTraveler().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only sender or traveler can rate and review");
        }

        if (contract.getSender().getId().equals(user.getId())
                && contract.getReviewAndRatingBySender() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sender cannot review and rate the traveler twice");
        }

        if (contract.getTraveler().getId().equals(user.getId())
                && contract.getReviewAndRatingByTraveler() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Traveler cannot review and rate the traveler twice");
        }

        if (review == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Review cannot be empty");
        }

        if (rating == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rating cannot be empty");
        }

        ReviewAndRating reviewAndRating = new ReviewAndRating();
        reviewAndRating.setRating(rating);
        reviewAndRating.setReview(review);
        reviewAndRating.setReviewDateTime(LocalDateTime.now());

        if (contract.getSender().getId().equals(user.getId())) {
            reviewAndRating.setContractReviewedBySender(contract);
            reviewAndRating.setReviewedBy(contract.getSender());
            reviewAndRating.setReviewedTo(contract.getTraveler());
        } else {
            reviewAndRating.setContractReviewedByTraveler(contract);
            reviewAndRating.setReviewedBy(contract.getTraveler());
            reviewAndRating.setReviewedTo(contract.getSender());
        }

        /*
            Sending Notification to User
         */
        try {
            Notification notification = new Notification();
            notification.setNotificationMsg(user.getUsername() + " reviewed you - " + review);
            notificationService.saveNotification(reviewAndRating.getReviewedBy(), notification, reviewAndRating.getReviewedTo().getId());
        } catch (Exception e) {
            log.error("Can not send notification, reason: " + e);
        }

        return reviewAndRatingRepository.save(reviewAndRating);
    }
}
