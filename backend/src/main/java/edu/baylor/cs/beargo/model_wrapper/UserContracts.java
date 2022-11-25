package edu.baylor.cs.beargo.model_wrapper;

import edu.baylor.cs.beargo.model.Contract;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserContracts {
    Long userId;
    Set<Contract> senderContracts;
    Set<Contract> travellerContracts;
}
