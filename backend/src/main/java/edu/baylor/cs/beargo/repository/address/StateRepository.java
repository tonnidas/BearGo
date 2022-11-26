package edu.baylor.cs.beargo.repository.address;

import edu.baylor.cs.beargo.model.address.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    State findByState(String state);
}
