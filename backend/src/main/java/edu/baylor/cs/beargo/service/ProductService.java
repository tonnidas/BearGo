package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    /**
     * Returns all products
     *
     * @return list of products
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Checks if the product id is valid
     *
     * @param id the product id
     * @return product
     */
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product record exist for given id");
        }
    }

    /**
     * @param product the product instance
     * @return the product
     */
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * @param id      the product id
     * @param product the product instance
     * @return product
     */
    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    /**
     * @param id the product id
     */
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product record exist for given id");
        }
    }
}