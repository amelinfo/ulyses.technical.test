package com.septeo.ulyses.technical.test.service;

import com.septeo.ulyses.technical.test.entity.Brand;
import com.septeo.ulyses.technical.test.entity.Sales;
import com.septeo.ulyses.technical.test.entity.Vehicle;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.management.relation.RelationNotFoundException;

/**
 * Service interface for Sales operations.
 */
public interface SalesService {

    /**
     * Get all sales.
     *
     * @return a list of all sales
     */
    List<Sales> getAllSales();

    /**
     * Get a sales by its ID.
     *
     * @param id the ID of the sales to find
     * @return an Optional containing the sales if found, or empty if not found
     */
    Optional<Sales> getSalesById(Long id);

    /**
     * Get all sales for a specific brand.
     *
     * @param brandId the ID of the brand to filter sales
     * @return a list of sales for the given brand (empty if none found)
     */
    List<Sales> getSalesByBrandId(Long brandId);

    /**
     * Get all sales for a specific vehicle
     * 
     * @param vehicleId the ID of the vehicle to filter sales
     * @return a list of sales for the given vehicle (empty if none found)
     * @throws RelationNotFoundException 
     */
    List<Sales> getSalesByVehicleId(Long vehicleId);

}
