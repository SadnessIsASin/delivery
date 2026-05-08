package com.hereyougo.delivery.order.entity;

public enum OrderStatus {
    NEW,           // только создан
    IN_PROGRESS,    // в обработке
    ASSIGNED,      // назначен курьер
    IN_DELIVERY,   // в доставке
    DELIVERED,     // доставлен
    CANCELLED      // отменён
}