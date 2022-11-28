package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.ProductPostComplaint;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/admins")
public class AdminController {
    @Autowired
    AdminService adminService;

    // Get all admins (except general users)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> admins() {
        List<User> admins = adminService.getAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    // Get only users (except admins)
    @GetMapping("/onlyUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> users() {
        List<User> users = adminService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get all users (including admins)
    @GetMapping("/allUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // Get admin by id
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getAdminById(@PathVariable("id") Long id) {
        User admin = adminService.getAdminById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    // Promote a user to be admin
    @GetMapping("/promote/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> promote(@PathVariable("id") Long id) {
        User promotedUser = adminService.promoteUser(id);
        return new ResponseEntity<>(promotedUser, HttpStatus.OK);
    }

    // block product post
    @PostMapping("/resolve/productPost")
    public ResponseEntity<Set<ProductPostComplaint>> resolvePostComplaint(@AuthenticationPrincipal User user, @RequestParam Long productPostId, @RequestParam String verdict) {
        Set<ProductPostComplaint> complaints = adminService.resolvePostComplaint(user, productPostId, verdict);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }

    // block user here
}
