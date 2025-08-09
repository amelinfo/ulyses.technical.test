package com.septeo.ulyses.technical.test.service;

import com.septeo.ulyses.technical.test.entity.Sales;
import com.septeo.ulyses.technical.test.entity.Vehicle;
import com.septeo.ulyses.technical.test.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the SalesService interface.
 * This class provides the implementation for all sales-related operations.
 */
@Service
@Transactional(readOnly = false)
public class SalesServiceImpl implements SalesService {

    @Autowired
    private SalesRepository salesRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Sales> getAllSales(Pageable pageable) {
        return salesRepository.findAll(pageable);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Sales> getSalesById(Long id) {
        return salesRepository.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sales> getSalesByBrandId(Long brandId) {
        return salesRepository.findByBrandId(brandId);
    }

    @Override
    public List<Sales> getSalesByVehicleId(Long vehicleId) {
        List<Sales> sales = salesRepository.findByVehicleId(vehicleId);
        return sales;
    }

    /**
     * QuickSort has an average and best-case time complexity of O(n log n) and a worst-case of O(n²) (when poorly partitioned). 
     * It is in-place and faster than many other sorting algorithms for large datasets.
     * 
     * @param entries
     * @return List
     */
    private List<Map.Entry<Vehicle, Integer>> quickSort(List<Map.Entry<Vehicle, Integer>> entries) {
        if (entries.size() <= 1) {
            return entries;
        }

        Map.Entry<Vehicle, Integer> pivot = entries.get(entries.size() / 2);
        List<Map.Entry<Vehicle, Integer>> less = new ArrayList<>();
        List<Map.Entry<Vehicle, Integer>> equal = new ArrayList<>();
        List<Map.Entry<Vehicle, Integer>> greater = new ArrayList<>();

        for (Map.Entry<Vehicle, Integer> entry : entries) {
            int cmp = entry.getValue().compareTo(pivot.getValue());
            if (cmp > 0) {
                greater.add(entry);
            } else if (cmp < 0) {
                less.add(entry);
            } else {
                equal.add(entry);
            }
        }

        List<Map.Entry<Vehicle, Integer>> sorted = new ArrayList<>();
        sorted.addAll(quickSort(greater)); // Descending order (higher counts first)
        sorted.addAll(equal);
        sorted.addAll(quickSort(less));

        return sorted;
    }

    @Override
    public List<Object[]> getTopSellingVehicles(LocalDate startDate, LocalDate endDate) {
        List<Sales> allSales = salesRepository.findAll();

        Map<Vehicle, Integer> vehicleCounts = new HashMap<>();

        // Filter and count
        for (Sales sale : allSales) {
            if ((startDate == null || !sale.getSaleDate().isBefore(startDate)) &&
                    (endDate == null || !sale.getSaleDate().isAfter(endDate))) {

                vehicleCounts.merge(sale.getVehicle(), 1, Integer::sum);
            }

        }

        // Convert to ArrayList for sorting
        List<Map.Entry<Vehicle, Integer>> entries = new ArrayList<>(vehicleCounts.entrySet());

        // Sort using QuickSort
        List<Map.Entry<Vehicle, Integer>> sortedEntries = quickSort(entries);

        // Prepare result
        return sortedEntries.stream()
                .limit(5)
                .map(entry -> new Object[] { entry.getKey(), entry.getValue() })
                .collect(Collectors.toList());

    }

}
