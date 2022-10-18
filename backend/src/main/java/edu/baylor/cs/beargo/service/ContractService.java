package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.repository.ContractRepository;
import edu.baylor.cs.beargo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ContractService {

    @Autowired
    ContractRepository contractRepository;

    public List<Contract> getContracts() {
        return contractRepository.findAll();
    }
}