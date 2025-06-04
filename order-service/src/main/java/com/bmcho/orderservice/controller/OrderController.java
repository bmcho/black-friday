package com.bmcho.orderservice.controller;

import com.bmcho.orderservice.dto.FinishOrderDto;
import com.bmcho.orderservice.dto.ProductOrderDetailDto;
import com.bmcho.orderservice.dto.StartOrderDto;
import com.bmcho.orderservice.dto.StartOrderResponseDto;
import com.bmcho.orderservice.entity.ProductOrder;
import com.bmcho.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/start-order")
    public StartOrderResponseDto startOrder(@RequestBody StartOrderDto dto) throws Exception {
        return orderService.startOrder(dto.userId, dto.productId, dto.count);
    }

    @PostMapping("/order/finish-order")
    public ProductOrder finishOrder(@RequestBody FinishOrderDto dto) throws Exception {
        return orderService.finishOrder(dto.orderId, dto.paymentMethodId, dto.addressId);
    }

    @GetMapping("/order/users/{userId}/orders")
    public List<ProductOrder> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    @GetMapping("/order/orders/{orderId}")
    public ProductOrderDetailDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrderDetail(orderId);
    }
}
