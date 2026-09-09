package com.codingshuttle.ecommerce.inventory_service.dto;

import com.codingshuttle.ecommerce.order_service.dto.OrderRequestItemDto;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {
    private List<OrderRequestItemDto> items;
    private String messages;
    private String mobileNumber;
}

