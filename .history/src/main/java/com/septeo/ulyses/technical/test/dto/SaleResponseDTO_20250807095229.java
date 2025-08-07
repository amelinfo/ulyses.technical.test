package com.septeo.ulyses.technical.test.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.lang.Nullable;

import com.septeo.ulyses.technical.test.entity.Sales;

public record SaleResponseDTO(
    Long id,
    LocalDate saleDate,
    BigDecimal price,
    VehicleDTO vehicle,
    BrandDTO brand
) {
    public SaleResponseDTO(Sales sale) {
        this(
            sale.getId(),
            sale.getSaleDate(),
            sale.getPrice(),
            new VehicleDTO(sale.getVehicle()),
            new BrandDTO(sale.getBrand())
        );
    }
}