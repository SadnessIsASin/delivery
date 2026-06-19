package com.hereyougo.delivery.courier.mapper;

import com.hereyougo.delivery.courier.dto.CourierResponseDto;
import com.hereyougo.delivery.courier.entity.Courier;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CourierMapper {
    CourierResponseDto toDto (Courier courier);
}
