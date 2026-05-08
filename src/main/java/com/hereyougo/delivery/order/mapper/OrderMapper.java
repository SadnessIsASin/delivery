package com.hereyougo.delivery.order.mapper;

import com.hereyougo.delivery.order.dto.CreateOrderRequestDto;
import com.hereyougo.delivery.order.dto.OrderResponseDto;
import com.hereyougo.delivery.order.dto.UpdateOrderDto;
import com.hereyougo.delivery.order.entity.Order;

public class OrderMapper {

    public static Order toEntity(CreateOrderRequestDto dto){
        return Order.builder()
                .pickupAddress(dto.getPickupAddress())
                .shippingAddress(dto.getShippingAddress())
                .orderItems(dto.getOrderItems())
                .build();
    }

    public static OrderResponseDto toDto(Order order){
        return OrderResponseDto.builder()
                .id(order.getId())
                .pickupAddress(order.getPickupAddress())
                .shippingAddress(order.getShippingAddress())
                .build();
    }

    public static Order toEntity(UpdateOrderDto dto){
        return Order.builder()
                .pickupAddress(dto.getPickupAddress())
                .shippingAddress(dto.getShippingAddress())
                .orderItems(dto.getOrderItems())
                .courier(dto.getCourierId())
                .build();
    }

}
