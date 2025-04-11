package com.individual.online_store.exceptions;

public class OrderNotFound extends RuntimeException{
    public OrderNotFound(Long id) {super("Order not found" +id);}
}
