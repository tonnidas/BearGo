package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.BlogPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.BlogPostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BlogPostServiceTest {

    @Autowired
    BlogPostService blogPostService;

    @MockBean
    BlogPostRepository blogPostRepository;

    @MockBean
    UserService userService;

    private User getDummyUser() {
        User user = new User();
        user.setId(1L);
        user.setUsername("Test");
        return user;
    }

    private List<BlogPost> getDummyBlogPost(int n) {
        List<BlogPost> blogPostList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            BlogPost blogPost = new BlogPost();
            blogPost.setId(i + 1L);
            blogPost.setPostedBy(getDummyUser());
            blogPost.setDescription("Test" + i);
            blogPostList.add(blogPost);
        }
        return blogPostList;
    }

    @Test
    void getBlogPosts() {
        int total = 5;
        List<BlogPost> blogPostList = getDummyBlogPost(total);
        when(blogPostRepository.findAllByOrderByPostedDateTimeDesc()).thenReturn(blogPostList);
        assertEquals(blogPostList, blogPostService.getBlogPosts());
    }

    @Test
    void getMyBlogPosts() {
        int total = 5;
        List<BlogPost> blogPostList = getDummyBlogPost(total);
        when(blogPostRepository.findAllByOrderByPostedDateTimeDesc()).thenReturn(blogPostList);
        assertEquals(blogPostList, blogPostService.getMyBlogPosts(getDummyUser()));
    }

    @Test
    void getBlogPostById() {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(1L);
        blogPost.setDescription("Test");
        when(blogPostRepository.findById(1L)).thenReturn(Optional.of(blogPost));
        assertEquals(blogPost, blogPostService.getBlogPostById(1L));
    }

    @Test
    void getBlogPostByUsername() {
        int total = 5;
        User user = getDummyUser();
        List<BlogPost> blogPostList = getDummyBlogPost(total);
        when(userService.getUserByUsername(user.getUsername())).thenReturn(user);
        when(blogPostRepository.findAllByPostedBy(user)).thenReturn(blogPostList);
        assertEquals(blogPostList, blogPostService.getBlogPostByUsername(user.getUsername()));
    }

    @Test
    void getBlogPostByUserId() {
        int total = 5;
        User user = getDummyUser();
        List<BlogPost> blogPostList = getDummyBlogPost(total);
        when(userService.getUserById(user.getId())).thenReturn(user);
        when(blogPostRepository.findAllByPostedBy(user)).thenReturn(blogPostList);
        assertEquals(blogPostList, blogPostService.getBlogPostByUserId(user.getId()));
    }
}
