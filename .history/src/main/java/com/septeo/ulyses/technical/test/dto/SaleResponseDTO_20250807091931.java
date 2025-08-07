package com.septeo.ulyses.technical.test.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.lang.Nullable;

import com.septeo.ulyses.technical.test.entity.Sales;

public record SaleResponseDTO(
    Long id,
    LocalDate saleDate,
    BigDecimal price,
    VehicleSaleDTO vehicle,
    @Nullable BrandDTO directBrand  // Only populated if different from vehicle's brand
) {
    public SaleResponseDTO(Sales sale) {
        this(
            sale.getId(),
            sale.getSaleDate(),
            sale.getPrice(),
            new VehicleSaleDTO(sale.getVehicle()),
            shouldIncludeDirectBrand(sale) 
                ? new BrandDTO(sale.getBrand()) 
                : null
        );
    }
    
    private static boolean shouldIncludeDirectBrand(Sales sale) {
        return sale.getBrand() != null && 
               !sale.getBrand().getId().equals(sale.getVehicle().getBrand().getId());
    }
}