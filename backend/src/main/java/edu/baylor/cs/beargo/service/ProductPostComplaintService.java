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

    public ProductPostComplaint createComplaint(User user, ProductPostComplaint complaint) {
        if (complaint.getProductPost() == null || complaint.getProductPost().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Complaint should have a ProductPost ID");
        }

        ProductPost productPost = productPostService.findProductPostById(complaint.getProductPost().getId());

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

        // TODO: check complaints count for the product post and send notification
    }
}