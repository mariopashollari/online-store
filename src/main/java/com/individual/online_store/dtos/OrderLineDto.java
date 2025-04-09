package com.individual.online_store.dtos;


import com.individual.online_store.entities.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OrderLineDto {
    private String productName;
    private Integer numberOfProducts;
    private Double unitPrice;

    public static OrderLineDto toDto(OrderLine orderLine) {
        return OrderLineDto.builder()
                .productName(orderLine.getProduct().getTitle())
                .numberOfProducts(orderLine.getNumberOfProducts())
                .unitPrice(orderLine.getProduct().getPrice())
                .build();
    }

}
