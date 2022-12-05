package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.DeliveryStatus;
import edu.baylor.cs.beargo.model.address.City;
import edu.baylor.cs.beargo.model.address.State;
import edu.baylor.cs.beargo.model.address.Zip;
import edu.baylor.cs.beargo.repository.ContractRepository;
import edu.baylor.cs.beargo.repository.address.CityRepository;
import edu.baylor.cs.beargo.service.address.CityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
class CityServiceTest {


    @Autowired
    CityRepository cityRepository;

    CityService cityService;

    @BeforeEach
    void setUp() {
        cityService = new CityService(cityRepository);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void createCity() {
        City city = new City();
        State state = new State();
        state.setState("Texas");
        city.setCity("Waco");
        cityService.save(city);
        City c = cityService.findById(Long.valueOf(1));
        assert c.getId() != null;
    }


}