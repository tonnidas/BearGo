package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.ProductPostComplaint;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import static edu.baylor.cs.beargo.service.SampleModels.*;

@DataJpaTest
class AdminServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductPostComplaintRepository productPostComplaintRepository;

    @Autowired
    ProductPostRepository productPostRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    NotificationRepository notificationRepository;

    AdminService adminService;
    ProductPostService productPostService;

    @BeforeEach
    void setUp() {
        productPostService = new ProductPostService(addressRepository,
                productRepository,
                contractRepository,
                productPostRepository
        );

        adminService = new AdminService(userRepository,
                productPostComplaintRepository,
                productPostRepository,
                productPostService
        );
    }

    @Test
    void getAdmins() {
        User admin = userRepository.save(getSampleAdmin(1));
        User user = userRepository.save(getSampleUser(1));
        List<User> admins = adminService.getAdmins();
        assert adminService.getAdmins().size() == 1;
        assert Objects.equals(admins.get(0).getId(), admin.getId());
    }

    @Test
    void getUsers() {
        User admin = userRepository.save(getSampleAdmin(1));
        User user = userRepository.save(getSampleUser(1));
        List<User> users = adminService.getUsers();
        assert adminService.getUsers().size() == 1;
        assert Objects.equals(users.get(0).getId(), user.getId());
    }

    @Test
    void getAllUsers() {
        User admin = userRepository.save(getSampleAdmin(1));
        User user = userRepository.save(getSampleUser(2));
        List<User> allUsers = adminService.getAllUsers();
        assert adminService.getAllUsers().size() == 2;
        assert Objects.equals(allUsers.get(0).getId(), admin.getId());
        assert Objects.equals(allUsers.get(1).getId(), user.getId());
    }

    @Test
    void getAdminById() {
        User admin = userRepository.save(getSampleAdmin(1));
        User searchedAdmin = adminService.getAdminById(admin.getId());
        assert searchedAdmin != null;
        assert searchedAdmin.getId().equals(admin.getId());
    }

    @Test
    void promoteUser() {
        User user = userRepository.save(getSampleUser(1));
        User promotedUser = adminService.promoteUser(user.getId());
        assert promotedUser.getId().equals(user.getId());
        assert promotedUser.getIsAdmin();
    }

    @Test
    void resolvePostComplaint() {
        User user1 = userRepository.save(getSampleUser(1));
        User admin = userRepository.save(getSampleAdmin(1));

        ProductPost createdProductPost = productPostService.createProductPost(user1, getSampleProductPost());

        ProductPostComplaint complaint = getSamplerProductPostComplaint();
        complaint.setProductPost(createdProductPost);
        createdProductPost.getComplaints().add(complaint);
        productPostComplaintRepository.save(complaint);
        productPostRepository.save(createdProductPost);

        Set<ProductPostComplaint> complaints = adminService.reviewProductPostComplaint(admin, createdProductPost.getId(), "blocked");
        assert complaints.size() == 1;

        for (ProductPostComplaint c : complaints) {
            assert c.getResolvedBy().getId().equals(admin.getId());
            assert c.getIsResolved().equals(true);
        }

        ProductPost blockedProductPost = productPostService.getProductPostById(createdProductPost.getId());
        assert blockedProductPost.isBlocked();
    }

    @Test
    void getReportedProductPosts() {
        User user1 = userRepository.save(getSampleUser(1));

        ProductPost createdProductPost = productPostService.createProductPost(user1, getSampleProductPost());

        ProductPostComplaint complaint = getSamplerProductPostComplaint();
        complaint.setProductPost(createdProductPost);
        createdProductPost.getComplaints().add(complaint);
        productPostComplaintRepository.save(complaint);
        productPostRepository.save(createdProductPost);

        List<ProductPost> productPosts = adminService.getReportedProductPosts(0);
        assert productPosts.size() == 1;
        assert productPosts.get(0).getId().equals(createdProductPost.getId());
    }
}