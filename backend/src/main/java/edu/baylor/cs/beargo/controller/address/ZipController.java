package edu.baylor.cs.beargo.controller.address;

import edu.baylor.cs.beargo.dto.AddressDto;
import edu.baylor.cs.beargo.model.address.Zip;
import edu.baylor.cs.beargo.service.address.ZipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zip")
public class ZipController {

    @Autowired
    ZipService zipService;

    @GetMapping("/findByStateAndCity")
    public List<Zip> findByStateAndCity(@RequestParam("state") String stateName,
                                @RequestParam(value = "city") String cityName) {
        return zipService.findByStateAndCity(stateName, cityName);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody List<AddressDto> addressDtoList) {
        zipService.save(addressDtoList);
        return new ResponseEntity<>("Data saved", HttpStatus.OK);
    }
}
