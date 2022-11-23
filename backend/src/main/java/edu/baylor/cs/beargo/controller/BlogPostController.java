package edu.baylor.cs.beargo.controller;

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

    @GetMapping("/getAllBlogPost")
    public ResponseEntity<List<BlogPost>> getAllBlogPost() {
        List<BlogPost> blogPosts = blogPostService.getBlogPosts();
        return new ResponseEntity<>(blogPosts, HttpStatus.OK);
    }

    @GetMapping("/getBlogPostById")
    public ResponseEntity<BlogPost> getBlogPostById(@RequestParam(name = "blogPostId") Long id) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        return new ResponseEntity<>(blogPost, HttpStatus.OK);
    }

    @PostMapping("/createBlogPost")
    public ResponseEntity<BlogPost> createBlogPost(@AuthenticationPrincipal User user, BlogPost blogPost) {
        BlogPost createdBlogPost = blogPostService.createBlogPost(user, blogPost);
        return new ResponseEntity<>(createdBlogPost, HttpStatus.OK);
    }

    /*
     * getBlogPostByUserName - Done
     * getBlogPostByUserId - Done
     * updateBlogPost
     * getRecentPost - pagination (for the homepage refresh)
     * how many pages, which page you want
     *
     */

    @GetMapping("/getBlogPostByUsername")
    public ResponseEntity<List<BlogPost>> getBlogPostByUsername(@RequestParam(name = "username") String username) {
        List<BlogPost> blogPostList = blogPostService.getBlogPostByUsername(username);
        return new ResponseEntity<>(blogPostList, HttpStatus.OK);
    }

    @GetMapping("/getBlogPostByUserId")
    public ResponseEntity<List<BlogPost>> getBlogPostByUserId(@RequestParam(name = "id") Long id) {
        List<BlogPost> blogPostList = blogPostService.getBlogPostByUserId(id);
        return new ResponseEntity<>(blogPostList, HttpStatus.OK);
    }
}
