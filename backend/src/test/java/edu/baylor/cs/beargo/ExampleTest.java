package edu.baylor.cs.beargo;

import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.repository.BlogPostRepository;
import edu.baylor.cs.beargo.repository.ProductRepository;
import edu.baylor.cs.beargo.repository.ReviewAndRatingRepository;
import edu.baylor.cs.beargo.repository.UserRepository;
import edu.baylor.cs.beargo.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ExampleTest {
    /*@Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private ReviewAndRatingRepository reviewAndRatingRepository;

    private ProductService productService;

    private UserService userService;

    private BlogPostService blogPostService;

    private ReviewAndRatingService reviewAndRatingService;


    @BeforeEach
    public void initService() {
        productService = new ProductService(productRepository);
        userService = new UserService();
        blogPostService = new BlogPostService();
        reviewAndRatingService = new ReviewAndRatingService();
        MyService myService = new MyService(productRepository, userRepository, blogPostRepository, reviewAndRatingRepository);
        myService.populate();
    }

    @Test
    public void getProductsTest() {
        List<Product> products = productService.getAllProducts();
        assert (products.size() == 3);
    }

    @Test
    public void findByMinimumWeightTest() {
        List<Product> products = productRepository.findByMinimumWeight(21);
        assert (products.size() == 2);
    }*/
}
