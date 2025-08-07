package com.septeo.ulyses.technical.test.repository;

import com.septeo.ulyses.technical.test.entity.Sales;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Sales entity.
 */
@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    /**
     * Find all sales.
     *
     * @return a list of all sales
     */
     Page<Sales> findAll(Pageable pageable);

    /**
     * Find a sale by its ID.
     *
     * @param id the ID of the sale to find
     * @return an Optional containing the sale if found, or empty if not found
     */
    Optional<Sales> findById(Long id);

    /**
     * Find a sale by its brandID
     * 
     * @param brandId the id of the brand
     * @return a list of sales if found, or empty list if not found
     */
    List<Sales> findByBrandId(Long brandId);

    /**
     * Find a sale by its vehicleId
     * 
     * @param vehicleId the id of the vehicle
     * @return a list of sales if found, or empty list if not found
     */
	List<Sales> findByVehicleId(Long vehicleId);
}
