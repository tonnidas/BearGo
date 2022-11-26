package edu.baylor.cs.beargo.repository;

import edu.baylor.cs.beargo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByWeightAsc();

    List<Product> findAllByOrderByWeightDesc();

    @Query(value = "SELECT * FROM PRODUCT WHERE WEIGHT > ?1", nativeQuery = true)
    List<Product> findByMinimumWeight(int weight);
}
