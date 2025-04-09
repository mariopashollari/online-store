package com.individual.online_store.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long orderId;
    private LocalDateTime orderDate;
    private List<Long> orderLinesIds;
    private Double totalAmount;
    private String deliveryAddress;

}
