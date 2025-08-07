package com.septeo.ulyses.technical.test.dto;

import com.septeo.ulyses.technical.test.entity.Brand;

public record BrandDTO(
    Long id,
    String name,
    String description
) {
    public BrandDTO(Brand brand) {
        this(brand.getId(), brand.getName(), brand.getDescription());
    }
}