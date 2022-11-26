package edu.baylor.cs.beargo.controller.address;

import edu.baylor.cs.beargo.model.address.City;
import edu.baylor.cs.beargo.service.address.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/city")
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping("/findByState")
    public List<City> findByState(@RequestParam(value = "state") String state) {
        return cityService.findByState(state);
    }
}
