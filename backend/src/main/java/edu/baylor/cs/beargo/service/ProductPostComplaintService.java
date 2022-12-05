package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.ProductPostComplaint;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.ProductPostComplaintRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductPostComplaintService {

    @Autowired
    NotificationService notificationService;

    @Autowired
    ProductPostComplaintRepository productPostComplaintRepository;

    @Autowired
    ProductPostService productPostService;

    @Autowired
    AdminService adminService;

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

        for (ProductPostComplaint c : productPost.getComplaints()) {
            if (c.getComplainedBy().getId().equals(user.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You have already reported this post");
            }
        }

        // overwrite with default values
        complaint.setComplainedBy(user);
        complaint.setProductPost(productPost);
        complaint.setComplainDate(LocalDate.now());
        complaint.setIsResolved(false);
        complaint.setResolvedBy(null);
        complaint.setResolveDate(null);

        try {
            Notification notification = new Notification();
            notification.setNotificationMsg("You complain is recorded");
            notificationService.saveNotification(user, notification, user.getId());

            List<User> admins = adminService.getAdmins();
            for (User admin : admins) {
                Notification notification1 = new Notification();
                notification1.setNotificationMsg("A user has complained a product post");
                notificationService.saveNotification(admin, notification, admin.getId());
            }
        } catch (Exception e) {
            log.error("Can not send notification, reason: " + e);
        }
        return productPostComplaintRepository.save(complaint);
    }

    /**
     * @return a list of complaints
     */
    public List<ProductPostComplaint> getAllComplains() {
        List<ProductPostComplaint> complaintLis = productPostComplaintRepository.findAll();
        List<ProductPostComplaint> allComplaints = new ArrayList<>();
        for (ProductPostComplaint p : complaintLis) {
            if (!p.getIsResolved())
                allComplaints.add(p);
        }
        return allComplaints;
    }
}