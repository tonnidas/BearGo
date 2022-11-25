package edu.baylor.cs.beargo.dto;

import edu.baylor.cs.beargo.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPostDetails {
    ProductPost productPost;
    Product product;
    Address source;
    Address destination;
    Contract contract;
    User sender;
    User traveller;

    public ProductPostDetails(ProductPost productPost) {
        this.productPost = productPost;
        this.product = productPost.getProduct();
        this.source = productPost.getSource();
        this.destination = productPost.getDestination();
        this.contract = productPost.getContract();
        this.sender = productPost.getContract().getSender();
        this.traveller = productPost.getContract().getTraveler();
    }

    public static List<ProductPostDetails> convertProductPostList(List<ProductPost> productPosts) {
        List<ProductPostDetails> productPostDetails = new ArrayList<>();

        for (ProductPost productPost : productPosts) {
            productPostDetails.add(new ProductPostDetails(productPost));
        }

        return productPostDetails;
    }
}
