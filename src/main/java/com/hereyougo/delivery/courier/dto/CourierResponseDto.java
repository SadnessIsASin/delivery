package com.hereyougo.delivery.courier.dto;

import com.hereyougo.delivery.courier.entity.CourierStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CourierResponseDto {
    private UUID id;
    private String name;
    private String phone;
    private CourierStatus status;
}
