package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.ProductPostComment;
import edu.baylor.cs.beargo.model.ProductPostComplaint;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.ProductPostCommentService;
import edu.baylor.cs.beargo.service.ProductPostComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class ProductPostCommentController {
    @Autowired
    ProductPostCommentService productPostCommentService;

    // Create Product post comment
    @PostMapping
    public ResponseEntity<ProductPostComment> createComment(@AuthenticationPrincipal User user, @RequestBody ProductPostComment productPostComment, @RequestParam Long productPostId) {
        ProductPostComment createdComment = productPostCommentService.createComment(user, productPostComment, productPostId);
        return new ResponseEntity<>(createdComment, HttpStatus.OK);
    }
}