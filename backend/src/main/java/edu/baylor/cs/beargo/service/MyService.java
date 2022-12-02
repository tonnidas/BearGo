package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.BlogPost;
import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.model.ReviewAndRating;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.BlogPostRepository;
import edu.baylor.cs.beargo.repository.ProductRepository;
import edu.baylor.cs.beargo.repository.ReviewAndRatingRepository;
import edu.baylor.cs.beargo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MyService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    ReviewAndRatingRepository reviewAndRatingRepository;

    public void populate() {
        for (int i = 1; i <= 3; i++) {
            Product product = new Product();
            product.setDescription("description-" + i);
            product.setWeight(20.0 + i);
            productRepository.save(product);
        }
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUsername("user" + (i + 10) + "@beargo.com");
            user.setPassword("password");
            user.setIsAdmin(false);
            userRepository.save(user);
        }
        for (int i = 1; i <= 3; i++) {
            User user = new User();
            user.setUsername("admin" + (i + 200) + "@beargo.com");
            user.setPassword("password");
            user.setIsAdmin(true);
            userRepository.save(user);
        }
        for (int i = 1; i <= 5; i++) {
            BlogPost blogPost = new BlogPost();
            blogPost.setDescription("Blog Post Description " + i);
            blogPost.setPostedDateTime(LocalDateTime.now());
            blogPost.setLastEditedDateTime(blogPost.getPostedDateTime());
            // It depends on the user loop. There must be some user with this username.
            User user = userRepository.findByUsername("user" + ((i % 2) + 11) + "@beargo.com").get();
            blogPost.setPostedBy(user);
            blogPostRepository.save(blogPost);
        }

        for (int i = 1; i <= 5; i++) {
            ReviewAndRating reviewAndRating = new ReviewAndRating();
            reviewAndRating.setReview("This person is good " + i);
            reviewAndRating.setRating(5);
            reviewAndRating.setReviewDateTime(LocalDateTime.now());
            // It depends on the user loop. There must be some user with this username.
            User user1 = userRepository.findByUsername("user" + (i * 2 + 9) + "@beargo.com").get();
            User user2 = userRepository.findByUsername("user" + (i * 2 + 10) + "@beargo.com").get();
            reviewAndRating.setReviewedBy(user1);
            reviewAndRating.setReviewedTo(user2);
            reviewAndRatingRepository.save(reviewAndRating);
        }
    }
}