package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.dto.BlogPostDto;
import edu.baylor.cs.beargo.model.BlogPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogPosts")
public class BlogPostController {

    @Autowired
    BlogPostService blogPostService;

    /**
     *
     * @return a list of all Blog Posts
     */
    @GetMapping("/getAllBlogPost")
    public ResponseEntity<List<BlogPostDto>> getAllBlogPost() {
        List<BlogPost> blogPosts = blogPostService.getBlogPosts();
        List<BlogPostDto> blogPostDtoList = BlogPostDto.getBlogPostDtoList(blogPosts);
        return new ResponseEntity<>(blogPostDtoList, HttpStatus.OK);
    }

    /**
     *
     * @param user    the logged user
     * @return a list of Blog Posts of the logged user
     */
    @GetMapping("/getMyBlogPosts")
    public ResponseEntity<List<BlogPostDto>> getMyBlogPosts(@AuthenticationPrincipal User user) {
        List<BlogPost> blogPosts = blogPostService.getMyBlogPosts(user);
        List<BlogPostDto> blogPostDtoList = BlogPostDto.getBlogPostDtoList(blogPosts);
        return new ResponseEntity<>(blogPostDtoList, HttpStatus.OK);
    }

    /**
     *
     * @param id     the id of a Blog Post
     * @return a list of Blog Posts by id
     */
    @GetMapping("/getBlogPostById")
    public ResponseEntity<BlogPost> getBlogPostById(@RequestParam(name = "blogPostId") Long id) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }

    /**
     *
     * @param user       the logged user
     * @param blogPost   the Blog Post
     * @return the saved Blog Post
     */
    @PostMapping("/createBlogPost")
    public ResponseEntity<BlogPost> createBlogPost(@AuthenticationPrincipal User user, @RequestBody BlogPost blogPost) {
        BlogPost createdBlogPost = blogPostService.createBlogPost(user, blogPost);
        return new ResponseEntity<>(createdBlogPost, HttpStatus.OK);
    }

    /**
     *
     * @param username  the username
     * @return  a list of Blog Posts by the username
     */
    @GetMapping("/getBlogPostByUsername")
    public ResponseEntity<List<BlogPost>> getBlogPostByUsername(@RequestParam(name = "username") String username) {
        List<BlogPost> blogPostList = blogPostService.getBlogPostByUsername(username);
        return new ResponseEntity<>(blogPostList, HttpStatus.OK);
    }

    /**
     *
     * @param id   the user id
     * @return  a list of Blog Posts by user id
     */
    @GetMapping("/getBlogPostByUserId")
    public ResponseEntity<List<BlogPost>> getBlogPostByUserId(@RequestParam(name = "id") Long id) {
        List<BlogPost> blogPostList = blogPostService.getBlogPostByUserId(id);
        return new ResponseEntity<>(blogPostList, HttpStatus.OK);
    }

    /**
     *
     * @param user      the logged user
     * @param blogPost  the Blog Post
     * @return the updated Blog post
     */
    @PutMapping("/updateBlogPost")
    public ResponseEntity<BlogPost> updateBlogPost(@AuthenticationPrincipal User user, @RequestBody BlogPost blogPost) {
        BlogPost updateBlogPost = blogPostService.updateBlogPost(user, blogPost);
        return new ResponseEntity<>(updateBlogPost, HttpStatus.OK);
    }
}
