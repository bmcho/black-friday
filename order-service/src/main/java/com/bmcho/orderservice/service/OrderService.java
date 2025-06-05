package com.bmcho.orderservice.service;

import blackfriday.protobuf.EdaMessage;
import com.bmcho.orderservice.dto.*;
import com.bmcho.orderservice.entity.ProductOrder;
import com.bmcho.orderservice.enums.OrderStatus;
import com.bmcho.orderservice.feign.CatalogClient;
import com.bmcho.orderservice.feign.DeliveryClient;
import com.bmcho.orderservice.feign.PaymentClient;
import com.bmcho.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;
    private final DeliveryClient deliveryClient;
    private final CatalogClient catalogClient;
    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public StartOrderResponseDto startOrder(Long userId, Long productId, Long count) {
        // 1. 상품 정보 조회
        var product = catalogClient.getProduct(productId);

        // 2. 결제 수단 정보 조회
        var paymentMethod = paymentClient.getPaymentMethod(userId);

        // 3. 배송지 정보 조회
        var address = deliveryClient.getUserAddress(userId);

        // 4. 주문 정보 생성
        var order = new ProductOrder(
                userId,
                productId,
                count,
                OrderStatus.INITIATED,
                null,
                null,
            null
        );
        orderRepository.save(order);

        var startOrderDto = new StartOrderResponseDto();
        startOrderDto.orderId = order.id;
        startOrderDto.paymentMethod = paymentMethod;
        startOrderDto.address = address;

        return startOrderDto;
    }

    public ProductOrder finishOrder(Long orderId, Long paymentMethodId, Long addressId) {
        var order = orderRepository.findById(orderId).orElseThrow();

        // 1. 상품 정보 조회
        var product = catalogClient.getProduct(order.productId);

        // 2. 결제
//        var processPaymentDto = new ProcessPaymentDto();
//        processPaymentDto.orderId = order.id;
//        processPaymentDto.userId = order.userId;
//        processPaymentDto.amountKRW = Long.parseLong(product.get("price").toString()) * order.count;
//        processPaymentDto.paymentMethodId = paymentMethodId;
//
//        var payment = paymentClient.processPayment(processPaymentDto);

        // 2. 결제 요청
        var message = EdaMessage.PaymentRequestV1.newBuilder()
            .setOrderId(order.id)
            .setUserId(order.userId)
            .setAmountKRW(Long.parseLong(product.get("price").toString()) * order.count)
            .setPaymentMethodId(paymentMethodId)
            .build();

        kafkaTemplate.send("payment_request", message.toByteArray());


        // 3. 주문 정보 업데이트
        var address = deliveryClient.getAddress(addressId);
        order.orderStatus = OrderStatus.PAYMENT_REQUEST;
        order.deliveryAddress = address.get("address").toString();
        return orderRepository.save(order);

//        // 3. 배송 요청
//        var address = deliveryClient.getAddress(addressId);
//
//        var processDeliveryDto = new ProcessDeliveryDto();
//        processDeliveryDto.orderId = order.id;
//        processDeliveryDto.productName = product.get("name").toString();
//        processDeliveryDto.productCount = order.count;
//        processDeliveryDto.address = address.get("address").toString();
//
//        var delivery = deliveryClient.processDelivery(processDeliveryDto);
//
//        // 4. 상품 재고 감소
//        var decreaseStockCountDto = new DecreaseStockCountDto();
//        decreaseStockCountDto.decreaseCount = order.count;
//
//        catalogClient.decreaseStockCount(order.productId, decreaseStockCountDto);

        // 5. 주문 정보 업데이트
//        order.paymentId = Long.parseLong(payment.get("id").toString());
//        order.deliveryId = Long.parseLong(delivery.get("id").toString());
//        order.orderStatus = OrderStatus.DELIVERY_REQUESTED;
//        return orderRepository.save(order);

    }

    public List<ProductOrder> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public ProductOrderDetailDto getOrderDetail(Long orderId) {
        var order = orderRepository.findById(orderId).orElseThrow();

        var paymentRes = paymentClient.getPayment(order.paymentId);
        var deliveryRes = deliveryClient.getDelivery(order.deliveryId);

        var dto = new ProductOrderDetailDto(
                order.id,
                order.userId,
                order.productId,
                order.paymentId,
                order.deliveryId,
                order.orderStatus,
                paymentRes.get("paymentStatus").toString(),
                deliveryRes.get("status").toString()
        );

        return dto;
    }
}
