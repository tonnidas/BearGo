package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.DeliveryStatus;
import edu.baylor.cs.beargo.model.ProductPostComplaint;
import edu.baylor.cs.beargo.repository.ContractRepository;
import edu.baylor.cs.beargo.repository.ProductPostComplaintRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static edu.baylor.cs.beargo.service.SampleModels.getSampleComplaints;

@DataJpaTest
class ProductpostComplaintServiceTest {


    @Autowired
    ProductPostComplaintRepository productPostComplaintRepository;

    ProductPostComplaintService productPostComplaintService;

    @BeforeEach
    void setUp() {
        productPostComplaintService = new ProductPostComplaintService();

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void createComplaint() {
        ProductPostComplaint productPostComplaint = new ProductPostComplaint();
        productPostComplaint.setIsResolved(false);
        productPostComplaint.setReason("Bad");
        productPostComplaint.setComplainDate(LocalDate.of(2022, Month.DECEMBER, 01));
        productPostComplaintRepository.save(productPostComplaint);
        Long id = Long.valueOf(1);
        assert productPostComplaint.getId() != null;
    }



}