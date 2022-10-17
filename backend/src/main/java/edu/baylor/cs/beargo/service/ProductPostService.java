package edu.baylor.cs.beargo.service;

import edu.baylor.cs.beargo.model.ProductPost;
import edu.baylor.cs.beargo.repository.ContractRepository;
import edu.baylor.cs.beargo.repository.ProductPostRepository;
import edu.baylor.cs.beargo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Transactional
public class ProductPostService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    ProductPostRepository productPostRepository;

    public ProductPost createProductPost(ProductPost productPost) {
        productRepository.save(productPost.getProduct());
//        contractRepository.save(productPost.getContract());
        return productPostRepository.save(productPost);
    }
}
