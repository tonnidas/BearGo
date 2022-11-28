package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.ProductPostComplaint;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.ProductPostComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/complaints")
public class ProductPostComplaintController {
    @Autowired
    ProductPostComplaintService productPostComplaintService;

    // Create Product post complaint
    @PostMapping
    public ResponseEntity<ProductPostComplaint> createComplain(@AuthenticationPrincipal User user, @RequestBody ProductPostComplaint productPostComplaint, @RequestParam Long productPostId) {
        ProductPostComplaint createdComplaint = productPostComplaintService.createComplaint(user, productPostComplaint, productPostId);
        return new ResponseEntity<>(createdComplaint, HttpStatus.OK);
    }
}
