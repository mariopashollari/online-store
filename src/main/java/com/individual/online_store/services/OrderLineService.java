package com.individual.online_store.services;


import com.individual.online_store.entities.OrderLine;
import com.individual.online_store.exceptions.OrderNotFound;
import com.individual.online_store.repositories.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class OrderLineService {
    private final OrderLineRepository orderLineRepository;

    public OrderLine create(OrderLine orderLine){
        orderLine.setOrderLinePrice(orderLine.getOrderLinePrice());
        return orderLineRepository.save(orderLine);
    }

    public OrderLine findById(Long id){
        return orderLineRepository.findById(id)
                .orElseThrow(() -> new OrderNotFound(id));
    }

    public List<OrderLine> getAllOrderLines(){
        return orderLineRepository.findAll();
    }

    public OrderLine updateOrderLine(Long orderLineId, Integer amount) {
        OrderLine orderLine=orderLineRepository.findById(orderLineId)
                .orElseThrow(()-> new OrderNotFound(orderLineId));
        orderLine.setNumberOfProducts(amount);
        return orderLineRepository.save(orderLine);
    }

    public void deleteOrderLine(Long orderLineId) {
        OrderLine orderLine=orderLineRepository.findById(orderLineId)
                .orElseThrow(()-> new OrderNotFound(orderLineId));
        orderLineRepository.delete(orderLine);
    }
}
