package edu.baylor.cs.beargo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Integer zip_code;
    private Float latitude;
    private Float longitude;
    private String city;
    private String state;
    private String county;

//    {
//        "zip_code": 613,
//            "latitude": 18.458093,
//            "longitude": -66.732732,
//            "city": "Arecibo",
//            "state": "PR",
//            "county": "Arecibo"
//    }
}
