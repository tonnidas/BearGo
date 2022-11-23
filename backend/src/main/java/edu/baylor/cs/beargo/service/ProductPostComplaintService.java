package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.ProductPostComplaint;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.ProductPostComplaintRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductPostComplaintService {

    @Autowired
    ProductPostComplaintRepository productPostComplaintRepository;

    @Autowired
    ProductPostService productPostService;

    /**
     * Checks if the complained product post is valid
     * Checks if the complainer is not complaining against own product post
     * Adds logged user, product post, date to the complaint
     * Sets resolving attributes null as complaint is not resolved during creation
     *
     * @param user      the authenticated user
     * @param complaint the corresponding product post id
     * @return created contract
     */
    public ProductPostComplaint createComplaint(User user, ProductPostComplaint complaint, Long productPostId) {
        if (complaint == null || productPostId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Complaint or ProductPost ID cannot be null");
        } else if (complaint.getReason() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reason cannot be empty text");
        }

        ProductPost productPost = productPostService.getProductPostById(productPostId);

        if (productPost.getContract().getSender().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User cannot complaint against own ProductPost, update or delete ProductPost instead");
        }

        // overwrite with default values
        complaint.setComplainedBy(user);
        complaint.setProductPost(productPost);
        complaint.setComplainDate(LocalDate.now());
        complaint.setIsResolved(false);
        complaint.setResolvedBy(null);
        complaint.setResolveDate(null);

        return productPostComplaintRepository.save(complaint);

        // TODO: check complaints count for the product post and send notification - Tonni
    }

    /**
     * Checks if
     *
     * @param user          the authenticated user
     * @param productPostId the corresponding product post id
     * @return created contract
     */
    // TODO: Review a complain and send notification of reviewing to complainers - Tonni
    public Set<ProductPostComplaint> confirmComplaintReview(User user, Long productPostId) {
        ProductPost productPost = productPostService.getProductPostById(productPostId);
        Set<ProductPostComplaint> complaints = productPost.getComplaints();

        Set<User> users = new HashSet<>();

        for (ProductPostComplaint c : complaints) {
            c.setIsResolved(Boolean.TRUE);
            c.setResolveDate(LocalDate.now());
            c.setResolvedBy(user);
            users.add(c.getComplainedBy());
            productPostComplaintRepository.save(c);
        }

        return complaints;
//        return productPostComplaintRepository.save(complaint);
    }
}