package com.individual.online_store.services;


import com.individual.online_store.dtos.OrderDto;
import com.individual.online_store.entities.Order;
import com.individual.online_store.entities.OrderLine;
import com.individual.online_store.exceptions.OrderNotFound;
import com.individual.online_store.repositories.OrderLineRepository;
import com.individual.online_store.repositories.OrderRepository;
import com.individual.online_store.repositories.UserRepository;
import com.individual.online_store.static_data.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineRepository orderLineRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    public Order createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setDateOfSubmission(LocalDateTime.now());
        order.setStatus(OrderStatus.NEW);
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        order.setUser(userService.findLoggedInUser());
        order = orderRepository.save(order);
        List<OrderLine> orderLines = orderLineRepository.findAllById(orderDto.getOrderLinesIds());
        Double total = 0.0;
        for (OrderLine orderLine : orderLines) {
            orderLine.setOrder(order);
            total = total + orderLine.getOrderLinePrice();
        }
        order.setTotalCost(total);
        orderLineRepository.saveAll(orderLines);
        return orderRepository.save(order);
    }

    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFound(id));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFound(id));
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }

    public Order update(OrderDto orderDto) {
        Order order = getOrder(orderDto.getOrderId());
        order.setDeliveryAddress(orderDto.getDeliveryAddress());
        return orderRepository.save(order);
    }

    public List<Order> getAllByUser() {
        return orderRepository.findOrderByUser(userService.findLoggedInUser().getEmail());
    }
}
