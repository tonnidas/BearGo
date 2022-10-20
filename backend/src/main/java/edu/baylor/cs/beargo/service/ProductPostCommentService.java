package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.ProductPostComment;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.ProductPostCommentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductPostCommentService {

    @Autowired
    ProductPostCommentRepository productPostCommentRepository;

    @Autowired
    ProductPostService productPostService;

    /**
     * Checks if the input comment instance is null and product post id is null.
     *
     * @param user               the authenticated user
     * @param productPostComment the comment instance
     * @param productPostId      the productPost id
     * @return created ProductPostComment
     */
    public ProductPostComment createComment(User user, ProductPostComment productPostComment, Long productPostId) {
        if (productPostComment == null || productPostId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment or ProductPost ID cannot be null");
        } else if (productPostComment.getComment() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment cannot be empty text");
        }

        ProductPost productPost = productPostService.findProductPostById(productPostId);
        productPostComment.setCommentedProductPost(productPost);
        productPostComment.setCommentTime(LocalDateTime.now());
        productPostComment.setCommentedBy(user);

        return productPostCommentRepository.save(productPostComment);
    }
}