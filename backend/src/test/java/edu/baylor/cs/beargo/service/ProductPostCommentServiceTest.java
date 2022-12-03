package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.ProductPostComment;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Objects;

import static edu.baylor.cs.beargo.service.SampleModels.*;

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
        User user1 = userRepository.save(getSampleUser(1));
        User user2 = userRepository.save(getSampleUser(2));
        ProductPost productPost = productPostService.createProductPost(user1, getSampleProductPost());
        ProductPostComment productPostComment = productPostCommentService.createComment(user2, getSampleProductPostComment(), productPost.getId());
        assert productPostCommentService.getCommentById(productPostComment.getId()) != null;
    }

    @Test
    void getCommentsByProductPostId() {
        User user1 = userRepository.save(getSampleUser(1));
        User user2 = userRepository.save(getSampleUser(2));
        ProductPost productPost = productPostService.createProductPost(user1, getSampleProductPost());
        ProductPostComment productPostComment = productPostCommentService.createComment(user2, getSampleProductPostComment(), productPost.getId());
        List<ProductPostComment> searchedPostComments = productPostCommentService.getCommentsByProductPostId(productPost.getId());
        assert searchedPostComments.size() == 1;
        assert Objects.equals(searchedPostComments.get(0).getId(), productPostComment.getId());
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
        User user1 = userRepository.save(getSampleUser(1));
        User user2 = userRepository.save(getSampleUser(2));
        ProductPost productPost = productPostService.createProductPost(user1, getSampleProductPost());
        ProductPostComment comment = productPostCommentService.createComment(user2, getSampleProductPostComment(), productPost.getId());
        ProductPostComment newComment = productPostCommentService.updateComment(user2, comment.getId(), "New comment");
        assert newComment.getComment().equals("New comment");
    }
}