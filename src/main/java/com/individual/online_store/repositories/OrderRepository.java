package com.individual.online_store.repositories;

import com.individual.online_store.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query(value = "select o from Order o where o.user.email = :email")
    List<Order> findOrderByUser(@Param("email") String email);
}
