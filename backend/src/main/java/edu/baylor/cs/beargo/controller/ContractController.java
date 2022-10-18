package edu.baylor.cs.beargo.controller;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.service.ContractService;
import edu.baylor.cs.beargo.service.ProductPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    @Autowired
    ContractService contractService;

    @GetMapping
    public ResponseEntity<List<Contract>> contracts() {
        List<Contract> contracts = contractService.getContracts();
        return new ResponseEntity<>(contracts, HttpStatus.OK);
    }
}
