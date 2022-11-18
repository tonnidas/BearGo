package edu.baylor.cs.beargo.repository;

import edu.baylor.cs.beargo.model.ProductPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPostRepository extends JpaRepository<ProductPost, Long> {

}
