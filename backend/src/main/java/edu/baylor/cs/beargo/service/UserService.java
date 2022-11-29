package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.dto.ContractFrequencyDto;
import edu.baylor.cs.beargo.dto.RatingDto;
import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.DeliveryStatus;
import edu.baylor.cs.beargo.model.ReviewAndRating;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.UserRepository;
import edu.baylor.cs.beargo.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewAndRatingService reviewAndRatingService;

    @Autowired
    ContractService contractService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException("Username: " + username);
        }
    }

    // Log in user
    public String login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        User authenticatedUser = (User) authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password))
                .getPrincipal();
        log.info("User authenticated: " + authenticatedUser.getUsername());

        return jwtTokenProvider.createToken(username, user.getRoles());
    }

    /**
     * This method registers a user.
     * It always sets isAdmin to false during registration.
     * By default, one admin will be created during application startup.
     * Later that admin can promote other user to admin.
     *
     * @param user the User object
     * @return created User object
     */
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setIsAdmin(false);
        return userRepository.save(user);
    }

    /**
     * Create admin user if not exist, otherwise update.
     *
     * @param email admin email/username
     * @param password admin password
     * @param name full name
     * @return created admin object
     */
    public User registerAdmin(String email, String password, String name) {
        User adminUser = userRepository.findByUsername(email).orElseGet(User::new);
        adminUser.setIsAdmin(true);
        adminUser.setUsername(email);
        adminUser.setPassword(passwordEncoder.encode(password));
        adminUser.setFullname(name);
        return userRepository.save(adminUser);
    }

    /**
     * @param id the user id
     * @return the user
     */
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user exists for given id");
        }
    }

    /**
     * @param username the username
     * @return the user
     */
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No user exists for given id");
        }
    }

    /**
     * @param fullname required for search
     * @return List of users contains this full name
     */
    public List<User> findByFullname(User currentUser, String fullname) {
        List<User> userList = userRepository.findByFullnameContainingIgnoreCase(fullname);
        List<User> retUserList = new ArrayList<>();
        for (User user : userList) {
            if (!user.getIsAdmin() && !currentUser.getId().equals(user.getId())) {
                retUserList.add(user);
            }
        }
        return retUserList;
    }

    // user is current user
    // user details represents updated fields
    public User updateUser(User updatedUser, User user) {
        // copy the fields that are allowed to update
        // username/email and associations are not allowed to update
        if (updatedUser.getFullname() != null) {
            user.setFullname(updatedUser.getFullname());
        }
        if (updatedUser.getStreet() != null) {
            user.setStreet(updatedUser.getStreet());
        }
        if (updatedUser.getCity() != null) {
            user.setCity(updatedUser.getCity());
        }
        if (updatedUser.getState() != null) {
            user.setState(updatedUser.getState());
        }
        if (updatedUser.getZip() != null) {
            user.setZip(updatedUser.getZip());
        }
        if (updatedUser.getPhoneNumber() != null) {
            user.setPhoneNumber(updatedUser.getPhoneNumber());
        }


        // TODO: update password more elegantly
        if (updatedUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }

        user = userRepository.save(user);

        // save the activity
        // activityService.saveUpdatedProfileActivity(user);

        return user;
    }

    // TODO: Need to check
    /**
     *
     * @param userId the user id
     * @return average rating of given user as a sender and a traveler
     */
    public RatingDto getRatingByUserId(Long userId) {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setUserId(userId);
        double totalRatingAsSender = 0;
        double totalRatingAsTraveler = 0;
        int totalRatingCntAsSender = 0;
        int totalRatingCntAsTraveler = 0;
        User user = getUserById(userId);
        Set<ReviewAndRating> reviewAndRatingList = user.getReceivedReviews();
        for (ReviewAndRating reviewAndRating: reviewAndRatingList) {
            reviewAndRating = reviewAndRatingService.getReviewRatingById(reviewAndRating.getId());
            if (reviewAndRating != null) {
                if(reviewAndRating.getContractReviewedBySender() != null) {
                    totalRatingCntAsTraveler++;
                    totalRatingAsTraveler += reviewAndRating.getRating();
                } else {
                    totalRatingAsSender++;
                    totalRatingAsSender += reviewAndRating.getRating();
                }
            }
        }

        if (totalRatingCntAsSender == 0) {
            ratingDto.setRatingAsSender(0.0);
        } else {
            ratingDto.setRatingAsSender(totalRatingAsSender / totalRatingCntAsSender);
        }

        if (totalRatingCntAsTraveler == 0) {
            ratingDto.setRatingAsTraveler(0.0);
        } else {
            ratingDto.setRatingAsTraveler(totalRatingAsTraveler / totalRatingCntAsTraveler);
        }

        return ratingDto;
    }

    // TODO: Need to check
    /**
     *
     * @param userId the user id
     * @return total number of contract, delivered, and unsuccessful contract as sender and traveler
     */
    public ContractFrequencyDto getContractFrequency(Long userId) {
        ContractFrequencyDto contractFrequencyDto = new ContractFrequencyDto();

        contractFrequencyDto.setUsedId(userId);

        int totalContractAsSender = 0;
        int totalDeliveredAsSender = 0;
        int totalUnsuccessfulAsSender = 0;

        int totalContractAsTraveler = 0;
        int totalDeliveredAsTraveler = 0;
        int totalUnsuccessfulAsTraveler = 0;

        User user = getUserById(userId);

        if(user != null) {
            totalContractAsSender = user.getSenderContracts().size();
            totalContractAsTraveler = user.getTravelerContracts().size();

            for(Contract contract: user.getSenderContracts()) {
                contract = contractService.getContractById(contract.getId());
                if(contract.getDeliveryStatus() == DeliveryStatus.DELIVERED) {
                    totalDeliveredAsSender++;
                }
                if(contract.getDeliveryStatus() == DeliveryStatus.UNSUCCESSFULL) {
                    totalUnsuccessfulAsSender++;
                }
            }

            for(Contract contract: user.getTravelerContracts()) {
                contract = contractService.getContractById(contract.getId());
                if(contract.getDeliveryStatus() == DeliveryStatus.DELIVERED) {
                    totalDeliveredAsTraveler++;
                }
                if(contract.getDeliveryStatus() == DeliveryStatus.UNSUCCESSFULL) {
                    totalUnsuccessfulAsTraveler++;
                }
            }
        }

        contractFrequencyDto.setTotalContractAsSender(totalContractAsSender);
        contractFrequencyDto.setTotalDeliveredAsSender(totalDeliveredAsSender);
        contractFrequencyDto.setTotalUnsuccessfulAsSender(totalUnsuccessfulAsSender);

        contractFrequencyDto.setTotalContractAsTraveler(totalContractAsTraveler);
        contractFrequencyDto.setTotalDeliveredAsTraveler(totalDeliveredAsTraveler);
        contractFrequencyDto.setTotalUnsuccessfulAsTraveler(totalUnsuccessfulAsTraveler);

        return contractFrequencyDto;
    }

    /**
     * Checks if
     *
     * @param
     * @param
     * @param
     * @return
     */
    // TODO: Ban a user for 6 months or a year or forever from the system - Maisha
}