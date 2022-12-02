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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @GetMapping("/{id}")
    public ProductPostDto getProductPostById(@PathVariable Long id) {
        ProductPost productPost = productPostService.getProductPostById(id);
        return ProductPostDto.getProductPostDto(productPost);
    }

//    @GetMapping("/searchProductPost")
//    public ResponseEntity<List<ProductPostDto>> searchProductPost(@AuthenticationPrincipal User user,
//                                                                  @RequestParam(value = "startDate") String startDate
////                                                                  ,@RequestParam Date endDate,
////                                                                  @RequestParam String sourceCity,
////                                                                  @RequestParam String sourceState,
////                                                                  @RequestParam String destCity,
////                                                                  @RequestParam String destState
//    ) {
//
//        SearchDto searchDto = new SearchDto();
////        searchDto.setStartDate(startDate);
////        searchDto.setEndDate(endDate);
////        searchDto.setSourceCity(sourceCity);
////        searchDto.setSourceState(sourceState);
////        searchDto.setDestState(destState);
////        searchDto.setDestCity(destCity);
//        List<ProductPost> searchPosts = productPostService.searchProductPost(searchDto);
//        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(searchPosts);
//        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
//    }

    @GetMapping("/searchProductPost/{startDate}/{endDate}/{sourceCity}/{sourceState}/{destCity}/{destState}")
    public ResponseEntity<?> searchProductPost(@AuthenticationPrincipal User user,
                                               @PathVariable(value = "startDate") String startDate,
                                               @PathVariable(value = "endDate") String endDate,
                                               @PathVariable(value = "sourceCity") String sourceCity,
                                               @PathVariable(value = "sourceState") String sourceState,
                                               @PathVariable(value = "destCity") String destCity,
                                               @PathVariable(value = "destState") String destState)
    {
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
        // return new ResponseEntity<>("OkayOkay", HttpStatus.OK);
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
                                                                         @PathVariable("delStatus") String delStatus) {
        List<ProductPost> productPosts = productPostService.getProductPostByCriteria(user, userType, delStatus);
        List<ProductPostDto> productPostDtoList = ProductPostDto.getProductPostDtoList(productPosts);
        return new ResponseEntity<>(productPostDtoList, HttpStatus.OK);
    }

    // set and update interested people list
    @PostMapping("/setInterestedPeople")
    public ResponseEntity<ProductPost> setInterestedPeople(@AuthenticationPrincipal User user, @RequestParam Long productPostId) {
        ProductPost productPost = productPostService.updateInterestedPeople(user, productPostId);
        return new ResponseEntity<>(productPost, HttpStatus.OK);
    }
}
