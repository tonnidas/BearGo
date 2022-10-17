package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MyService {

    @Autowired
    ProductRepository productRepository;

    public void populate() {
        for (int i = 1; i <= 3; i++) {
            Product product = new Product();
            product.setName("product-" + i);
            product.setDescription("description-" + i);
            product.setWeight(20.0 + i);
            productRepository.save(product);
        }
    }
}