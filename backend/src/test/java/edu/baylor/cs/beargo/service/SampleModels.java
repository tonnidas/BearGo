package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.*;

import java.time.LocalDate;

public class SampleModels {
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

    public static User getSampleUser(int suffix) {
        User user = new User();
        user.setUsername("user" + suffix + "@beargo.com");
        user.setPassword("password");
        user.setFullname("user");
        user.setIsAdmin(false);
        return user;
    }
}
