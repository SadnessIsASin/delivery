package com.hereyougo.delivery.order.controller;

import com.hereyougo.delivery.order.dto.CreateOrderRequestDto;
import com.hereyougo.delivery.order.dto.OrderPageResponseDto;
import com.hereyougo.delivery.order.dto.OrderResponseDto;
import com.hereyougo.delivery.order.dto.UpdateOrderDto;
import com.hereyougo.delivery.order.entity.Order;
import com.hereyougo.delivery.order.mapper.OrderMapper;
import com.hereyougo.delivery.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<OrderPageResponseDto> getAllOrders(
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size) {
        Page<Order> orders = orderService.getAllOrders(page,size);
        return ResponseEntity.ok(OrderPageResponseDto.from(orders));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable UUID id){
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(OrderMapper.toDto(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody CreateOrderRequestDto dto){
        Order order = OrderMapper.toEntity(dto);
        Order savedOrder = orderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(OrderMapper.toDto(savedOrder));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> patchOrder(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateOrderDto dto){
        return ResponseEntity.ok(orderService.patchOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}