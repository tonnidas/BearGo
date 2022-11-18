package edu.baylor.cs.beargo.repository;

import edu.baylor.cs.beargo.model.ProductPostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPostCommentRepository extends JpaRepository<ProductPostComment, Long> {
}
