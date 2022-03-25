package com.microservices.order.services;

import com.microservices.order.pojos.OrderItem;
import com.microservices.order.repositories.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    public OrderService(OrderRepo orderRepo){
        this.orderRepo=orderRepo;
    }

    private OrderRepo orderRepo;

    public OrderItem getOrder(Integer id){
        return orderRepo.findById(id).get();
    }

    public void addAndUpdateOrder(OrderItem orderItem){
        orderRepo.saveAndFlush(orderItem);
    }

    public List<OrderItem> getAllOrders(){
        return orderRepo.findAll();
    }

    public void deleteOrder(Integer id){
        orderRepo.deleteById(id);
    }


}
