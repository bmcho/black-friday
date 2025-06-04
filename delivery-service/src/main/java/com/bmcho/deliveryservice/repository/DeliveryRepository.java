package com.bmcho.deliveryservice.repository;


import com.bmcho.deliveryservice.entity.Delivery;
import com.bmcho.deliveryservice.enums.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findAllByOrderId(Long orderId);
    List<Delivery> findAllByStatus(DeliveryStatus status);
}
