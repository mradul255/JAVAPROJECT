package com.inventory;

import com.inventory.model.Product;
import com.inventory.repository.MockDatabase;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Inventory System ---");

        MockDatabase db = MockDatabase.getInstance();

        // 1. Create dummy data
        Product p1 = new Product("101", "Laptop", 55000.00);
        Product p2 = new Product("102", "Mouse", 450.00);

        // 2. Save to DB
        db.saveProduct(p1);
        db.saveProduct(p2);

        // 3. Add stock
        db.addStock("101", 10);
        db.addStock("102", 50);

        // 4. Print all products
        System.out.println("\n--- Current Inventory ---");
        List<Product> list = db.getAllProducts();
        
        for(Product p : list) {
            int qty = db.getStock(p.getId());
            System.out.println(p.getName() + " | Price: " + p.getPrice() + " | Qty: " + qty);
        }
    }
}