package com.bmcho.paymentservice.dto;


import com.bmcho.paymentservice.enums.PaymentMethodType;

public class PaymentMethodDto {
    public Long userId;
    public PaymentMethodType paymentMethodType;
    public String creditCardNumber;
}
