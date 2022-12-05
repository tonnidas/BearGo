package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class SampleModels {

    public static Product getSampleProduct(Double w, String d) {
        Product product = new Product();
        product.setWeight(w);
        product.setDescription(d);
        return product;
    }

    public static ProductPostComplaint getSamplerProductPostComplaint() {
        ProductPostComplaint productPostComplaint = new ProductPostComplaint();

        productPostComplaint.setReason("Wrong Description");
        productPostComplaint.setIsResolved(Boolean.FALSE);
        productPostComplaint.setComplainDate(LocalDate.now());

        return productPostComplaint;
    }

    public static BlogPost getSamplerBlogPost() {
        BlogPost blogPost = new BlogPost();

        blogPost.setDescription("Blog Post 1");
        blogPost.setPostedDateTime(LocalDateTime.now());

        return blogPost;
    }

    public static ProductPostComment getSampleProductPostComment() {
        ProductPostComment productPostComment = new ProductPostComment();
        productPostComment.setComment("nice");
        return productPostComment;
    }

    public static ProductPost getSampleProductPost() {
        ProductPost productPost = new ProductPost();

        productPost.setDescription("Product Post 1");
        productPost.setExpectedPickupDate(LocalDate.now());
        productPost.setExpectedDeliveryDate(LocalDate.now());

        Address address = new Address();
        address.setStreet("1825");
        address.setCity("Waco");
        address.setState("TX");
        address.setZip("76706");
        address.setCountry("USA");
        productPost.setSource(address);
        productPost.setDestination(address);

        Product product = new Product();
        product.setDescription("Product 1");
        product.setWeight(10.0);
        productPost.setProduct(product);

        return productPost;
    }
    public static Contract getSampleContract() {
        Contract contract = new Contract();
        contract.setDescription("New contract created");
        contract.setCost(30.2);
        contract.setContractStartDate(LocalDate.of(2022, Month.DECEMBER, 10));
        contract.setContractEndDate(LocalDate.of(2022, Month.DECEMBER, 30));
        contract.setDeliveryStatus(DeliveryStatus.INITIATED);
        return contract;
    }

    public static ProductPostComplaint getSampleComplaints() {
        ProductPostComplaint productPostComplaint = new ProductPostComplaint();
        productPostComplaint.setIsResolved(false);
        productPostComplaint.setReason("Bad");
        productPostComplaint.setComplainDate(LocalDate.of(2022, Month.DECEMBER, 01));
        return productPostComplaint;
    }

    public static User getSampleAdmin(int suffix) {
        User user = new User();
        user.setUsername("admin" + suffix + "@beargo.com");
        user.setPassword("password");
        user.setFullname("admin");
        user.setIsAdmin(true);
        return user;
    }

    public static User getSampleUser(int suffix) {
        User user = new User();
        user.setUsername("user" + suffix + "@beargo.com");
        user.setPassword("password");
        user.setFullname("user");
        user.setIsAdmin(false);
        return user;
    }
}
