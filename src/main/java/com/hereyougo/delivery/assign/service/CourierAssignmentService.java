package com.hereyougo.delivery.assign.service;

import com.hereyougo.delivery.courier.entity.Courier;
import com.hereyougo.delivery.courier.entity.CourierStatus;
import com.hereyougo.delivery.courier.repository.CourierRepository;
import com.hereyougo.delivery.order.entity.Order;
import com.hereyougo.delivery.order.entity.OrderStatus;
import com.hereyougo.delivery.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CourierAssignmentService {

    private final OrderRepository orderRepository;
    private final CourierRepository courierRepository;

    public CourierAssignmentService(
             OrderRepository orderRepository,
             CourierRepository courierRepository) {
        this.orderRepository = orderRepository;
        this.courierRepository = courierRepository;
    }

    @Scheduled(fixedDelay = 5000)
    public void assignCourier () {
        log.info("Поиск новых заказов");
        List<Order> newOrders = searchNewOrders();
        if(newOrders.isEmpty()){
            log.info("Новых заказов нет");
            return;
        }

        log.info("Найдено новых заказов: {}", newOrders.size() );

        log.info("Поиск свободных курьеров");
        List<Courier> freeCouriers = searchFreeCouriers();
        if(freeCouriers.isEmpty()){
            log.info("Свободных курьеров нет");
            return;
        }

        log.info("Найдено свободных курьеров {}", freeCouriers.size());

        for (int i = 0; i < newOrders.size() && i < freeCouriers.size(); i++){
            Order order = newOrders.get(i);
            Courier courier = freeCouriers.get(i);

            try {
                assignCourierToOrder(order, courier);
            } catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    @Transactional
    public void assignCourierToOrder (Order order, Courier courier) {
        order.setCourier(courier.getId());
        order.setUpdatedAt(LocalDateTime.now());
        courier.setStatus(CourierStatus.BUSY);
        courier.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    public List<Order> searchNewOrders () {
        return orderRepository.findByStatusAndCourierIsNull(OrderStatus.NEW);
    }

    public List<Courier> searchFreeCouriers(){
        return courierRepository.findCourierByStatus(CourierStatus.FREE);
    }

}
