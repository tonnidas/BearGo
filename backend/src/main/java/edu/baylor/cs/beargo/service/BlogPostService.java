package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.BlogPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.BlogPostRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class BlogPostService {

    @Autowired
    BlogPostRepository blogPostRepository;

    @Autowired
    UserService userService;

    /**
     * @return all BlogPosts
     */
    public List<BlogPost> getBlogPosts() {
        return blogPostRepository.findAll();
    }

    /**
     * Checks if the blog post exists
     *
     * @param id the blog post id
     * @return blog post
     */
    public BlogPost getBlogPostById(Long id) {
        Optional<BlogPost> optionalBlogPost = blogPostRepository.findById(id);
        if (optionalBlogPost.isPresent()) {
            return optionalBlogPost.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No blog post record exist for given id");
        }
    }

    /**
     * Checks if the description exists
     * Checks if the postedBy exists
     * Sets the postedTime by current time
     * Sets the lastEditedTime by current time
     *
     * @param blogPost the user id
     * @return created blog post
     */
    public BlogPost createBlogPost(User user, BlogPost blogPost) {

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BlogPost must be postedBy someone.");
        }

        if (blogPost.getDescription() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BlogPost description cannot be empty");
        }

        blogPost.setPostedDateTime(LocalDateTime.now());
        blogPost.setLastEditedDateTime(blogPost.getPostedDateTime());
        blogPost.setPostedBy(user);

        return blogPostRepository.save(blogPost);
    }

    /**
     *
     * @param username the username
     * @return created blog post by that username
     */
    public List<BlogPost> getBlogPostByUsername(String username) {

        if (username == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username parameter can not be null.");
        }

        User user = userService.getUserByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user found with this user name.");
        }

        return blogPostRepository.findAllByPostedBy(user);
    }
}
