package com.bmcho.deliveryservice.dg;

import com.bmcho.deliveryservice.enums.DeliveryStatus;
import com.bmcho.deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryStatusUpdater {

    private final DeliveryRepository deliveryRepository;

    @Scheduled(fixedDelay = 10000)
    public void deliveryStatusUpdate() {
        System.out.println("----------- delivery status update ------------");

        var inDeliveryList = deliveryRepository.findAllByStatus(DeliveryStatus.IN_DELIVERY);
        inDeliveryList.forEach(delivery -> {
            delivery.status = DeliveryStatus.COMPLETED;
            deliveryRepository.save(delivery);
        });

        var requestedList = deliveryRepository.findAllByStatus(DeliveryStatus.REQUESTED);
        requestedList.forEach(delivery -> {
            delivery.status = DeliveryStatus.IN_DELIVERY;
            deliveryRepository.save(delivery);
        });
    }
}
