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
    public ProductPostComplaint createComplaint(User user, ProductPostComplaint complaint) {
        if (complaint.getProductPost() == null || complaint.getProductPost().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Complaint should have a ProductPost ID");
        }

        ProductPost productPost = productPostService.getProductPostById(complaint.getProductPost().getId());

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

    // TODO: Review a complain and send notification of reviewing to complainers - Tonni
}