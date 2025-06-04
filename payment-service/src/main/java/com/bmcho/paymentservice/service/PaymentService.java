package com.bmcho.paymentservice.service;

import com.bmcho.paymentservice.entity.Payment;
import com.bmcho.paymentservice.entity.PaymentMethod;
import com.bmcho.paymentservice.enums.PaymentMethodType;
import com.bmcho.paymentservice.enums.PaymentStatus;
import com.bmcho.paymentservice.pg.CreditCardPaymentAdapter;
import com.bmcho.paymentservice.repository.PaymentMethodRepository;
import com.bmcho.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentRepository paymentRepository;
    private final CreditCardPaymentAdapter creditCardPaymentAdapter;

    public PaymentMethod registerPaymentMethod(
            Long userId,
            PaymentMethodType paymentMethodType,
            String creditCardNumber
    ) {
        var paymentMethod = new PaymentMethod(userId, paymentMethodType, creditCardNumber);
        return paymentMethodRepository.save(paymentMethod);
    }

    public Payment processPayment(
            Long userId,
            Long orderId,
            Long amountKRW,
            Long paymentMethodId
    ) throws Exception {
       var paymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow();

       if(paymentMethod.paymentMethodType != PaymentMethodType.CREDIT_CARD) {
           throw new Exception("Unsupported payment method type");
       }

       var refCode = creditCardPaymentAdapter.processCreditCardPayment(amountKRW, paymentMethod.creditCardNumber);

       var payment = new Payment(userId,
               orderId,
               amountKRW,
               paymentMethod.paymentMethodType,
               paymentMethod.creditCardNumber,
               PaymentStatus.COMPLETED,
               refCode
       );

        return paymentRepository.save(payment);
    }

    public PaymentMethod getPaymentMethodByUser(Long userId) {
        return paymentMethodRepository.findByUserId(userId).stream().findFirst().orElseThrow();
    }

    public Payment getPayment(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow();
    }
}
