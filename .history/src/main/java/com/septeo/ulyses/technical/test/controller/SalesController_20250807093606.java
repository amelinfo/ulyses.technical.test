package com.septeo.ulyses.technical.test.controller;

import com.septeo.ulyses.technical.test.dto.SaleResponseDTO;
import com.septeo.ulyses.technical.test.entity.Sales;
import com.septeo.ulyses.technical.test.entity.Vehicle;
import com.septeo.ulyses.technical.test.repository.SalesRepository;
import com.septeo.ulyses.technical.test.repository.VehicleRepository;
import com.septeo.ulyses.technical.test.service.SalesService;
import com.septeo.ulyses.technical.test.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.relation.RelationNotFoundException;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;
    private VehicleRepository vehicleRepository;
    private SalesRepository salesRepository;

    @GetMapping
    public ResponseEntity<List<Sales>> getAllSales() {
        return ResponseEntity.ok(salesService.getAllSales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSalesById(@PathVariable Long id) {
        return salesService.getSalesById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get all sales for a specific brand
     * @param brandId The ID of the brand to filter sales
     * @return List of sales for the given brand
     */
    @GetMapping("/brands/{brandId}")
    public ResponseEntity<List<Sales>> getSalesByBrandId(@PathVariable Long brandId) {
        List<Sales> sales = salesService.getSalesByBrandId(brandId);
        if (sales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sales);
    }

    /**
     * Get all sales for a specific vehicle
     * @param vehicleId The ID of the vehicle to filter sales
     * @return List of sales for the given vehicle
     * @throws RelationNotFoundException 
     */
    @GetMapping("/vehicles/{vehicleId}")
    public ResponseEntity<List<SaleResponseDTO>> getSalesByVehicleId(
            @PathVariable Long vehicleId) {
        
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new IllegalArgumentException());
           // .orElseThrow(() -> new NotFoundException());
        
        List<SaleResponseDTO> response = salesRepository.findByVehicleId(vehicleId)
            .stream()
            .map(sale -> {
                // Verify brand consistency
                if (sale.getBrand() != null && 
                    !sale.getBrand().getId().equals(vehicle.getBrand().getId())) {
                    System.out.println("Sales"+sale.getId()+" has inconsistent brand");
                }
                return new SaleResponseDTO(sale);
            })
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(response);
    }
}
