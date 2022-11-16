package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.ProductRepository;
import edu.baylor.cs.beargo.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    public void populate() {
        for (int i = 1; i <= 3; i++) {
            Product product = new Product();
            product.setName("product-" + i);
            product.setDescription("description-" + i);
            product.setWeight(20.0 + i);
            productRepository.save(product);
        }
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUsername("user"+i+"@beargo.com");
            user.setPassword("password");
            user.setIsAdmin(false);
            userRepository.save(user);
        }
    }
}