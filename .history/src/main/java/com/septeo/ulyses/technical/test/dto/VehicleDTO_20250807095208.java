package com.septeo.ulyses.technical.test.dto;

import com.septeo.ulyses.technical.test.entity.Vehicle;

public record VehicleDTO(
    Long id,
    String model,
    BrandDTO brand
) {
    public VehicleDTO(Vehicle vehicle) {
        this(
            vehicle.getId(),
            vehicle.getModel(),
            new BrandDTO(vehicle.getBrand())
        );
    }
}