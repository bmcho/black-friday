package com.bmcho.catalogservice.dto;

import java.util.List;

public class RegisterProductDto {

    public Long sellerId;
    public String name;
    public String description;
    public Long price;
    public Long stockCount;
    public List<String> tags;
}
