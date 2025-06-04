package com.bmcho.catalogservice.controller;

import com.bmcho.catalogservice.cassandra.entity.Product;
import com.bmcho.catalogservice.dto.DecreaseStockCountDto;
import com.bmcho.catalogservice.dto.RegisterProductDto;
import com.bmcho.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping("/catalog/products")
    public Product registerProduct(@RequestBody RegisterProductDto dto) {
        return catalogService.registerProduct(
            dto.sellerId,
            dto.name,
            dto.description,
            dto.price,
            dto.stockCount,
            dto.tags
        );
    }

    @DeleteMapping("/catalog/products/{productId}")
    public void deleteProduct(@PathVariable Long productId) {
        catalogService.deleteProduct(productId);
    }

    @GetMapping("/catalog/products/{productId}")
    public Product getProductById(@PathVariable Long productId) throws Exception {
        return catalogService.getProductById(productId);
    }

    @GetMapping("/catalog/sellers/{sellerId}/products")
    public List<Product> getProductsBySellerId (@PathVariable Long sellerId) throws Exception {
        return catalogService.getProductsBySellerId(sellerId);
    }

    @PostMapping("/catalog/products/{productId}/decreaseStockCount")
    public Product decreaseStockCount(@PathVariable Long productId, @RequestBody DecreaseStockCountDto dto) {
        return catalogService.decreaseStockCount(productId, dto.decreaseCount);
    }
}
