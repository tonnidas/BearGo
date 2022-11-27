package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.dto.ProductPostDto;
import edu.baylor.cs.beargo.dto.UserContractsDto;
import edu.baylor.cs.beargo.model.DeliveryStatus;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.ProductPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<ProductPostDto>> searchProductPost(@AuthenticationPrincipal User user,
                                                                  @PathVariable("source") String source,
                                                                  @PathVariable("destination") String destination,
                                                                  @PathVariable("startDate") Date startDate,
                                                                  @PathVariable("endDate") Date endDate) {

        List<ProductPost> searchPosts = productPostService.searchProductPost(source, destination, startDate, endDate);
        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(searchPosts);
        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
    }

    @GetMapping("/getAllProductPost")
    public ResponseEntity<List<ProductPostDto>> getAllProductPost() {
        List<ProductPost> productPosts = productPostService.getProductPosts();
        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(productPosts);
        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
    }

    @GetMapping("/getAllProductPost/searchingTraveler")
    public ResponseEntity<List<ProductPostDto>> getSearchingTravelerPost() {
        List<ProductPost> productPosts = productPostService.getSearchingTravelerPosts();
        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(productPosts);
        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
    }

    @GetMapping("/getProductPostByUser")
    public ResponseEntity<List<ProductPostDto>> getProductPostByUser(@AuthenticationPrincipal User user) {
        List<ProductPost> productPosts = productPostService.getProductPostsByUser(user);
        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(productPosts);
        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
    }

    @GetMapping("/getProductPostByCriteria/{userType}/{delStatus}")
    public ResponseEntity<List<ProductPostDto>> getProductPostByCriteria(@AuthenticationPrincipal User user,
                                                                         @PathVariable("userType") String userType,
                                                                         @PathVariable("delStatus") DeliveryStatus delStatus) {
        List<ProductPost> productPosts = productPostService.getProductPosts();
        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(productPosts);
        List<UserContractsDto> userContractsDtos = UserContractsDto.getUserContractDtoList(productPosts, user, userType, delStatus);
        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
    }
}
