package com.microservices.demo.POJO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Integer userId;
    private String email;
    private String userName;
    private String phoneNum;
}
