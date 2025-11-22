# Project Report: Small Business Inventory Management System
Course: VITYARTHI - Build My Own Projects
Author: Mradul Tripathi
Current Date: 22/11/2025

This document serves as the project report.

## INTRODUCTION
This report outlines the design, implementation, and evaluation process of a Small Business Inventory Management System. The project was developed through the VITyarthi "Build Your Own Project" program.

## Problem Statement
Business owners of small establishments often have a hard time keeping track of their inventory by hand. This leads to mistakes such as running out of a popular item (lost sales) or over-ordering perishable goods (waste and loss of money).

## Functional Requirements
The system has three functional modules:

## FR-Module 1: Product Management (CRUD)
FR1.1: The system shall allow a user to create a new product with a unique ID, name, and price.

FR1.2: The system shall allow a user to retrieve a product's information by ID.

FR1.3: The system shall allow a user to edit the name and price of an existing product.

FR1.4: The system shall allow a user to delete a product from the catalog.

FR1.5: The system shall allow a user to view a list of products.

FR-Module 2: Stock Management (Data Management)
FR2.1: The system shall initialize the stock for a new product to zero.

FR2.2: The system shall allow a user to update the stock quantity for a product (adding stock if the number is positive, and selling if the number is negative).

FR2.3: The system shall allow a user to retrieve the current stock for a product.

FR-Module 3: Reporting (Analytics)
FR3.1: The system shall create a "low stock report" displaying all products with a stock below a predetermined threshold (e.g., 10).

FR3.2: The system shall create a complete inventory report, displaying all products, pricing, and current stock.

## Non-Functional Requirements
NFR1: Usability: The system should be straightforward to navigate through a simple, text-based command-line interface (CLI). All menu options should be clear and understandable.

NFR2: Reliability: The system should accurately save and retrieve product and stock data for the duration of the session. It should gracefully handle incorrect user input and not crash when this occurs.

NFR3: Error Handling: The system should provide clear, user-friendly error messages for common issues (e.g., "Product not found," "Invalid number format").

NFR4: Maintainability: The code should be modular and adequately commented, with a clear separation of concerns (e.g., Presentation vs. Logic vs. Data) to allow for easy future edits and enhancements.

## System Architecture
The project consists of a 3-Tier Architecture, which uses Java packages:

Presentation Layer (com.inventory.Main)
Handles all interaction with the user (displays menus, reads input through Scanner).
It is responsible for formatting output for the console.
It contains no business logic; it invokes services to complete actions.

Business Logic Layer (com.inventory.service)
Contains the core application logic.
This layer has been separated into services, each of which is mapped to a functional module (ProductService, StockService, ReportService).
Services work together to perform functions (e.g., ProductService will invoke StockService when adding a product).

Data Access Layer (com.inventory.repository)
Responsible for all data storage and retrieval.
It uses a MockDatabase class (implemented as a Singleton) that uses HashMaps to store and manage data in memory.
This layer abstracts away the data source so that, in the future, it could be changed to a real SQL database without any changes needed in the Business Logic.

## Design Diagrams
Use Case Diagram
This diagram illustrates the different types of interactions that a user ("Inventory Manager") can have with the system.

graph TD
    User(Inventory Manager)
    User --> M1(Manage Products)
    User --> M2(Manage Stock)
    User --> M3(Generate Reports)
    
    subgraph Manage Products
        M1 --> UC1.1(Add Product)
        M1 --> UC1.2(Update Product)
        M1 --> UC1.3(Delete Product)
        M1 --> UC1.4(View Product)
    end
    
    subgraph Manage Stock
        M2 --> UC2.1(Update Stock Quantity)
        M2 --> UC2.2(Check Stock Level)
    end

    subgraph Generate Reports
        M3 --> UC3.1(Run Low Stock Report)
        M3 --> UC3.2(Run Full Inventory Report)
    end
Workflow Diagram (Process Flow for "Add New Product")
graph TD
    A[User selects "Add Product"] --> B{Enter Product Id};
    B --> C{Enter Product Name};
    C --> D{Enter Product Price};
    D --> E[Main invokes ProductService.addProduct()];
    E --> F[ProductService validation of data];
    F --> G[ProductService invokes Repository.saveProduct()];
    G --> H[Repository saves Product in HashMap];
    H --> I[ProductService invokes StockService.initStock()];
    I --> J[StockService invokes Repository.saveStock()];
    J --> K[Repository saves Stock in HashMap];
    K --> L[Service returns 'Success' to Main];
    L --> M[Main displays "Product Added" to User];
Workflow Diagram (Flow of the "Add New Product" Process)
graph TD
    A[User selects "Add Product"] --> B{Enter Product ID};
    B --> C{Enter Product Name};
    C --> D{Enter Product Price};
    D --> E[Main calls ProductService.addProduct()];
    E --> F[ProductService validates data];
    F --> G[ProductService calls Repository.saveProduct()];
    G --> H[Repository stores Product in HashMap];
    H --> I[ProductService calls StockService.initStock()];
    I --> J[StockService calls Repository.saveStock()];
    J --> K[Repository stores Stock in HashMap];
    K --> L[Service returns 'Success' to main];
    L --> M[main notifies user "Product Added"];
Participants:

User
Main
StockService
MockDatabase
Sequence:

User → Main: Choose "Update Stock"
Main → User: "Provide Product ID:"
User → Main: "P101"
Main → User: "Provide Amount:"
User → Main: "50"
Main → StockService: updateStock("P101", 50)
StockService → MockDatabase: findStock("P101")
MockDatabase → StockService: 10 (current stock)
StockService → MockDatabase: saveStock("P101", 60)
MockDatabase → StockService: (success)
StockService → Main: (success)
Class Diagram
classDiagram
    class Main {
        -ProductService productService
        -StockService stockService
        -ReportService reportService
        -Scanner scanner
        +main(String[])
        -runMenu()
        -ui_addProduct()
    }

    class ProductService {
        -MockDatabase db
        +addProduct(String, String, double)
        +updateProduct(String, String, double)
        +deleteProduct(String)
        +getProduct(String) : Product
        +getAllProducts() : List~Product~
    }
    
    class StockService {
        -MockDatabase db
        +updateStock(String, int)
        +getStockLevel(String) : int
        +initStock(String)
    }

    class ReportService {
        -MockDatabase db
        +generateLowStockReport(int) : List~Product~
        +generateInventoryReport() : Map~Product, Integer~
    }
    
    class MockDatabase {
        -Map~String, Product~ products
        -Map~String, Integer~ stock
        -static MockDatabase instance
        -MockDatabase()
        +getInstance() : MockDatabase
        +saveProduct(Product)
        +findProduct(String) : Product
        +deleteProduct(String)
        +saveStock(String, int)
        +findStock(String) : int
    }
    
    class Product {
        -String id
        -String name
        -double price
        +getId()
        +getName()
        +getPrice()
        +setName(String)
    }
    
    class ProductNotFoundException {
        +ProductNotFoundException(String msg)
    }

    Main o-- ProductService
    Main o-- StockService
    Main o-- ReportService
    
    ProductService o-- MockDatabase
    StockService o-- MockDatabase
    ReportService o-- MockDatabase
    
    ProductService ..> Product
    ReportService ..> Product
    MockDatabase o-- Product
    
    ProductService ..> ProductNotFoundException
    StockService ..> ProductNotFoundException
Entity Relationship Diagram (Conceptual)
This represents a conceptual model of the data because it is using a mock database.

## ER-Diagram
    PRODUCT ||--o{ STOCK : "is tracked in"
    
    PRODUCT {
        String id PK
        String name
        double price
    }
    
    STOCK {
        String product_id PK
        int quantity
    }
Design Decisions and Rationale
Architecture: A three-layer (Presentation/Logic/Data) architecture was chosen to keep concerns separated and maintain highly maintainable code.

Data Storage: Instead of using a real database (such as MySQL), I used a MockDatabase class (Singleton pattern with HashMaps). This is a big consideration to keep the project self-contained so a user can easily clone and run it.

Error Handling: I created a custom exception (ProductNotFoundException) so my service methods can fail gracefully, and back in Main I can indicate to the user that an error occurred rather than causing a crash.

Service Layer: The business logic has been encapsulated in service classes (ProductService, StockService) to keep logic reusable and testable.

Build Tool: I used Maven to manage project structure and dependencies (none in this case because of the simple app, but also to get the project into a build system and provide a convenient way to run the app).

Implementation Details
The project is comprised of 7 Java files in various packages.

com.inventory.Main: The CLI entry point. It uses a Scanner and a while loop to display a menu and route user input to the appropriate service.
com.inventory.model.Product: A simple POJO (Plain Old Java Object) used to store data related to products.
com.inventory.repository.MockDatabase: A Singleton class. This is an important pattern as it guarantees that all service classes work with the same instance of the HashMaps (the same data).
com.inventory.service.ProductService: The class that implements the logic for CRUD functionality. It communicates with MockDatabase to save or fetch products. Additionally, when a new product is created, it calls StockService to initialize stock.
com.inventory.service.StockService: Responsible for managing stock-on-hand quantities. Its methods are synchronized to prevent possible race conditions, which is a best practice (although not absolutely necessary for a simple in-memory single-user CLI app).
com.inventory.service.ReportService: This service iterates over the data in MockDatabase to filter and format data for reporting purposes. The ReportService does not change any data.
com.inventory.exception.ProductNotFoundException: A simple class that extends RuntimeException to improve error handling.
Screenshots and Results
This is a text-based application, with console output examples included below.

Welcome to Inventory Management System
=========================================
1. Add a product
2. Edit stock for a product
3. See all products
4. Create Low Stock Report
5. Exit
Enter your choice: 1
Enter Product ID: P101
Enter Product Name: Coffee Beans
Enter Product Price: 19.99
Product 'Coffee Beans' added.

Enter your choice: 1
Enter Product ID: P102
Enter Product Name: Tea Leaves
Enter Product Price: 12.50
Product 'Tea Leaves' added.

Enter your choice: 2
Enter Product ID: P101
Enter stock qty to add/remove (e.g., 50 or -10): 50
Stock was updated for 'Coffee Beans'. Now stock = 50

Enter your choice: 2
Enter Product ID: P102
Enter stock qty to add/remove (e.g., 50 or -10): 5
Stock was updated for 'Tea Leaves'. Now stock = 5

Enter your choice: 4
--- LOW STOCK REPORT (Threshold: 10) ---
ID: P102, Name: Tea Leaves, Price: $12.50, Stock = 5
----------------------------------------

Enter your choice: 5
Goodbye.
Testing Approach
Testing was done manually through the CLI using a "black-box" approach.

Test Cases:

Add Product: Created a new product and checked "View all products" for its presence.
Add Duplicate Product: Attempted to add a product with an existing ID and checked for "Product ID already exists" error.
Update Stock (Add): Added 50 to a product and checked stock is 50.
Update Stock (Sell): Subtracted 10 from the product and checked that it is 40 stock now.
Low Stock Report: Created one product with 5 stock and one with 50, then ran the report to check only the product with stock 5 would be returned.
Invalid Input: Entered "abc" when input of a number was expected and checked for "Invalid number format," and that the application did not crash.
Not Found: Attempted to update stock for a non-existent ID and checked for "Product not found" error.
This manual testing process confirms that all of the functional requirements and error-handling NFRs are met. In the future, a series of JUnit tests would be a great enhancement to test each service.

## Challenges Faced
Design: Determining the right level of abstraction (e.g., interfaces vs. concrete classes). I used concrete classes for simplicity, but normally you would use interfaces in a larger or multi-user application.

## Data Flow: Ensuring the services communicated correctly. For example, in ProductService it was necessary to call into StockService to initialize stock. This required careful ordering of function calls.

## CLI Input: Managing user input robustly with Scanner is complex. The structure of try-catch for exceptions was very important, especially with InputMismatchException (for example, if a user enters text instead of a number).

Learnings & Key Takeaways
Separation of Concerns: This project demonstrated how separating presentation, logic, and data is beneficial. The design of the Main class is simple and allows easy location and correction of bugs.

Design Patterns: The use of the Singleton pattern in MockDatabase is critical for making the application work. The Service-Repository pattern also helped keep data access logic separate from business logic.

Error Handling: Custom exceptions are powerful. The intent of the code (for example, "this service can fail by not finding a product") is much clearer than just using a NullPointerException.

## Future Aspirations
Long-Term Storage: Replace the MockDatabase with a real database (such as SQLite or MySQL), using JDBC or an ORM such as Hibernate.
User Interface: Develop a user interface with JavaFX or Swing to enhance user experience.
User Management: Create a UserService to manage users and passwords, controlling access to the system.
## Automated Testing: Implement JUnit tests for automated testing to ensure reliability.
Reports: Implement more sophisticated reports, such as "Top Selling Items" (which assumes tracking sales).
References
## Oracle Java Documentation
Head First Design Patterns (for Singletons and architectural patterns)
Official Maven Website (for exec-maven-plugin configurations)