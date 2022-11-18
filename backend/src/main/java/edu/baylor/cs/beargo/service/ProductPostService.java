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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    /**
     * @return all ProductPosts
     */
    public List<ProductPost> getProductPosts() {
        return productPostRepository.findAll();
    }

    /**
     * Checks if the product exists
     *
     * @param id the product id
     * @return product
     */
    public ProductPost getProductPostById(Long id) {
        Optional<ProductPost> optionalProductPost = productPostRepository.findById(id);
        if (optionalProductPost.isPresent()) {
            return optionalProductPost.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product post record exist for given id");
        }
    }

    /**
     * Checks if the source and destination for the product is given
     * Checks if the product instance is given
     * Creates a product instance and a contract instance
     * Sets contract start date null and contract end date as the product post expected delivery date
     * Sets a description to contract
     * Sets the product post to contract
     * Sets the logged user as sender to contract
     * Sets teh delivery status as "searching for traveler"
     *
     * @param user        the authenticated user
     * @param productPost the product post instance
     * @return created product post
     */
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
        contract.setContractStartDate(null); // not sure: LocalDate.now()
        contract.setContractEndDate(productPost.getExpectedDeliveryDate());
        contract.setDeliveryStatus(DeliveryStatus.SEARCHING_TRAVELER);
        contract.setProductPost(productPost);
        contract.setSender(user);
        contractRepository.save(contract);

        // set contract to product post
        productPost.setContract(contract);
        return productPostRepository.save(productPost);
    }

    // TODO: Update product post - Swapnil

    public ProductPost updateProductPost(User user, ProductPost productPost) {
        return productPostRepository.save(productPost);
    }
    public List<ProductPost> searchProductPost(String source, String destination, Date startDate, Date endDate)
    {
        List<ProductPost> posts = new ArrayList<>();
        return posts;
    }


    public ProductPost deleteProductPost( Long id) {
        productPostRepository.deleteById(id);
        return null;
    }

}
