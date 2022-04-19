package com.example.task02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
private Integer userId;
private String city;
private String street;
private String home;
private String paymentType;
private Integer[] productIds;
private Integer[] productAmounts;
}
