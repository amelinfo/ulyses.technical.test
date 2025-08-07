package com.septeo.ulyses.technical.test.dto;

import com.septeo.ulyses.technical.test.entity.Vehicle;

public record VehicleDTO(
    Long id,
    String model,
    String year,
    String color,
    BrandDTO brand
) {
    public VehicleDTO(Vehicle vehicle) {
        this(
            vehicle.getId(),
            vehicle.getModel(),
            vehicle.getYear(),
            vehicle.getColor(),
            new BrandDTO(vehicle.getBrand())
        );
    }
}