package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.dto.ContractFrequencyDto;
import edu.baylor.cs.beargo.dto.RatingDto;
import edu.baylor.cs.beargo.dto.ReviewAndRatingDto;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Get the user that is currently logged in to the system
     *
     * @param user the logged user
     * @return the user who is currently logged
     */
    @GetMapping("/current")
    public ResponseEntity<UserDetails> getCurrentUser(@AuthenticationPrincipal User user) {
        UserDetails currentUser = userService.loadUserByUsername(user.getUsername());
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    /**
     * Get the user that is currently logged in to the system
     *
     * @param user the logged user
     * @param name the full name of the user
     * @return a list of all user with the fullname
     */
    @GetMapping("/findByFullname")
    public ResponseEntity<List<User>> findByFullname(@AuthenticationPrincipal User user,
                                                     @RequestParam(value = "name") String name) {
        List<User> userList = userService.findByFullname(user, name);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    /**
     * @param id the user id
     * @return the user
     */
    @GetMapping("/getUserById")
    public ResponseEntity<User> getUserById(@RequestParam(value = "id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    /**
     * update current user
     *
     * @param updatedUser the user
     * @param user        the logged user
     * @return the updated user
     */
    @PostMapping("/updateProfile")
    public User updateCurrentUser(@RequestBody User updatedUser, @AuthenticationPrincipal User user) {
        return userService.updateUser(updatedUser, user);
    }

    /**
     * @param userId the user id
     * @return a rating Dto
     */
    @GetMapping("/getRatingByUserId")
    public ResponseEntity<RatingDto> getRatingByUserId(@RequestParam(value = "userId") Long userId) {
        return new ResponseEntity<>(userService.getRatingByUserId(userId), HttpStatus.OK);
    }

    /**
     * @param userId the user id
     * @return a list of ReviewRating by the user
     */
    @GetMapping("/getReviewRatingByUserIdAsSender/{userId}")
    public ResponseEntity<List<ReviewAndRatingDto>> getReviewRatingByUserIdAsSender(@PathVariable(value = "userId") Long userId) {
        return new ResponseEntity<>(userService.getReviewRatingByUserIdAsSender(userId), HttpStatus.OK);
    }

    /**
     * @param userId the user id
     * @return a list of ReviewRating by the user
     */
    @GetMapping("/getReviewRatingByUserIdAsTraveler/{userId}")
    public ResponseEntity<List<ReviewAndRatingDto>> getReviewRatingByUserIdAsTraveler(@PathVariable(value = "userId") Long userId) {
        return new ResponseEntity<>(userService.getReviewRatingByUserIdAsTraveler(userId), HttpStatus.OK);
    }

    /**
     * @param userId the user id
     * @return the number of contracts of the user
     */
    @GetMapping("/getContractFrequency")
    public ResponseEntity<ContractFrequencyDto> getContractFrequency(@RequestParam(value = "userId") Long userId) {
        return new ResponseEntity<>(userService.getContractFrequency(userId), HttpStatus.OK);
    }

    /**
     * @param user    the logged user
     * @param imageId the image id
     * @return the profile image
     */
    @PostMapping("/updateProfileImage/{imageId}")
    public ResponseEntity<User> updateProfileImage(@AuthenticationPrincipal User user,
                                                   @PathVariable(value = "imageId") Long imageId) {
        return new ResponseEntity<>(userService.updateProfileImage(user, imageId), HttpStatus.OK);
    }

    /**
     * @param currentUser the logged user
     * @param userId      the user id
     * @param isBanned    the decision to ban
     * @return the user to be banned or unbanned
     */
    @PostMapping("/banOrUnbanUser/{userId}/{isBanned}")
    public ResponseEntity<User> banOrUnbanUser(@AuthenticationPrincipal User currentUser,
                                               @PathVariable(value = "userId") Long userId,
                                               @PathVariable(value = "isBanned") Boolean isBanned) {
        return new ResponseEntity<>(userService.banOrUnbanUser(currentUser, userId, isBanned), HttpStatus.OK);
    }
}
