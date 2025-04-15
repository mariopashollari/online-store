package com.individual.online_store.controllers;


import com.individual.online_store.entities.OrderLine;
import com.individual.online_store.services.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order_line")
@RequiredArgsConstructor

public class OrderLineController {
    private final OrderLineService orderLineService;

    @PostMapping("/create")
    public OrderLine create(@RequestBody OrderLine orderLine) {
        return orderLineService.create(orderLine);
    }

    @GetMapping("/all")
    public List<OrderLine> findAll() {
        return orderLineService.getAllOrderLines();
    }

    @GetMapping("{orderLineId}")
    public OrderLine getById(@PathVariable Long orderLineId) {
        return orderLineService.findById(orderLineId);
    }

    @PutMapping("{orderLineId")
    public ResponseEntity<OrderLine> update(@PathVariable Long orderLineId, @RequestParam Integer amount) {
        OrderLine updatedOrderLine = orderLineService.updateOrderLine(orderLineId, amount);
        return ResponseEntity.ok(updatedOrderLine);
    }

    @DeleteMapping("/{orderLineId}")
    public ResponseEntity<Void> deleteOrderLine(@PathVariable Long orderLineId) {
        orderLineService.deleteOrderLine(orderLineId);
        return ResponseEntity.noContent()
                .build();
    }
}

