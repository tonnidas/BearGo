package edu.baylor.cs.beargo.dto;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.DeliveryStatus;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserContractsDto {
    Long userId;
    Set<Contract> senderContracts;
    Set<Contract> travellerContracts;
   // DeliveryStatus deliveryStatus;


}
