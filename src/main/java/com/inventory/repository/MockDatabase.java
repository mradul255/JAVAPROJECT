package com.inventory.repository;

import com.inventory.model.Product;

import java.util.*;

/**
 * Singleton mock database for storing products and stock.
 */
public class MockDatabase {
    
    private static MockDatabase instance;
    private final Map<String, Product> products;
    private final Map<String, Integer> stockLevels;
    
    private MockDatabase() {
        this.products = new HashMap<>();
        this.stockLevels = new HashMap<>();
    }
    
    public static MockDatabase getInstance() {
        if (instance == null) {
            instance = new MockDatabase();
        }
        return instance;
    }
    
    // Product methods
    public void saveProduct(Product product) {
        products.put(product.getId(), product);
    }
    
    public Optional<Product> findProductById(String id) {
        return Optional.ofNullable(products.get(id));
    }
    
    public Collection<Product> findAllProducts() {
        return products.values();
    }
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    public void deleteProductById(String id) {
        products.remove(id);
        stockLevels.remove(id); // Also remove stock when product is deleted
    }
    
    // Stock methods
    public void addStock(String productId, int quantity) {
        int currentStock = stockLevels.getOrDefault(productId, 0);
        stockLevels.put(productId, currentStock + quantity);
    }
    
    public int getStock(String productId) {
        return stockLevels.getOrDefault(productId, 0);
    }
    
    public void setStock(String productId, int quantity) {
        stockLevels.put(productId, quantity);
    }
    
    public void removeStock(String productId, int quantity) {
        int currentStock = getStock(productId);
        int newStock = Math.max(0, currentStock - quantity);
        stockLevels.put(productId, newStock);
    }
    
    // For testing purposes
    public void clearAll() {
        products.clear();
        stockLevels.clear();
    }
}
