package com.hereyougo.delivery.order.repository;

import com.hereyougo.delivery.order.entity.Order;
import com.hereyougo.delivery.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByStatusAndCourierIsNull(OrderStatus status);


}
