package edu.baylor.cs.beargo.repository.address;

import edu.baylor.cs.beargo.model.address.City;
import edu.baylor.cs.beargo.model.address.Zip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZipRepository extends JpaRepository<Zip, Long> {

    List<Zip> findByCity(City city);

    Zip findByZip(Integer zip);
}
