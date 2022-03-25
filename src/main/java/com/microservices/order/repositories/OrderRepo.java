package com.microservices.order.repositories;


import com.microservices.order.pojos.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderItem,Integer> {
}
