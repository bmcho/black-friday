package com.bmcho.deliveryservice.dg;

import blackfriday.protobuf.EdaMessage;
import com.bmcho.deliveryservice.entity.Delivery;
import com.bmcho.deliveryservice.enums.DeliveryStatus;
import com.bmcho.deliveryservice.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryStatusUpdater {

    private final DeliveryRepository deliveryRepository;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @Scheduled(fixedDelay = 10000)
    public void deliveryStatusUpdate() {
        System.out.println("----------- delivery status update ------------");

        var inDeliveryList = deliveryRepository.findAllByStatus(DeliveryStatus.IN_DELIVERY);
        inDeliveryList.forEach(delivery -> {
            delivery.status = DeliveryStatus.COMPLETED;
            deliveryRepository.save(delivery);

            publishStatusChange(delivery);
        });

        var requestedList = deliveryRepository.findAllByStatus(DeliveryStatus.REQUESTED);
        requestedList.forEach(delivery -> {
            delivery.status = DeliveryStatus.IN_DELIVERY;
            deliveryRepository.save(delivery);

            publishStatusChange(delivery);
        });
    }

    private void publishStatusChange(Delivery delivery) {
        // 배송 상태 publish
        var deliveryStatusMessage = EdaMessage.DeliveryStatusUpdateV1.newBuilder()
            .setOrderId(delivery.orderId)
            .setDeliveryId(delivery.id)
            .setDeliveryStatus(delivery.status.toString())
            .build();

        kafkaTemplate.send("delivery_status_update", deliveryStatusMessage.toByteArray());
    }
}
