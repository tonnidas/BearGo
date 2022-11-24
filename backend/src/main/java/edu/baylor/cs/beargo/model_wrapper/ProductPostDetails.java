package edu.baylor.cs.beargo.model_wrapper;

import edu.baylor.cs.beargo.model.Contract;
import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.model.User;
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
    Contract contract;
    User sender;
    User traveller;

    public ProductPostDetails(ProductPost productPost) {
        this.productPost = productPost;
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
