package com.hereyougo.delivery.courier.repository;

import com.hereyougo.delivery.courier.entity.Courier;
import com.hereyougo.delivery.courier.entity.CourierStatus;
import com.hereyougo.delivery.order.entity.Order;
import com.hereyougo.delivery.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourierRepository extends JpaRepository<Courier, UUID> {
    List<Courier> findCourierByStatus(CourierStatus status);
}