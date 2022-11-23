package edu.baylor.cs.beargo.repository;

import edu.baylor.cs.beargo.model.BlogPost;
import edu.baylor.cs.beargo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    List<BlogPost> findAllByPostedBy(User user);

}
