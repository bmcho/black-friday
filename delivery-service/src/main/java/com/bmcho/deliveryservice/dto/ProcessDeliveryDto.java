package com.bmcho.deliveryservice.dto;

@Deprecated(since = "Deprecated due to Kafka-based refactoring")
public class ProcessDeliveryDto {
    public Long orderId;
    public String productName;
    public Long productCount;
    public String address;
}
