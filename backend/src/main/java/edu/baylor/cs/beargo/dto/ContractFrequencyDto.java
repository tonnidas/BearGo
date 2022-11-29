package edu.baylor.cs.beargo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContractFrequencyDto {
    private Long usedId;
    private Integer totalContractAsSender;
    private Integer totalDeliveredAsSender;
    private Integer totalUnsuccessfulAsSender;
    private Integer totalContractAsTraveler;
    private Integer totalDeliveredAsTraveler;
    private Integer totalUnsuccessfulAsTraveler;
}
