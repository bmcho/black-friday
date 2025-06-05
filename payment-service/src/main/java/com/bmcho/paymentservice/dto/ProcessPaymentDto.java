package com.bmcho.paymentservice.dto;

@Deprecated(since = "Deprecated due to Kafka-based refactoring")
public class ProcessPaymentDto {
    public Long userId;
    public Long orderId;
    public Long amountKRW;
    public Long paymentMethodId;
}
