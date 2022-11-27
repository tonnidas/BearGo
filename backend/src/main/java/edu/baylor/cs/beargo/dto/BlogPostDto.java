package edu.baylor.cs.beargo.dto;

import edu.baylor.cs.beargo.model.BlogPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostDto {
    private Long id;
    private LocalDateTime postedDateTime;
    private LocalDateTime lastEditedDateTime;
    private Long imageId;
    private String description;

    private UserDto postedBy;

    public static List<BlogPostDto> getBlogPostDtoList(List<BlogPost> blogPosts) {
        ModelMapper modelMapper = new ModelMapper();
        List<BlogPostDto> blogPostDtoList = new ArrayList<>();

        for (BlogPost blogPost : blogPosts) {
            BlogPostDto productPostDto = modelMapper.map(blogPost, BlogPostDto.class);
            blogPostDtoList.add(productPostDto);
        }

        return blogPostDtoList;
    }
}
