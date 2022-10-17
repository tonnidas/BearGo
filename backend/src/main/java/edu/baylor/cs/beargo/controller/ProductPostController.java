package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.service.ProductPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productPosts")
public class ProductPostController {
    @Autowired
    ProductPostService productPostService;

    @PostMapping
    public ResponseEntity<ProductPost> createProductPost(@RequestBody ProductPost productPost) {
        ProductPost createdProductPost = productPostService.createProductPost(productPost);
        return new ResponseEntity<>(createdProductPost, HttpStatus.OK);
    }
}
