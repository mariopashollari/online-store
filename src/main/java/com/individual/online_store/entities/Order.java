package com.individual.online_store.entities;


import com.individual.online_store.static_data.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Double totalCost;
    private String deliveryAddress;
    private LocalDateTime dateOfSubmission;
    @OneToMany(mappedBy = "order")
    private List<OrderLine> orderLines;
    @ManyToOne
    private User user;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

}
