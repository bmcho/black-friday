package com.bmcho.paymentservice.controller;

import com.bmcho.paymentservice.dto.PaymentMethodDto;
import com.bmcho.paymentservice.dto.ProcessPaymentDto;
import com.bmcho.paymentservice.entity.Payment;
import com.bmcho.paymentservice.entity.PaymentMethod;
import com.bmcho.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment/methods")
    public PaymentMethod registerPaymentMethod(@RequestBody PaymentMethodDto dto) {
        return paymentService.registerPaymentMethod(
              dto.userId,
              dto.paymentMethodType,
              dto.creditCardNumber
        );
    }

    @PostMapping("/payment/process-payment")
    public Payment processPayment(@RequestBody ProcessPaymentDto dto) throws Exception {
        return paymentService.processPayment(
                dto.userId,
                dto.orderId,
                dto.amountKRW,
                dto.paymentMethodId
        );
    }

    @GetMapping("/payment/users/{userId}/first-method")
    public PaymentMethod getPaymentMethod(@PathVariable Long userId) {
        return paymentService.getPaymentMethodByUser(userId);
    }
    @GetMapping("/payment/payments/{paymentId}")
    public Payment getPayment(@PathVariable Long paymentId) {
        return paymentService.getPayment(paymentId);
    }
}
