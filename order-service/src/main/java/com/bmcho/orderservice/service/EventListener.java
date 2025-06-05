package com.bmcho.orderservice.service;

import blackfriday.protobuf.EdaMessage;
import com.bmcho.orderservice.dto.DecreaseStockCountDto;
import com.bmcho.orderservice.entity.ProductOrder;
import com.bmcho.orderservice.enums.OrderStatus;
import com.bmcho.orderservice.feign.CatalogClient;
import com.bmcho.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class EventListener {

    private final OrderRepository orderRepository;
    private final CatalogClient catalogClient;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    @KafkaListener(topics = "payment_result")
    public void consumePaymentResult(byte[] message) throws Exception {
        var object = EdaMessage.PaymentResultV1.parseFrom(message);
        log.info("[payment_result] consumed: {}", object);

        // 결제 정보 업데이트
        ProductOrder order = orderRepository.findById(object.getOrderId()).orElseThrow();
        order.paymentId = object.getPaymentId();
        order.orderStatus = OrderStatus.DELIVERY_REQUESTED;
        orderRepository.save(order);

        var product = catalogClient.getProduct(order.productId);
        var deliveryRequest = EdaMessage.DeliveryRequestV1.newBuilder()
            .setOrderId(order.id)
            .setProductName(product.get("name").toString())
            .setProductCount(order.count)
            .setAddress(order.deliveryAddress)
            .build();

        kafkaTemplate.send("delivery_request", deliveryRequest.toByteArray());
    }

    @KafkaListener(topics = "delivery_status_update")
    public void consumeDeliveryStatusUpdate(byte[] message) throws Exception {
        var object = EdaMessage.DeliveryStatusUpdateV1.parseFrom(message);
        log.info("[delivery_status_update] consumed: {}", object);

        if (object.getDeliveryStatus().equals("REQUESTED")) {
            var order = orderRepository.findById(object.getOrderId()).orElseThrow();

            // deliveryId 저장
            order.deliveryId = object.getDeliveryId();
            orderRepository.save(order);

            // 상품 재고 감소
            var decreaseStockCountDto = new DecreaseStockCountDto();
            decreaseStockCountDto.decreaseCount = order.count;
            catalogClient.decreaseStockCount(order.productId, decreaseStockCountDto);
        }
    }
}
