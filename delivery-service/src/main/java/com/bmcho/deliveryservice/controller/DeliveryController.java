package com.bmcho.deliveryservice.controller;

import com.bmcho.deliveryservice.dto.ProcessDeliveryDto;
import com.bmcho.deliveryservice.dto.RegisterAddressDto;
import com.bmcho.deliveryservice.entity.Delivery;
import com.bmcho.deliveryservice.entity.UserAddress;
import com.bmcho.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/delivery/addresses")
    public UserAddress registerAddress(@RequestBody RegisterAddressDto dto) {
        return deliveryService.addUserAddress(
                dto.userId,
                dto.address,
                dto.alias
        );
    }

    @PostMapping("/delivery/process-delivery")
    public Delivery processDelivery(@RequestBody ProcessDeliveryDto dto) {
        return deliveryService.processDelivery(
                dto.orderId,
                dto.productName,
                dto.productCount,
                dto.address
        );
    }

    @GetMapping("/delivery/deliveries/{deliveryId}")
    public Delivery getDelivery(@PathVariable Long deliveryId) {
        return deliveryService.getDelivery(deliveryId);
    }

    @GetMapping("/delivery/address/{addressId}")
    public UserAddress getAddress(@PathVariable Long addressId) throws Exception {
        return deliveryService.getAddress(addressId);
    }

    @GetMapping("/delivery/users/{userId}/first-address")
    public UserAddress getUserAddress(@PathVariable Long userId) throws Exception {
        return deliveryService.getUserAddress(userId);
    }
}
