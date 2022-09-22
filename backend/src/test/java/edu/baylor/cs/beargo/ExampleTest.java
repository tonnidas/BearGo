package edu.baylor.cs.beargo;

import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.repository.ProductRepository;
import edu.baylor.cs.beargo.service.MyService;
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
    private ProductRepository repository; // @DataJpaTest will initialize a transactional repository
    private MyService service; // do not mock, instead use the autowired repository

    @BeforeEach
    public void initService() {
        service = new MyService(repository);
        service.populate();
    }

    @Test
    public void getProductsTest() {
        List<Product> products = service.getProducts(null);
        assert (products.size() == 3);
    }

    @Test
    public void findByMinimumWeightTest() {
        List<Product> products = repository.findByMinimumWeight(21);
        assert (products.size() == 2);
    }
}
