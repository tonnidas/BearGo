package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.dto.ProductPostDto;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.ProductPostComplaint;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.AdminService;
import edu.baylor.cs.beargo.service.MyService;
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
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    MyService myService;

    /**
     * Populates 10 admins and 10 users
     * @return a confirmation string
     */
    @PostMapping("/populate")
    public ResponseEntity<String> populate() {
        myService.populate();
        return new ResponseEntity<>("Data populated", HttpStatus.OK);
    }

    /**
     * selects all admins (except general users) from list of users
     * @return a list of users aho are admins
     */
    @GetMapping
    public ResponseEntity<List<User>> admins() {
        List<User> admins = adminService.getAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    /**
     * selects only users (except admins) from list of users
     * @return a list of users aho are not admins
     */
    @GetMapping("/onlyUsers")
    public ResponseEntity<List<User>> users() {
        List<User> users = adminService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Selects all users (including admins)
     * @return a list of users
     */
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Get admin by id
     * @param id     The id
     * @return an admin
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getAdminById(@PathVariable("id") Long id) {
        User admin = adminService.getAdminById(id);
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    /**
     * Promote a user with the id to be admin
     * @param id     The id
     * @return a promoted user
     */
    @GetMapping("/promote/{id}")
    public ResponseEntity<User> promote(@PathVariable("id") Long id) {
        User promotedUser = adminService.promoteUser(id);
        return new ResponseEntity<>(promotedUser, HttpStatus.OK);
    }

    /**
     *
     * @param threshold  the limit of complaints count
     * @return a list of reported productPosts
     */
    @GetMapping("/getReportedProductPosts")
    public List<ProductPostDto> getReportedProductPosts(@RequestParam int threshold) {
        List<ProductPost> productPosts = adminService.getReportedProductPosts(threshold);
        return ProductPostDto.getProductPostDtoList(productPosts);
    }

    /**
     *
     * @param user           the logged user
     * @param productPostId  the complained productPost id
     * @param verdict        the decision
     * @return a set of productPost Complaints
     */
    @PostMapping("/resolve/productPost")
    public ResponseEntity<Set<ProductPostComplaint>> reviewProductPostComplaint(@AuthenticationPrincipal User user,
                                                                                @RequestParam Long productPostId,
                                                                                @RequestParam String verdict) {
        Set<ProductPostComplaint> complaints = adminService.reviewProductPostComplaint(user, productPostId, verdict);
        return new ResponseEntity<>(complaints, HttpStatus.OK);
    }
}
