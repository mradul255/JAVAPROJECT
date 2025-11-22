package com.inventory.service;

import com.inventory.repository.MockDatabase;

/**
 * Service for Module 2: Stock Management.
 * Contains business logic for managing stock levels.
 */
public class StockService {
    
    private final MockDatabase db;
    
    public StockService() {
        this.db = MockDatabase.getInstance();
    }
    
    /**
     * Initializes stock for a new product (sets to 0).
     */
    public void initStock(String productId) {
        db.setStock(productId, 0);
    }
    
    /**
     * Gets the current stock level for a product.
     */
    public int getStock(String productId) {
        return db.getStock(productId);
    }
    
    /**
     * Sets stock to a specific quantity.
     */
    public void setStock(String productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }
        db.setStock(productId, quantity);
    }
    
    /**
     * Adds quantity to existing stock.
     */
    public void addStock(String productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot add negative quantity.");
        }
        db.addStock(productId, quantity);
    }
    
    /**
     * Removes quantity from existing stock.
     */
    public void removeStock(String productId, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Cannot remove negative quantity.");
        }
        int currentStock = db.getStock(productId);
        if (currentStock < quantity) {
            throw new IllegalArgumentException("Insufficient stock. Available: " + currentStock + ", Requested: " + quantity);
        }
        db.removeStock(productId, quantity);
    }
}
