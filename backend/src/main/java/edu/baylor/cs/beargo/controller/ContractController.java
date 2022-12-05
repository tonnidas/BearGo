package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.dto.UserContractsDto;
import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.ContractService;
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

    /**
     * @return a list of all contracts
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Contract>> getContracts() {
        List<Contract> contracts = contractService.getContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }

    /**
     * @param id the contract id
     * @return the contract
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contract> getContractById(@PathVariable("id") Long id) {
        Contract contract = contractService.getContractById(id);
        return new ResponseEntity<>(contract, HttpStatus.OK);
    }

    /**
     * @param user       the logged user
     * @param contractId the contract id
     * @return a boolean value
     */
    @GetMapping("/getReviewCompletion/{contractId}")
    public ResponseEntity<Boolean> getReviewCompletion(@AuthenticationPrincipal User user,
                                                       @PathVariable("contractId") Long contractId) {
        return new ResponseEntity<>(contractService.getReviewCompletion(user, contractId), HttpStatus.OK);
    }

    /**
     * @param userId         the user id
     * @param lookBackMonths the time
     * @return the user contract Dto
     */
    @GetMapping("/userContracts")
    public ResponseEntity<UserContractsDto> getContractByUserId(@RequestParam Long userId, @RequestParam int lookBackMonths) {
        UserContractsDto userContractsDto = contractService.getContractByUserIdByDate(userId, lookBackMonths);
        return new ResponseEntity<>(userContractsDto, HttpStatus.OK);
    }

    /**
     * @param user          the logged user
     * @param productPostId the product post id
     * @param travelerId    the traveler id
     * @return the updated contract
     */
    @PostMapping("/confirmContract/{productPostId}/{travelerId}")
    public ResponseEntity<Contract> confirmContract(@AuthenticationPrincipal User user,
                                                    @PathVariable("productPostId") Long productPostId,
                                                    @PathVariable("travelerId") Long travelerId) {
        Contract updatedContract = contractService.confirmContract(user, productPostId, travelerId);
        return new ResponseEntity<>(updatedContract, HttpStatus.OK);
    }

    /**
     * @param user       the logged user
     * @param contractId the contract id
     * @param newStatus  the new status of contract
     * @return the updated contract
     */
    @PostMapping("/updateStatus/{contractId}/{newStatus}")
    public ResponseEntity<Contract> updateStatus(@AuthenticationPrincipal User user,
                                                 @PathVariable("contractId") Long contractId,
                                                 @PathVariable("newStatus") String newStatus) {
        Contract updatedContract = contractService.updateContractStatus(user, contractId, newStatus);
        return new ResponseEntity<>(updatedContract, HttpStatus.OK);
    }

    /**
     * @param user       the logged user
     * @param contractId the contract id
     * @param cost       the cost
     * @return the updated contract
     */
    @PostMapping("/addCost/{contractId}/{cost}")
    public ResponseEntity<Contract> addCost(@AuthenticationPrincipal User user,
                                            @PathVariable("contractId") Long contractId,
                                            @PathVariable("cost") Double cost) {
        Contract updatedContract = contractService.updateCost(user, contractId, cost);
        return new ResponseEntity<>(updatedContract, HttpStatus.OK);
    }

    /**
     * @param user       the logged user
     * @param postid     the product post id
     * @param travelerid the traveler id
     * @param contractid the contract id
     * @param status     the new status
     * @param cost       the cost
     * @return the updated contract
     */
    @PostMapping("/updateEverything/{parampostid}/{travelervalue}/{contractid}/{status}/{cost}")
    public ResponseEntity<Contract> updateEverything(@AuthenticationPrincipal User user,
                                                     @PathVariable("parampostid") Long postid,
                                                     @PathVariable("travelervalue") Long travelerid,
                                                     @PathVariable("contractid") Long contractid,
                                                     @PathVariable("status") String status,
                                                     @PathVariable("cost") Double cost) {

        System.out.println("postid  " + postid);
        System.out.println("travelerid  " + travelerid);
        System.out.println("contractid  " + contractid);
        System.out.println("status  " + status);
        System.out.println("cost  " + cost);

        Contract updatedTraveler = contractService.confirmContract(user, postid, travelerid);
        Contract updatedContractStatus = contractService.updateContractStatus(user, contractid, status);
        Contract updatedContract = contractService.updateCost(user, contractid, cost);
        return new ResponseEntity<>(updatedContract, HttpStatus.OK);
    }
}
