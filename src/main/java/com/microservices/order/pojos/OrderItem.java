package com.microservices.order.pojos;

import lombok.*;

import javax.persistence.*;
import com.microservices.order.enums.Enums;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;

    private String itemName;
    private Integer itemPrice;
    private Integer userId;
    private String email;
    @Enumerated(EnumType.STRING)
    private Enums.OrderStaus status;

}
