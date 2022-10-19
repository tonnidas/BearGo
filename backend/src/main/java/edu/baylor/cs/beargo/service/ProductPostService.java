package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.DeliveryStatus;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.User;
import edu.baylor.cs.beargo.repository.AddressRepository;
import edu.baylor.cs.beargo.repository.ContractRepository;
import edu.baylor.cs.beargo.repository.ProductPostRepository;
import edu.baylor.cs.beargo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class ProductPostService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ProductPostRepository productPostRepository;

    // create product post
    public ProductPost createProductPost(User user, ProductPost productPost) {
        if (productPost.getSource() == null || productPost.getDestination() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Source and destination cannot be empty");
        }
        addressRepository.save(productPost.getSource());
        addressRepository.save(productPost.getDestination());

        if (productPost.getProduct() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product cannot be empty");
        }
        productRepository.save(productPost.getProduct());

        // create contract
        Contract contract = new Contract();
        contract.setDescription(user.getUsername() + " is looking for a traveler to deliver a product within " + productPost.getExpectedDeliveryDate() + ".");
        contract.setContractStartDate(LocalDate.now()); // not sure
        contract.setContractEndDate(productPost.getExpectedDeliveryDate());
        contract.setDeliveryStatus(DeliveryStatus.SEARCHING_TRAVELER);
        contract.setProductPost(productPost);
        contract.setSender(user);
        contractRepository.save(contract);

        // set contract to product post
        productPost.setContract(contract);
        return productPostRepository.save(productPost);
    }

    public ProductPost findProductPostById(Long id) {
        Optional<ProductPost> optionalProductPost = productPostRepository.findById(id);
        if (optionalProductPost.isPresent()) {
            return optionalProductPost.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product post record exist for given id");
        }
    }

    // TODO: update product post - Swapnil
}
