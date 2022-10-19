package edu.baylor.cs.beargo.repository;

import edu.baylor.cs.beargo.model.ProductPostComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPostComplaintRepository extends JpaRepository<ProductPostComplaint, Long> {
}
