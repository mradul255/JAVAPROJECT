package com.inventory.service;

import com.inventory.model.Product;
import com.inventory.repository.MockDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for Module 3: Reporting (Analytics).
 * Contains business logic for generating reports.
 */
public class ReportService {

    private final MockDatabase db;

    public ReportService() {
        this.db = MockDatabase.getInstance();
    }

    /**
     * Generates a report of all products below a certain stock threshold.
     *
     * @param threshold The stock level to check against.
     * @return A list of products that are at or below the threshold.
     */
    public List<Product> generateLowStockReport(int threshold) {
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product p : db.findAllProducts()) {
            if (db.getStock(p.getId()) <= threshold) {
                lowStockProducts.add(p);
            }
        }
        return lowStockProducts;
    }

    /**
     * Generates a full report of all products and their stock levels.
     *
     * @return A Map where Key=Product and Value=StockLevel.
     */
    public Map<Product, Integer> generateInventoryReport() {
        Map<Product, Integer> report = new HashMap<>();
        for (Product p : db.findAllProducts()) {
            int stock = db.getStock(p.getId());
            report.put(p, stock);
        }
        return report;
    }
}

