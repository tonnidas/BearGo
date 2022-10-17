package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    MyService service;

    // TODO: remove this in production
    @PostMapping("/populate")
    @PreAuthorize("hasRole('ADMIN')")
    public HttpStatus populate() {
        service.populate();
        return HttpStatus.OK;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String name) {
        List<Product> products = service.getProducts(name);
        return new ResponseEntity<>(products, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = service.getProductById(id);
        return new ResponseEntity<>(product, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = service.createProduct(product);
        return new ResponseEntity<>(createdProduct, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product updatedProduct = service.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public HttpStatus deleteProduct(@PathVariable("id") Long id) {
        service.deleteProduct(id);
        return HttpStatus.OK;
    }
}