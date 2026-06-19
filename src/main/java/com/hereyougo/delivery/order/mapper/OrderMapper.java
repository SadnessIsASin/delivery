package com.hereyougo.delivery.order.mapper;

import com.hereyougo.delivery.order.dto.CreateOrderRequestDto;
import com.hereyougo.delivery.order.dto.OrderResponseDto;
import com.hereyougo.delivery.order.dto.UpdateOrderDto;
import com.hereyougo.delivery.order.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toEntity(CreateOrderRequestDto dto);
    OrderResponseDto toDto(Order order);
    Order toEntity(UpdateOrderDto dto);
}
