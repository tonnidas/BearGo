package edu.baylor.cs.beargo.service.address;

import edu.baylor.cs.beargo.model.address.City;
import edu.baylor.cs.beargo.model.address.State;
import edu.baylor.cs.beargo.model.address.Zip;
import edu.baylor.cs.beargo.repository.address.CityRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class CityService {

    @Autowired
    StateService stateService;

    @Autowired
    CityRepository cityRepository;

    public City save(City city) {
        return cityRepository.save(city);
    }

    public List<City> findByState(String stateName) {
        State state = stateService.findByState(stateName);
        if(state == null) {
            return new ArrayList<>();
        }
        return cityRepository.findByState(state);
    }

    public City findById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }
}
