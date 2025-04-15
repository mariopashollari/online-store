package com.individual.online_store.controllers;


import com.individual.online_store.dtos.OrderDto;
import com.individual.online_store.entities.Order;
import com.individual.online_store.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor

public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public Order create(@RequestBody OrderDto order) {return orderService.createOrder(order);}

    @GetMapping("/all")
    public List<Order> findAll() {return orderService.getAllOrders();}

    @GetMapping("/{orderId}")
    public Order getById(@PathVariable Long orderId) {return orderService.getOrder(orderId);}

    @PutMapping("/update")
    public Order update(@RequestBody OrderDto orderDto) {return orderService.update(orderDto);}

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Order> delete(@PathVariable Long orderId) {orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();}


    @GetMapping("/by_user")
    public List<Order> getAllByUser() {return orderService.getAllByUser();}

}
