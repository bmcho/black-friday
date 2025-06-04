package com.bmcho.deliveryservice.dg;

public interface DeliveryAdapter {
    Long processDelivery(String productName, Long productCount, String address);
}
