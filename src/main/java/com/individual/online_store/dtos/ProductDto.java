package com.individual.online_store.dtos;


import com.individual.online_store.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductDto {
    private Long productId;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String imageUrl;
    private Long authorId;
    private String authorName;

    public static Product toEntity(ProductDto productDto) {
        return Product.builder()
                .productId(productDto.getProductId())
                .title(productDto.getTitle())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .imageUrl(productDto.getImageUrl())
                .build();
    }

    public static ProductDto toDto(Product product) {
        ProductDto productDto = ProductDto.builder()
                .productId(product.getProductId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory().getName())
                .imageUrl(product.getImageUrl())
                .build();
        if (product.getAuthor() != null) {
            productDto.setAuthorName(product.getAuthor().getFirstName()
                    .concat(" ").concat(product.getAuthor().getLastName()));
        }
        return productDto;
    }

}
