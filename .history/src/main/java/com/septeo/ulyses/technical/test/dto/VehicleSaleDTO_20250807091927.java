package com.septeo.ulyses.technical.test.dto;

import com.septeo.ulyses.technical.test.entity.Brand;
import com.septeo.ulyses.technical.test.entity.Vehicle;

public record VehicleSaleDTO(
    Long id,
    String model,
    BrandDTO brand,
    boolean brandMatchesSale
) {
    public VehicleSaleDTO(Vehicle vehicle) {
        this(
            vehicle.getId(),
            vehicle.getModel(),
            new BrandDTO(vehicle.getBrand()),
            true // Will be adjusted during SaleResponseDTO construction
        );
    }
    
    // Constructor used by SaleResponseDTO
    public VehicleSaleDTO(Vehicle vehicle, Brand saleBrand) {
        this(
            vehicle.getId(),
            vehicle.getModel(),
            new BrandDTO(vehicle.getBrand()),
            saleBrand == null || saleBrand.getId().equals(vehicle.getBrand().getId())
        );
    }
}