package com.hereyougo.delivery.order.dto;

import lombok.Getter;

@Getter
public class CreateOrderRequestDto {
    private String pickupAddress;
    private String shippingAddress;
    private String orderItems;
}
