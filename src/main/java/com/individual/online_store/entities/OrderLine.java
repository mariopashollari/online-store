package com.individual.online_store.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "order_lines")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderLineId;
    @ManyToOne
    private Product product;
    private Integer numberOfProducts;
    private Double orderLinePrice;
    @ManyToOne
    private Order order;

    public Double getOrderLinePrice() {
        return product.getPrice() * numberOfProducts;
    }


}
