package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.ContractService;
import edu.baylor.cs.beargo.dto.UserContractsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    @Autowired
    ContractService contractService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Contract>> getContracts() {
        List<Contract> contracts = contractService.getContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable("id") Long id) {
        Contract contract = contractService.getContractById(id);
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

    @GetMapping("/userContracts")
    public ResponseEntity<UserContractsDto> getContractByUserId(@RequestParam Long userId, @RequestParam int lookBackMonths) {
        UserContractsDto userContractsDto = contractService.getContractByUserIdByDate(userId, lookBackMonths);
        return new ResponseEntity<>(userContractsDto, HttpStatus.OK);
    }

    @PostMapping("/confirmContract/{productPostId}/{travelerId}")
    public ResponseEntity<Contract> confirmContract(@AuthenticationPrincipal User user,
                                                    @PathVariable("productPostId") Long productPostId,
                                                    @PathVariable("travelerId") Long travelerId) {
        Contract updatedContract = contractService.confirmContract(user, productPostId, travelerId);
        return new ResponseEntity<>(updatedContract, HttpStatus.OK);
    }

    @PostMapping("/updateStatus/{contractId}/{newStatus}")
    public ResponseEntity<Contract> updateStatus(@AuthenticationPrincipal User user,
                                                 @PathVariable("contractId") Long contractId,
                                                 @PathVariable("newStatus") String newStatus) {
        Contract updatedContract = contractService.updateContractStatus(user, contractId, newStatus);
        return new ResponseEntity<>(updatedContract, HttpStatus.OK);
    }
}
