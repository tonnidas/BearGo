package edu.baylor.cs.beargo.repository;

import edu.baylor.cs.beargo.model.TwitterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwitterModelRepository extends JpaRepository<TwitterModel,Long> {
}
