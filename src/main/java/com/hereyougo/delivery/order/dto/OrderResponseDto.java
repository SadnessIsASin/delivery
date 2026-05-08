package com.hereyougo.delivery.order.dto;

import com.hereyougo.delivery.order.entity.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class OrderResponseDto {
    private UUID id;
    private String pickupAddress;
    private String shippingAddress;
    private String orderItems;
    private OrderStatus status;
}
