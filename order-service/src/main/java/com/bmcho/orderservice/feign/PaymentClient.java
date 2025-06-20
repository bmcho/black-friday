package com.bmcho.orderservice.feign;

import com.bmcho.orderservice.dto.ProcessPaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "paymentClient", url = "http://payment-service:8080")
public interface PaymentClient {
    @GetMapping(value = "/payment/users/{userId}/first-method")
    Map<String, Object> getPaymentMethod(@PathVariable Long userId);

    @Deprecated(since = "Deprecated due to Kafka-based refactoring")
    @PostMapping(value = "/payment/process-payment")
    Map<String, Object> processPayment(@RequestBody ProcessPaymentDto dto);

    @GetMapping(value = "/payment/payments/{paymentId}")
    Map<String, Object> getPayment(@PathVariable Long paymentId);
}
