package com.bmcho.orderservice.dto;

@Deprecated(since = "Deprecated due to Kafka-based refactoring")
public class ProcessPaymentDto {
    public Long orderId;
    public Long userId;
    public Long amountKRW;
    public Long paymentMethodId;
}
