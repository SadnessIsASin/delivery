package com.hereyougo.delivery.assign.service;

import com.hereyougo.delivery.courier.entity.Courier;
import com.hereyougo.delivery.courier.entity.CourierStatus;
import com.hereyougo.delivery.courier.repository.CourierRepository;
import com.hereyougo.delivery.order.dto.UpdateOrderDto;
import com.hereyougo.delivery.order.entity.Order;
import com.hereyougo.delivery.order.entity.OrderStatus;
import com.hereyougo.delivery.order.repository.OrderRepository;
import com.hereyougo.delivery.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourierAssignmentService {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

     public CourierAssignmentService(
             OrderRepository orderRepository,
             OrderService orderService) {
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @Scheduled(fixedDelay = 5000)
    public void assignCourier () {
        List <Order> newOrders = searchNewOrder();
        if(newOrders.size() > 0) {
            for(int i = 0; i < newOrders.size(); i++){
                UUID orderId = newOrders.get(i).getId();
                UpdateOrderDto dto = new UpdateOrderDto();
                dto.setPickupAddress("автоматический адрес");
                orderService.patchOrder(orderId, dto);
            }
        }
    }

    public List<Order> searchNewOrder () {
        List<Order> newOrders = orderRepository.findByStatusAndCourierIsNull(OrderStatus.NEW);
        return newOrders;
    }
}
