package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.dto.ProductPostDto;
import edu.baylor.cs.beargo.dto.SearchDto;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.ProductPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productPosts")
public class ProductPostController {
    @Autowired
    ProductPostService productPostService;

    /**
     * Create a product post
     *
     * @param user        the logged user
     * @param productPost the product post
     * @return the created product post
     */
    @PostMapping
    public ResponseEntity<ProductPost> createProductPost(@AuthenticationPrincipal User user, @RequestBody ProductPost productPost) {
        ProductPost createdProductPost = productPostService.createProductPost(user, productPost);
        return new ResponseEntity<>(createdProductPost, HttpStatus.OK);
    }

    /**
     * Update a product post
     *
     * @param user        the logged user
     * @param productPost the product post
     * @return the updated product post
     */
    @PutMapping("/updateProductPost")
    public ResponseEntity<ProductPost> updateProductPost(@AuthenticationPrincipal User user, @RequestBody ProductPost productPost) {
        ProductPost updateProductPost = productPostService.updateProductPost(user, productPost);
        return new ResponseEntity<>(updateProductPost, HttpStatus.OK);
    }

    /**
     * Select product post by id
     *
     * @param id the product post id
     * @return the product post
     */
    @GetMapping("/{id}")
    public ProductPostDto getProductPostById(@PathVariable Long id) {
        ProductPost productPost = productPostService.getProductPostById(id);
        return ProductPostDto.getProductPostDto(productPost);
    }

    /**
     * @param user        the logged user
     * @param startDate   the start date of contract
     * @param endDate     the end date of contract
     * @param sourceCity  the source city
     * @param sourceState the source state
     * @param destCity    the destination city
     * @param destState   the destination state
     * @return a list of product post Dto
     */
    @GetMapping("/searchProductPost/{startDate}/{endDate}/{sourceCity}/{sourceState}/{destCity}/{destState}")
    public ResponseEntity<?> searchProductPost(@AuthenticationPrincipal User user,
                                               @PathVariable(value = "startDate") String startDate,
                                               @PathVariable(value = "endDate") String endDate,
                                               @PathVariable(value = "sourceCity") String sourceCity,
                                               @PathVariable(value = "sourceState") String sourceState,
                                               @PathVariable(value = "destCity") String destCity,
                                               @PathVariable(value = "destState") String destState) {
        SearchDto searchDto = new SearchDto();
        searchDto.setStartDate(startDate);
        searchDto.setEndDate(endDate);
        searchDto.setSourceCity(sourceCity);
        searchDto.setSourceState(sourceState);
        searchDto.setDestState(destState);
        searchDto.setDestCity(destCity);
        List<ProductPost> searchPosts = productPostService.searchProductPost(searchDto);
        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(searchPosts);
        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
    }

    /**
     * @return a list of product post Dto
     */
    @GetMapping("/getAllProductPost")
    public ResponseEntity<List<ProductPostDto>> getAllProductPost() {
        List<ProductPost> productPosts = productPostService.getProductPosts();
        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(productPosts);
        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
    }

    /**
     * Checks if the status of the contract is searching_traveler
     *
     * @return a list of product post Dto
     */
    @GetMapping("/getAllProductPost/searchingTraveler")
    public ResponseEntity<List<ProductPostDto>> getSearchingTravelerPost() {
        List<ProductPost> productPosts = productPostService.getSearchingTravelerPosts();
        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(productPosts);
        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
    }

    /**
     * @param user the logged user
     * @return a list of product posts of logged user
     */
    @GetMapping("/getProductPostByUser")
    public ResponseEntity<List<ProductPostDto>> getProductPostByUser(@AuthenticationPrincipal User user) {
        List<ProductPost> productPosts = productPostService.getProductPostsByUser(user);
        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(productPosts);
        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
    }

    /**
     * @param user      the logged user
     * @param userType  the type of the user
     * @param delStatus the delivery status
     * @return a list of product post Dto
     */
    @GetMapping("/getProductPostByCriteria/{userType}/{delStatus}")
    public ResponseEntity<List<ProductPostDto>> getProductPostByCriteria(@AuthenticationPrincipal User user,
                                                                         @PathVariable("userType") String userType,
                                                                         @PathVariable("delStatus") String delStatus) {
        List<ProductPost> productPosts = productPostService.getProductPostByCriteria(user, userType, delStatus);
        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(productPosts);
        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
    }

    /**
     * set and update interested people list
     *
     * @param user          the logged user
     * @param productPostId the product post id
     * @return the product post
     */
    @PostMapping("/setInterestedPeople")
    public ResponseEntity<ProductPost> setInterestedPeople(@AuthenticationPrincipal User user, @RequestParam Long productPostId) {
        ProductPost productPost = productPostService.updateInterestedPeople(user, productPostId);
        return new ResponseEntity<>(productPost, HttpStatus.OK);
    }
}
