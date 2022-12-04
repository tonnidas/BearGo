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
import java.util.ArrayList;
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
        return blogPostRepository.findAllByOrderByPostedDateTimeDesc();
    }

    /**
     * Checks if the blog post is of the logged user
     *
     * @param user     the logged user
     * @return blog post
     */
    public List<BlogPost> getMyBlogPosts(User user) {
        List<BlogPost> blogPosts = blogPostRepository.findAllByOrderByPostedDateTimeDesc();
        List<BlogPost> blogs = new ArrayList<>();

        for (BlogPost b : blogPosts) {
            if (b.getPostedBy().getId().equals(user.getId())) {
                blogs.add(b);
            }
        }
        return blogs;
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
     * @param user     the logged user
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
     * Checks if the given username is null or not
     * Checks if the user exists with this username
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

    /**
     * Checks if the given id is null or not
     * Checks if the user exists with this id
     *
     * @param id the id of a user
     * @return created blog post by that user
     */
    public List<BlogPost> getBlogPostByUserId(Long id) {

        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id parameter can not be null.");
        }

        User user = userService.getUserById(id);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user found with this id.");
        }

        return blogPostRepository.findAllByPostedBy(user);
    }

    /**
     * Checks if the user is null or not
     * Checks if the blog post is null or not
     * Checks if the blog post id is null or not
     * Fetch the blog post from the database with the id
     * Checks if the user id and blog posted by user id is same or not
     * Sets the new description if not null
     * Sets the new image path if not null
     * Sets the current time with current time
     * Put the data in the repository
     *
     * @param user     the user who will update the blog post
     * @param blogPost the blog post that should be updated
     * @return updated blog post
     */
    public BlogPost updateBlogPost(User user, BlogPost blogPost) {

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User parameter can not be null.");
        }

        if (blogPost == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BlogPost parameter can not be null.");
        }

        if (blogPost.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BlogPost Id can not be null.");
        }

        BlogPost dbBlogPost = getBlogPostById(blogPost.getId());

        if (!user.getId().equals(dbBlogPost.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Id and Blog Posted by User Id is not same.");
        }

        if (blogPost.getDescription() != null) {
            dbBlogPost.setDescription(blogPost.getDescription());
        }

        if (blogPost.getImageId() != null) {
            dbBlogPost.setId(blogPost.getId());
        }

        dbBlogPost.setLastEditedDateTime(LocalDateTime.now());

        return blogPostRepository.save(dbBlogPost);
    }
}
