package com.inventory.service;


import com.inventory.exception.ProductNotFoundException;
import com.inventory.model.Product;
import com.inventory.repository.MockDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private final MockDatabase db;
    private final StockService stockService;

    public ProductService() {
        this.db = MockDatabase.getInstance();
        this.stockService = new StockService();
    }

    public Product addProduct(String id, String name, double price) {
        if (db.findProductById(id).isPresent()) {
            throw new IllegalArgumentException("Product ID '" + id + "' already exists.");
        }
        Product newProduct = new Product(id, name, price);
        db.saveProduct(newProduct);
        stockService.initStock(id);
        return newProduct;
    }

    public Product updateProduct(String id, String newName, double newPrice) {
        Product product = getProduct(id);
        product.setName(newName);
        product.setPrice(newPrice);
        db.saveProduct(product);
        return product;
    }

    public void deleteProduct(String id) {
        if (!db.findProductById(id).isPresent()) {
             throw new ProductNotFoundException("Product with ID '" + id + "' not found.");
        }
        db.deleteProductById(id);
    }

    public Product getProduct(String id) {
        return db.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID '" + id + "' not found."));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(db.findAllProducts());
    }
}