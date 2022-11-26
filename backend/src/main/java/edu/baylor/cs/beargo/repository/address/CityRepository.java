package edu.baylor.cs.beargo.repository.address;

import edu.baylor.cs.beargo.model.address.City;
import edu.baylor.cs.beargo.model.address.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByState(State state);

    City findByCity(String city);
}
