package edu.baylor.cs.beargo;

import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.repository.ProductRepository;
import edu.baylor.cs.beargo.repository.UserRepository;
import edu.baylor.cs.beargo.service.MyService;
import edu.baylor.cs.beargo.service.ProductService;
import edu.baylor.cs.beargo.service.UserService;
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
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private ProductService productService;

    private UserService userService;

    @BeforeEach
    public void initService() {
        productService = new ProductService(productRepository);
        userService = new UserService();
        MyService myService = new MyService(productRepository, userRepository);
        myService.populate();
    }

    @Test
    public void getProductsTest() {
        List<Product> products = productService.getProducts(null);
        assert (products.size() == 3);
    }

    @Test
    public void findByMinimumWeightTest() {
        List<Product> products = productRepository.findByMinimumWeight(21);
        assert (products.size() == 2);
    }
}
