package com.bmcho.orderservice.feign;

import com.bmcho.orderservice.dto.ProcessDeliveryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "deliveryClient", url = "http://delivery-service:8080")
public interface DeliveryClient {
    @GetMapping(value = "/delivery/users/{userId}/first-address")
    Map<String, Object> getUserAddress(@PathVariable Long userId);

    @GetMapping(value = "/delivery/address/{addressId}")
    Map<String, Object> getAddress(@PathVariable Long addressId);

    @Deprecated(since = "Deprecated due to Kafka-based refactoring")
    @PostMapping(value = "/delivery/process-delivery")
    Map<String, Object> processDelivery(@RequestBody ProcessDeliveryDto dto);

    @GetMapping(value = "/delivery/deliveries/{deliveryId}")
    Map<String, Object> getDelivery(@PathVariable Long deliveryId);
}
