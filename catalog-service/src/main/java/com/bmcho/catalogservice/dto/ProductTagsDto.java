package com.bmcho.catalogservice.dto;

import java.util.List;

@Deprecated(since = "Deprecated due to Kafka-based refactoring")
public class ProductTagsDto {
    public Long productId;
    public List<String> tags;
}
