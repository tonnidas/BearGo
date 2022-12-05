package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.dto.SearchDto;
import edu.baylor.cs.beargo.model.*;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
     * Checks if the status of the post is searching_traveler
     *
     * @return a list of ProductPosts
     */
    public List<ProductPost> getSearchingTravelerPosts() {
        List<ProductPost> allPosts = productPostRepository.findAllByOrderByCreatedAtDesc();
        List<ProductPost> searchPosts = new ArrayList<>();
        for (ProductPost pr : allPosts) {
            if (pr.getContract().getDeliveryStatus().equals(DeliveryStatus.SEARCHING_TRAVELER) && !pr.isBlocked()) {
                searchPosts.add(pr);
            }
        }
        return searchPosts;
    }

    /**
     * Checks if the logged user is the sender of the product post
     *
     * @param user the user
     * @return a list of product posts
     */
    public List<ProductPost> getProductPostsByUser(User user) {
        List<ProductPost> allPosts = productPostRepository.findAllByOrderByCreatedAtDesc();
        List<ProductPost> searchPosts = new ArrayList<>();
        for (ProductPost pr : allPosts) {
            if (pr.getContract().getSender().getId().equals(user.getId())) {
                searchPosts.add(pr);
            }
        }
        return searchPosts;
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

        productPost.setBlocked(false);
        productPost.setCreatedAt(LocalDateTime.now());

        if (productPost.getProduct() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product cannot be empty");
        }
        productRepository.save(productPost.getProduct());

        if (productPost.getExpectedPickupDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pick up date cannot be before today");
        }

        if (productPost.getExpectedPickupDate().isAfter(productPost.getExpectedDeliveryDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Delivery date cannot be before pick up date");
        }

        // create contract
        Contract contract = new Contract();
        contract.setDescription(user.getUsername() + " is looking for a traveler to deliver a product within " + productPost.getExpectedDeliveryDate() + ".");
        contract.setContractStartDate(productPost.getExpectedPickupDate());
        contract.setContractEndDate(productPost.getExpectedDeliveryDate());
        contract.setDeliveryStatus(DeliveryStatus.SEARCHING_TRAVELER);
        contract.setProductPost(productPost);
        contract.setSender(user);
        contract.setCost(0.0);
        contractRepository.save(contract);

        // set contract to product post
        productPost.setContract(contract);
        return productPostRepository.save(productPost);
    }

    public ProductPost updateProductPost(User user, ProductPost newProductPost) {
        Optional<ProductPost> opt = productPostRepository.findById(newProductPost.getId());
        if (!opt.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No product post record exist for given id");
        }

        ProductPost oldProductPost = opt.get();

        if (!oldProductPost.getContract().getSender().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Only sender can update the product post");
        }

        if (newProductPost.getExpectedPickupDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pick up date cannot be before today");
        }

        if (newProductPost.getExpectedPickupDate().isAfter(newProductPost.getExpectedDeliveryDate())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Delivery date cannot be before pick up date");
        }

        Contract oldContract = oldProductPost.getContract();
        Product oldProduct = oldProductPost.getProduct();
        Address oldSourceAddress = oldProductPost.getSource();
        Address oldDestinationAddress = oldProductPost.getDestination();

        // update from new product post

        // contract
        oldContract.setContractStartDate(newProductPost.getExpectedPickupDate());
        oldContract.setContractEndDate(newProductPost.getExpectedDeliveryDate());

        // product
        oldProduct.setDescription(newProductPost.getProduct().getDescription());
        oldProduct.setWeight(newProductPost.getProduct().getWeight());

        // source
        oldSourceAddress.setStreet(newProductPost.getSource().getStreet());
        oldSourceAddress.setCity(newProductPost.getSource().getCity());
        oldSourceAddress.setState(newProductPost.getSource().getState());
        oldSourceAddress.setZip(newProductPost.getSource().getZip());
        oldSourceAddress.setCountry(newProductPost.getSource().getCountry());

        // destination
        oldDestinationAddress.setStreet(newProductPost.getDestination().getStreet());
        oldDestinationAddress.setCity(newProductPost.getDestination().getCity());
        oldDestinationAddress.setState(newProductPost.getDestination().getState());
        oldDestinationAddress.setZip(newProductPost.getDestination().getZip());
        oldDestinationAddress.setCountry(newProductPost.getDestination().getCountry());

        // product post
        oldProductPost.setCreatedAt(LocalDateTime.now());
        oldProductPost.setDescription(newProductPost.getDescription());
        oldProductPost.setExpectedPickupDate(newProductPost.getExpectedPickupDate());
        oldProductPost.setExpectedDeliveryDate(newProductPost.getExpectedDeliveryDate());

        contractRepository.save(oldContract);
        productRepository.save(oldProduct);
        addressRepository.save(oldSourceAddress);
        addressRepository.save(oldDestinationAddress);

        return productPostRepository.save(oldProductPost);
    }

    public List<ProductPost> searchProductPost(SearchDto searchDto) {
        List<ProductPost> posts = productPostRepository.findAll();
        List<ProductPost> searchPosts = new ArrayList<>();
        for (ProductPost pr : posts) {
            String stDate = searchDto.getStartDate();
            String enDate = searchDto.getEndDate();
            if (stDate != null && enDate != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                LocalDate startdate = LocalDate.parse(stDate, formatter);
                LocalDate endDate = LocalDate.parse(enDate, formatter);
                LocalDate date1 = pr.getExpectedPickupDate();
                LocalDate date2 = pr.getExpectedDeliveryDate();

                if ((date1.isAfter(startdate) && date1.isBefore(endDate)) ||
                        (date2.isAfter(startdate) && date2.isBefore(endDate))) {
                    //searchPosts.add(pr);

                    if ((!searchDto.getSourceCity().equals("-") && !searchDto.getSourceState().equals("-")) &&
                            (!searchDto.getDestCity().equals("-") && !searchDto.getDestState().equals("-"))) {
                        if (pr.getSource().getCity().equals(searchDto.getSourceCity()) &&
                                pr.getDestination().getCity().equals(searchDto.getDestCity()))
                            searchPosts.add(pr);
                    } else if ((searchDto.getSourceCity().equals("-") && !searchDto.getSourceState().equals("-")) &&
                            searchDto.getDestCity().equals("-") && !searchDto.getDestState().equals("-")) {
                        System.out.println(pr.getDestination().getState());
                        if (pr.getSource().getState().equals(searchDto.getSourceState()) &&
                                pr.getDestination().getState().equals(searchDto.getDestState())) {
                            System.out.println(pr.getDestination().getState());
                            searchPosts.add(pr);
                        }
                    } else if ((searchDto.getSourceCity().equals("-") && searchDto.getSourceState().equals("-")) &&
                            (searchDto.getDestCity().equals("-") && searchDto.getDestState().equals("-"))) {
                        searchPosts.add(pr);
                    } else if (searchDto.getDestCity().equals("-") && searchDto.getDestState().equals("-")) {
                        if (pr.getSource().getCity().equals(searchDto.getSourceCity())) {
                            System.out.println(pr.getDestination().getState());
                            searchPosts.add(pr);
                        } else if (pr.getSource().getState().equals(searchDto.getSourceState())) {
                            System.out.println(pr.getDestination().getState());
                            searchPosts.add(pr);
                        }
                    } else if (searchDto.getSourceCity().equals("-") && searchDto.getSourceCity().equals("-")) {
                        if (pr.getDestination().getCity().equals(searchDto.getDestCity())) {
                            System.out.println(pr.getDestination().getState());
                            searchPosts.add(pr);
                        } else if (pr.getDestination().getState().equals(searchDto.getDestState())) {
                            System.out.println(pr.getDestination().getState());
                            searchPosts.add(pr);
                        }
                    }

                }
            } else if (stDate == null && enDate == null) {
                if ((!searchDto.getSourceCity().equals("-") && !searchDto.getSourceState().equals("-")) &&
                        (!searchDto.getDestCity().equals("-") && !searchDto.getDestState().equals("-"))) {
                    if (pr.getSource().getCity().equals(searchDto.getSourceCity()) &&
                            pr.getDestination().getCity().equals(searchDto.getDestCity()))
                        searchPosts.add(pr);
                } else if ((searchDto.getSourceCity().equals("-") && !searchDto.getSourceState().equals("-")) &&
                        searchDto.getDestCity().equals("-") && !searchDto.getDestState().equals("-")) {
                    System.out.println(pr.getDestination().getState());
                    if (pr.getSource().getState().equals(searchDto.getSourceState()) &&
                            pr.getDestination().getState().equals(searchDto.getDestState())) {
                        System.out.println(pr.getDestination().getState());
                        searchPosts.add(pr);
                    }
                } else if (searchDto.getDestCity().equals("-")) {
                    if (pr.getSource().getCity().equals(searchDto.getSourceCity())) {
                        System.out.println(pr.getDestination().getState());
                        searchPosts.add(pr);
                    } else if (pr.getSource().getState().equals(searchDto.getSourceState())) {
                        System.out.println(pr.getDestination().getState());
                        searchPosts.add(pr);
                    }
                } else if (searchDto.getSourceCity().equals("-")) {
                    if (pr.getDestination().getCity().equals(searchDto.getDestCity())) {
                        System.out.println(pr.getDestination().getState());
                        searchPosts.add(pr);
                    } else if (pr.getDestination().getState().equals(searchDto.getDestState())) {
                        System.out.println(pr.getDestination().getState());
                        searchPosts.add(pr);
                    }
                }

            } else if (stDate != null && enDate != null && searchDto.getSourceState().equals("-") && searchDto.getDestState().equals("-")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                LocalDate startdate = LocalDate.parse(stDate, formatter);
                LocalDate endDate = LocalDate.parse(enDate, formatter);
                LocalDate date1 = pr.getExpectedPickupDate();
                LocalDate date2 = pr.getExpectedDeliveryDate();

                if ((date1.isAfter(startdate) && date1.isBefore(endDate)) ||
                        (date2.isAfter(startdate) && date2.isBefore(endDate))) {
                    searchPosts.add(pr);
                }
            }
        }
        return searchPosts;
    }

    public List<ProductPost> getProductPostByCriteria(User user, String userType, String deliveryStatus) {
        List<ProductPost> posts = productPostRepository.findAll();
        List<ProductPost> senderPosts = new ArrayList<>();
        Long id = user.getId();
        System.out.println("del status " + deliveryStatus);
        for (ProductPost p : posts) {
            Contract c = p.getContract();
            System.out.println("cost " + p.getContract().getCost());
            if (userType.equals("sender")) {
                User u = c.getSender();
                Long uid = u.getId();
                String status = c.getDeliveryStatus().toString();
                if (Objects.equals(id, uid)) {
                    if (deliveryStatus != null && deliveryStatus.equals(status))
                        senderPosts.add(p);
                    else if (deliveryStatus.equals("NONE")) {
                        senderPosts.add(p);
                    }
                }

            } else if (userType.equals("traveler")) {
                User u = c.getTraveler();
                if (u == null) continue;
                Long uid = u.getId();
                String status = c.getDeliveryStatus().toString();
                if (Objects.equals(id, uid)) {
                    if (deliveryStatus != null && deliveryStatus.equals(status))
                        senderPosts.add(p);
                    else if (deliveryStatus.equals("NONE")) {
                        senderPosts.add(p);
                    }
                }
            }
        }
        return senderPosts;
    }

    /**
     * @return updated ProductPosts with interested users
     */
    public ProductPost updateInterestedPeople(User user, Long productPostId) {
        ProductPost productPost = getProductPostById(productPostId);

        if (productPost.getContract().getSender().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not show interest in your own product");
        }

        productPost.getInterestedPeoples().add(user);
        return productPostRepository.save(productPost);
    }

    /**
     * @return updated ProductPost
     */
    public ProductPost updateCost(Long productPostId, Double cost) {
        ProductPost productPost = getProductPostById(productPostId);
        productPost.getContract().setCost(cost);
        return productPostRepository.save(productPost);
    }
}
