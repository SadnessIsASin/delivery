package com.hereyougo.delivery.courier.controller;

import com.hereyougo.delivery.courier.dto.CourierResponseDto;
import com.hereyougo.delivery.courier.entity.Courier;
import com.hereyougo.delivery.courier.mapper.CourierMapper;
import com.hereyougo.delivery.courier.service.CourierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(CourierController.API_PATH)
public class CourierController {

    public static final String API_PATH = "api/v1/couriers";

    private final CourierService courierService;
    private final CourierMapper courierMapper;

    public CourierController (CourierService courierService, CourierMapper courierMapper){
        this.courierService = courierService;
        this.courierMapper = courierMapper;
    }

    @GetMapping
    public ResponseEntity<List<Courier>> getAllCouriers () {
        return ResponseEntity.ok(courierService.getAllCouriers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourierResponseDto> getCourierById(@PathVariable UUID id) {
        Courier courier = courierService.getCourierById(id);
        return ResponseEntity.ok(courierMapper.toDto(courier));
    }

    @PostMapping
    public ResponseEntity<Courier> addCourier (@Valid @RequestBody Courier courier) {
        return ResponseEntity.ok(courierService.addCourier(courier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourier(@PathVariable UUID id){
        courierService.deleteCourier(id);
        return ResponseEntity.noContent().build();
    }

}
