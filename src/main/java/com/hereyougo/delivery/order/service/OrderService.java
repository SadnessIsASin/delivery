package com.hereyougo.delivery.order.service;

import com.hereyougo.delivery.order.dto.UpdateOrderDto;
import com.hereyougo.delivery.order.entity.Order;
import com.hereyougo.delivery.exception.OrderNotFoundException;
import com.hereyougo.delivery.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    public Page<Order> getAllOrders(int page, int size){
        log.info("Запрос всех заказов");
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return orderRepository.findAll(pageable);
    }

    public Order getOrderById(UUID id) {
        log.info("Поиск заказа: {}", id);
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Заказ не найден: " + id));
    }

    public Order saveOrder(Order order){
        log.info("Сохранение нового заказа: {}", order.toString());
        return orderRepository.save(order);
    }

    public Order patchOrder(UUID id, UpdateOrderDto dto){
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Заказ не найден: " + id));

        if(dto.getPickupAddress() != null){
            existingOrder.setPickupAddress(dto.getPickupAddress());
        }
        if (dto.getShippingAddress() != null){
            existingOrder.setShippingAddress(dto.getShippingAddress());
        }
        if (dto.getOrderItems() != null){
            existingOrder.setOrderItems(dto.getOrderItems());
        }
        if (dto.getCourierId() != null){
            existingOrder.setCourier(dto.getCourierId());
        }

        existingOrder.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(existingOrder);
    }

    public void deleteOrder(UUID id) {
        log.info("Удаление заказа: {}", id);
        if(!orderRepository.existsById(id)){
            throw new OrderNotFoundException("Заказ не найден: " + id);
        }
        orderRepository.deleteById(id);
    }
}
