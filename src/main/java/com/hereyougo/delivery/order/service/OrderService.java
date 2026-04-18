package com.hereyougo.delivery.order.service;

import com.hereyougo.delivery.order.entity.Order;
import com.hereyougo.delivery.order.exception.OrderNotFoundException;
import com.hereyougo.delivery.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    public List<Order> getAllOrders(){
        log.info("Запрос всех заказов");
        List<Order> orders = orderRepository.findAll();
        log.info("Найдено заказов: {}",orders.size());
        return orders;
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

    public void deleteOrder(UUID id) throws Exception {
        log.info("Удаление заказа: {}", id);
        if(!orderRepository.existsById(id)){
            throw new OrderNotFoundException("Заказ не найден: " + id);
        }
        orderRepository.deleteById(id);
    }
}
