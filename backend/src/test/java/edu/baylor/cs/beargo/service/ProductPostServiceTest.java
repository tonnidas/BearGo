package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.DeliveryStatus;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Objects;

import static edu.baylor.cs.beargo.service.SampleModels.getSampleProductPost;
import static edu.baylor.cs.beargo.service.SampleModels.getSampleUser;

@DataJpaTest
class ProductPostServiceTest {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ProductPostRepository productPostRepository;

    @Autowired
    UserRepository userRepository;

    ProductPostService productPostService;

    @BeforeEach
    void setUp() {
        productPostService = new ProductPostService(addressRepository,
                productRepository,
                contractRepository,
                productPostRepository
        );
    }

    @Test
    void getProductPosts() {
        User user = userRepository.save(getSampleUser(1));
        productPostService.createProductPost(user, getSampleProductPost());
        productPostService.createProductPost(user, getSampleProductPost());
        assert productPostService.getProductPosts().size() == 2;
    }

    @Test
    void getSearchingTravelerPosts() {
        User user = userRepository.save(getSampleUser(1));
        productPostService.createProductPost(user, getSampleProductPost());

        ProductPost productPost = productPostService.createProductPost(user, getSampleProductPost());
        productPost.getContract().setDeliveryStatus(DeliveryStatus.INITIATED);
        contractRepository.save(productPost.getContract());

        assert productPostService.getSearchingTravelerPosts().size() == 1;
    }


    @Test
    void getProductPostsByUser() {
        User user = userRepository.save(getSampleUser(1));
        productPostService.createProductPost(user, getSampleProductPost());
        List<ProductPost> searchedProductPosts = productPostService.getProductPostsByUser(user);
        assert searchedProductPosts.size() == 1;
        assert searchedProductPosts.get(0).getContract().getSender().getId().equals(user.getId());
    }

    @Test
    void getProductPostById() {
        User user = userRepository.save(getSampleUser(1));
        ProductPost createdProductPost = productPostService.createProductPost(user, getSampleProductPost());
        ProductPost searchedProductPost = productPostService.getProductPostById(createdProductPost.getId());
        assert Objects.equals(searchedProductPost.getId(), createdProductPost.getId());
    }

    @Test
    void createProductPost() {
        User user = userRepository.save(getSampleUser(1));
        ProductPost createdProductPost = productPostService.createProductPost(user, getSampleProductPost());
        assert createdProductPost.getId() != null;
    }

    @Test
    void updateProductPost() {
        User user = userRepository.save(getSampleUser(1));
        ProductPost createdProductPost = productPostService.createProductPost(user, getSampleProductPost());
        createdProductPost.setDescription("new description");
        ProductPost updatedProductPost = productPostService.updateProductPost(user, createdProductPost);
        assert updatedProductPost.getDescription().equals(createdProductPost.getDescription());
    }

    //    @Test
//    void searchProductPost() {
//    }
//
//    @Test
//    void getProductPostByCriteria() {
//    }
//
    @Test
    void updateInterestedPeople() {
        User user = userRepository.save(getSampleUser(1));
        ProductPost createdProductPost = productPostService.createProductPost(user, getSampleProductPost());

        User traveler1 = userRepository.save(getSampleUser(2));
        User traveler2 = userRepository.save(getSampleUser(3));
        productPostService.updateInterestedPeople(traveler1, createdProductPost.getId());
        ProductPost updatedProductPost = productPostService.updateInterestedPeople(traveler2, createdProductPost.getId());

        assert updatedProductPost.getId().equals(createdProductPost.getId());
        assert updatedProductPost.getInterestedPeoples().size() == 2;
    }
}