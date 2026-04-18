package com.hereyougo.delivery.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "shippingaddress")
    private String shippingAddress;

    @Column(name = "pickupaddress")
    private String pickupAddress;

    @Column(name = "orderitems")
    private String orderItems;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }
}
