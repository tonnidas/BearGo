package edu.baylor.cs.beargo.repository;

import edu.baylor.cs.beargo.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
