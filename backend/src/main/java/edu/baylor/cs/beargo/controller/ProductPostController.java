package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.ProductPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/productPosts")
public class ProductPostController {
    @Autowired
    ProductPostService productPostService;

    @PostMapping
    public ResponseEntity<ProductPost> createProductPost(@AuthenticationPrincipal User user, @RequestBody ProductPost productPost) {
        ProductPost createdProductPost = productPostService.createProductPost(user, productPost);
        return new ResponseEntity<>(createdProductPost, HttpStatus.OK);
    }

    @PutMapping("/updateProductPost")
    public ResponseEntity<ProductPost> updateProductPost(@AuthenticationPrincipal User user, @RequestBody ProductPost productPost) {
        ProductPost updateProductPost = productPostService.updateProductPost(user, productPost);
        return new ResponseEntity<>(updateProductPost, HttpStatus.OK);
    }

    @GetMapping("/searchProductPost/{source}/{destination}/{startDate}/{endDate}")
    public ResponseEntity<ProductPost> searchProductPost(@AuthenticationPrincipal User user,
                                                         @PathVariable("source") String source,
                                                         @PathVariable("destination") String destination,
                                                         @PathVariable("startDate") Date startDate,
                                                         @PathVariable("endDate") Date endDate) {
        List<ProductPost> searchPosts = productPostService.searchProductPost(source,destination,startDate,endDate);
        return new ResponseEntity(searchPosts, HttpStatus.OK);
    }

}
