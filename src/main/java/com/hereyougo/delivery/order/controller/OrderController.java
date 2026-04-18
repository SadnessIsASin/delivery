package com.hereyougo.delivery.order.controller;

import com.hereyougo.delivery.order.dto.CreateOrderDto;
import com.hereyougo.delivery.order.entity.Order;
import com.hereyougo.delivery.order.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID id){
            return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderDto createOrderDto){
        Order order = new Order();
        order.setPickupAddress(createOrderDto.getPickupAddress());
        order.setOrderItems(createOrderDto.getOrderItems());

        Order savedOrder = orderService.saveOrder(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) throws Exception {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}
