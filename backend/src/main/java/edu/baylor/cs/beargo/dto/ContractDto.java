package edu.baylor.cs.beargo.dto;

import edu.baylor.cs.beargo.model.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractDto {
    private Long id;
    private String description;
    private Double cost;
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    private DeliveryStatus deliveryStatus;
    private UserDto sender;
    private UserDto traveler;
}
