package com.microservices.order.controller;

import com.microservices.order.pojos.OrderItem;
import com.microservices.demo.POJO.User;
import com.microservices.order.services.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    public OrderController(OrderService orderService){

        this.orderService=orderService;
    }

    final private OrderService orderService;


   @KafkaListener(topics = "my_topic",groupId = "group-id")
    public void getKafkaUser(User user){

       System.out.println(user);
    }

    @PostMapping
    public ResponseEntity<OrderItem> addOrder(RequestEntity<OrderItem> requestEntity)
    {
//        OrderItem orderItem=requestEntity.getBody()
//        restTemplate.headForHeaders("http://localhost:8080:/user/kafka/")
        orderService.addAndUpdateOrder(requestEntity.getBody());
        return new ResponseEntity<OrderItem>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public OrderItem getOrder(@PathVariable Integer id)
    {
        return  orderService.getOrder(id);
    }

    @GetMapping
    public List<OrderItem> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PutMapping
    public void updateOrder(RequestEntity<OrderItem> requestEntity){
        OrderItem oldOrderItem =orderService.getOrder(requestEntity.getBody().getOrderId());
        OrderItem newOrderItem =requestEntity.getBody();
        BeanUtils.copyProperties(newOrderItem, oldOrderItem,"orderId");
        orderService.addAndUpdateOrder(oldOrderItem);

    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
    }

}
