package com.hereyougo.delivery.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateOrderDto {
    private String pickupAddress;
    private String shippingAddress;
    private String orderItems;
    private UUID courierId;
}
