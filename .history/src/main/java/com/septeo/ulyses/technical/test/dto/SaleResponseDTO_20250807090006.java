package com.septeo.ulyses.technical.test.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.septeo.ulyses.technical.test.entity.Sales;

public record SaleResponseDTO(
    Long id,
    LocalDate saleDate,
    BigDecimal price,
    VehicleDTO vehicle
) {
    public SaleResponseDTO(Sales sale) {
        this(
            sale.getId(),
            sale.getSaleDate(),
            sale.getPrice(),
            new VehicleDTO(sale.getVehicle())
        );
    }
}