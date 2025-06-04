package com.bmcho.deliveryservice.service;

import com.bmcho.deliveryservice.dg.DeliveryAdapter;
import com.bmcho.deliveryservice.entity.Delivery;
import com.bmcho.deliveryservice.entity.UserAddress;
import com.bmcho.deliveryservice.enums.DeliveryStatus;
import com.bmcho.deliveryservice.repository.DeliveryRepository;
import com.bmcho.deliveryservice.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final UserAddressRepository userAddressRepository;
    private final DeliveryRepository deliveryRepository;
    private final DeliveryAdapter deliveryAdapter;

    public UserAddress addUserAddress(Long userId, String address, String alias) {
        var userAddress = new UserAddress(userId, address, alias);

        return userAddressRepository.save(userAddress);
    }

    public Delivery processDelivery(
            Long orderId,
            String productName,
            Long productCount,
            String address
    ) {
        var refCode = deliveryAdapter.processDelivery(productName, productCount, address);
        var delivery = new Delivery(orderId, productName, productCount, address, refCode, DeliveryStatus.REQUESTED);

        return deliveryRepository.save(delivery);
    }

    public Delivery getDelivery(Long deliveryId) {
        return deliveryRepository.findById(deliveryId).orElseThrow();
    }

    public UserAddress getAddress(Long addressId) {
        return userAddressRepository.findById(addressId).orElseThrow();
    }

    public UserAddress getUserAddress(Long userId) {
        return userAddressRepository.findByUserId(userId).stream().findFirst().orElseThrow();
    }
}
