package com.bmcho.paymentservice.pg;

public interface CreditCardPaymentAdapter {
    Long processCreditCardPayment(Long amountKRW, String creditCardNumber);
}
