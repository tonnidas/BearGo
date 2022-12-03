package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.*;
import edu.baylor.cs.beargo.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
class ProductPostCommentServiceTest {

    @Autowired
    ProductPostRepository productPostRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductPostCommentRepository productPostCommentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ContractRepository contractRepository;

    ProductPostService productPostService;

    ProductPostCommentService productPostCommentService;

    @BeforeEach
    void setUp() {
        productPostService = new ProductPostService(addressRepository,
                productRepository,
                contractRepository,
                productPostRepository
        );

        productPostCommentService = new ProductPostCommentService(productPostCommentRepository,
                productPostRepository,
                productPostService
        );
    }

    @Test
    void getCommentById() {
    }

    @Test
    void getCommentsByProductPostId() {
    }

    @Test
    void createComment() {
        User user1 = userRepository.save(getSampleUser(1));
        User user2 = userRepository.save(getSampleUser(2));
        ProductPost productPost = productPostService.createProductPost(user1, getSampleProductPost());
        productPostCommentService.createComment(user2, getSampleProductPostComment(), productPost.getId());
        assert productPost.getComments() != null;
    }

    @Test
    void updateComment() {
    }

    private ProductPostComment getSampleProductPostComment() {
        ProductPostComment productPostComment = new ProductPostComment();
        productPostComment.setComment("nice");
        return productPostComment;
    }

    private ProductPost getSampleProductPost() {
        ProductPost productPost = new ProductPost();

        productPost.setDescription("Product Post 1");
        productPost.setExpectedPickupDate(LocalDate.now());
        productPost.setExpectedDeliveryDate(LocalDate.now());

        Address address = new Address();
        address.setStreet("1825");
        address.setCity("Waco");
        address.setState("TX");
        address.setZip("76706");
        address.setCountry("USA");
        productPost.setSource(address);
        productPost.setDestination(address);

        Product product = new Product();
        product.setDescription("Product 1");
        product.setWeight(10.0);
        productPost.setProduct(product);

        return productPost;
    }

    private User getSampleUser(int suffix) {
        User user = new User();
        user.setUsername("user" + suffix + "@beargo.com");
        user.setPassword("password");
        user.setFullname("user");
        user.setIsAdmin(false);
        return user;
    }
}