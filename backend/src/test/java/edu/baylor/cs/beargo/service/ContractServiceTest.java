package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.DeliveryStatus;
import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.repository.ContractRepository;
import edu.baylor.cs.beargo.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static edu.baylor.cs.beargo.service.SampleModels.getSampleProduct;

@DataJpaTest
class ContractServiceTest {


    @Autowired
    ContractRepository contractRepository;

    ContractService contractService;

    @BeforeEach
    void setUp() {
        contractService = new ContractService(contractRepository);

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void createContract() {
//        Contract contract = new Contract();
//        contract.setDescription("New contract created");
//        contract.setCost(30.2);
//        contract.setContractStartDate(LocalDate.of(2022, Month.DECEMBER, 10));
//        contract.setContractEndDate(LocalDate.of(2022, Month.DECEMBER, 30));
//        contract.setDeliveryStatus(DeliveryStatus.INITIATED);
//        contractRepository.save(contract);
//        Long id= 1L;
//        Contract contract1 = contractService.getContractById(id);
//        assert contract1.getId() != null;
    }

    @Test
    void getContract() {
//        Contract contract = new Contract();
//        contract.setDescription("New contract created");
//        contract.setCost(30.2);
//        contract.setContractStartDate(LocalDate.of(2022, Month.DECEMBER, 10));
//        contract.setContractEndDate(LocalDate.of(2022, Month.DECEMBER, 30));
//        contract.setDeliveryStatus(DeliveryStatus.INITIATED);
//        contractRepository.save(contract);
//        List<Contract> contract1 = contractService.getContracts();
//        assert contract1.size() == 1;
    }


}