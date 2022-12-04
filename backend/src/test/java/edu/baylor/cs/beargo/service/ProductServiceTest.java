package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static edu.baylor.cs.beargo.service.SampleModels.getSampleProduct;

@DataJpaTest
class ProductServiceTest {

    @Autowired
    ProductRepository productRepository;

    ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllProducts() {
        Product createdProduct = productService.createProduct(getSampleProduct(3.0, "Laptop"));
        Product createdProduct1 = productService.createProduct(getSampleProduct(1.5, "Cards"));
        Product createdProduct2 = productService.createProduct(getSampleProduct(1.0, "DVD Player"));
        assert productService.getAllProducts().size() == 3;
    }

    @Test
    void getProductById() {
        Product createdProduct = productService.createProduct(getSampleProduct(3.0, "Laptop"));
        assert productService.getProductById(createdProduct.getId()) != null;
    }

    @Test
    void createProduct() {
        Product createdProduct = productService.createProduct(getSampleProduct(3.0, "Laptop"));
        assert createdProduct.getId() != null;
    }

    @Test
    void updateProduct() {
        Product createdProduct1 = productService.createProduct(getSampleProduct(1.5, "Cards"));
        Product createdProduct2 = productService.createProduct(getSampleProduct(1.0, "DVD Player"));
        Product updatedProduct = productService.updateProduct(createdProduct1.getId(), createdProduct2);
        assert updatedProduct.getId() != null;
        assert updatedProduct.getWeight() == 1.0;
        assert updatedProduct.getDescription().equals("DVD Player");
    }

    @Test
    void deleteProduct() {
        Product createdProduct1 = productService.createProduct(getSampleProduct(1.5, "Cards"));
        Product createdProduct2 = productService.createProduct(getSampleProduct(1.0, "DVD Player"));
        productService.deleteProduct(createdProduct2.getId());
        assert productService.getAllProducts().size() == 1;
    }
}