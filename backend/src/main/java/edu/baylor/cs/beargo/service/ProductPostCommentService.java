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
import java.util.Optional;

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

        ProductPost productPost = productPostService.getProductPostById(productPostId);
        productPostComment.setCommentedProductPost(productPost);
        productPostComment.setCommentTime(LocalDateTime.now());
        productPostComment.setCommentedBy(user);

        return productPostCommentRepository.save(productPostComment);
    }

    /**
     * Checks if there is a comment with the commentId
     * Checks if the actual owner is trying to change the comment
     * Checks if the new comment string is not null
     * Updates the comment with the new string.
     *
     * @param user               the authenticated user
     * @param commentId          the comment id
     * @return created ProductPostComment
     */
    public ProductPostComment updateComment(User user, Long commentId, String updatedComment) {
        ProductPostComment updatingComment = getCommentById(commentId);

        if (updatedComment != null && updatingComment.getCommentedBy() == user) {
            updatingComment.setComment(updatedComment);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment cannot be empty text or edited by other users");
        }
        updatingComment.setCommentTime(LocalDateTime.now());
        updatingComment.setCommentedBy(user);

        return productPostCommentRepository.save(updatingComment);
    }

    /**
     * @param id     the comment id
     * @return the comment
     */
    public ProductPostComment getCommentById(Long id) {
        Optional<ProductPostComment> optionalComment = productPostCommentRepository.findById(id);

        if (optionalComment.isPresent()) {
            return optionalComment.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No comment exists for given id");
        }
    }
}