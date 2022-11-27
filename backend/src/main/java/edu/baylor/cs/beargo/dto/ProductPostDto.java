package edu.baylor.cs.beargo.dto;

import edu.baylor.cs.beargo.model.Address;
import edu.baylor.cs.beargo.model.Product;
import edu.baylor.cs.beargo.model.ProductPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPostDto {
    private Long id;
    private LocalDateTime createdAt;
    private String description;
    private Long imageId;
    private LocalDate expectedPickupDate;
    private LocalDate expectedDeliveryDate;
    private Address source;
    private Address destination;

    private Product product;
    private ContractDto contract;

    private List<ProductPostCommentDto> comments = new ArrayList<>();

    public static List<ProductPostDto> getProductPostDtoList(List<ProductPost> productPosts) {
        ModelMapper modelMapper = new ModelMapper();
        List<ProductPostDto> productPostDtoList = new ArrayList<>();

        for (ProductPost productPost : productPosts) {
            ProductPostDto productPostDto = modelMapper.map(productPost, ProductPostDto.class);
            productPostDtoList.add(productPostDto);
        }

        return productPostDtoList;
    }
}
