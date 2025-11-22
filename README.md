# Small Business Management System
## Overview
This project is a simple, Command-line (CLI) based Inventory Management System built in Java. It is designed to meet the project requirements for the VITYARTHI "Build Your Own Project" assignment. It allows a small business owner to manage products, track stock levels, and generate basic reports.

The application is built using a modular, Service-Oriented Architecture to demonstrate clean code principles and Separation of Concerns.

## Features
Product Management (Module 1):
Add a new product to the catalog.

Update an existing product's details (name, price).

Delete a product from the catalog.

View details of a specific product.

## List all products.

Stock Management (Module 2):
Update the stock quantity for a product (e.g., add new stock, record a sale).

Check the current stock level for any product.

## Reporting (Module 3):
Generate a "Low Stock Report" for all items below a certain threshold.

Generate a full "Inventory Report" listing all products and their stock levels.

Technologies & Tools Used
Java (JDK 11+)

Maven (for project build and dependency management)

Design Patterns: Service-Repository Pattern, Singleton (for mock database)

How to Install & Run the Project
You will need Java (JDK 11 or newer) and Apache Maven installed.

Clone the Repository:

Bash

git clone <your-github-repo-url>
cd <repository-folder>
Compile the Project:

Bash

mvn compile
Run the Application:

Bash

mvn exec:java
This will start the command-line interface.

How to Test
The application can be tested manually by running it and using the menu options:

Start by adding 2-3 new products (e.g., "Apple", "Banana").

List all products to verify they were added.

Update the stock for "Apple" to 15.

Update the stock for "Banana" to 5.

Generate the "Low Stock Report" (default threshold is 10). Only "Banana" should appear.

Generate the full "Inventory Report" to see all the items.

Delete a product and verify it is gone from the list.

Key Changes Made:
Verb Tense: Changed past tense words (Runned, Simpled, Compiled) to the correct present or imperative forms (Run, Simple, Compile), which is standard for README files.

Spelling: Fixed typos like "Mavened," "Concernd," and "Architectured."

Formatting: Converted your text markers (like _boldtext_) into actual Markdown formatting.

Phrasing: Smoothed out sentences for better flow (e.g., "Lists of all products" changed to "List all products").