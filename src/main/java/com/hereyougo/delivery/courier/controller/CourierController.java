package com.hereyougo.delivery.courier.controller;

import com.hereyougo.delivery.courier.entity.Courier;
import com.hereyougo.delivery.courier.service.CourierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/couriers")
public class CourierController {

    private final CourierService courierService;

    public CourierController (CourierService courierService){
        this.courierService = courierService;
    }

    @GetMapping
    public ResponseEntity<List<Courier>> getAllCouriers () {
        return ResponseEntity.ok(courierService.getAllCouriers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Courier> getCourierById(@PathVariable UUID id) {
        return ResponseEntity.ok(courierService.getCourierById(id));
    }

    @PostMapping
    public ResponseEntity<Courier> addCourier (@Valid @RequestBody Courier courier) {
        return ResponseEntity.ok(courierService.addCourier(courier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourier(@PathVariable UUID id){
        courierService.removeCourier(id);
        return ResponseEntity.noContent().build();
    }

}
