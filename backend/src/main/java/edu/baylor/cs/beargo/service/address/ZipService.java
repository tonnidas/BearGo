package edu.baylor.cs.beargo.service.address;

import edu.baylor.cs.beargo.dto.AddressDto;
import edu.baylor.cs.beargo.model.address.City;
import edu.baylor.cs.beargo.model.address.State;
import edu.baylor.cs.beargo.model.address.Zip;
import edu.baylor.cs.beargo.repository.address.ZipRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class ZipService {

    @Autowired
    StateService stateService;

    @Autowired
    CityService cityService;
    @Autowired
    ZipRepository zipRepository;

    public List<Zip> findByStateAndCity(String stateName, String cityName) {
        List<City> cityList = cityService.findByState(stateName);
        for(City city: cityList) {
            if(city.getCity().equals(cityName)) {
                return zipRepository.findByCity(city);
            }
        }
        return new ArrayList<>();
    }

    public void save(List<AddressDto> addressDtoList) {
        HashMap<String, List<AddressDto>> map = new HashMap<>();
        for(int i = 0; i < addressDtoList.size(); i++) {
            String stateData = addressDtoList.get(i).getState();
            if(map.get(stateData) == null) {
                map.put(stateData, new ArrayList<>());
            }
            map.get(stateData).add(addressDtoList.get(i));
        }

        for(Map.Entry<String, List<AddressDto>> stateEntry : map.entrySet()) {

            String stateData = stateEntry.getKey();
            State stateDB = new State();
            stateDB.setState(stateData);
            stateDB = stateService.save(stateDB);

            HashMap<String, List<Integer>> cityAndZip = new HashMap<>();
            for(int i = 0; i < stateEntry.getValue().size(); i++) {
                String city = stateEntry.getValue().get(i).getCity();
                Integer zip = stateEntry.getValue().get(i).getZip_code();
                if(cityAndZip.get(city) == null) {
                    cityAndZip.put(city, new ArrayList<>());
                }
                cityAndZip.get(city).add(zip);
            }

            for(Map.Entry<String, List<Integer>> cityEntry : cityAndZip.entrySet()) {

                String cityData = cityEntry.getKey();
                City cityDB = new City();
                cityDB.setState(stateDB);
                cityDB.setCity(cityData);
                cityDB = cityService.save(cityDB);

                List<Integer> zipList = cityEntry.getValue();
                for(int i = 0; i < zipList.size(); i++) {

                    Integer zipData = zipList.get(i);

                    System.out.println("State - " + stateEntry.getKey() + ", City - " + cityEntry.getKey() + ", Zip - " + zipList.get(i));

                    Zip zip = new Zip();
                    zip.setZip(zipData);
                    zip.setCity(cityDB);
                    zipRepository.save(zip);
                }
            }
        }
    }

    public Zip findById(Long id) {
        return zipRepository.findById(id).orElse(null);
    }

    public Zip findByZip(Integer zip) {
        return zipRepository.findByZip(zip);
    }
}
