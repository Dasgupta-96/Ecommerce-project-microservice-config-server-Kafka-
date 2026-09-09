package com.codingshuttle.ecommerce.order_service.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    private Long id;
    private List<OrderRequestItemDto> items;
    private Double totalPrice;
}

