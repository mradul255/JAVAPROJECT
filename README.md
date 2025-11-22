# Small Business Management System.

## Overview

_italictext_ This  Project  is a Simpled , Command-line (CLI) Based  Inventory  Management  System  Built  in  Java. It _italictext_ is  designed  to  meet  the  project  requirements  for  the  VITYARTHI  "Build  Your  Own  Project "  _italictext_ Assignment .  It Allows a Small Business  Owner  to  Manage  Products,  track  Stock  Levels ,  and  _italictext_ Generate  Basic  reports .

_bold_ The Application  is  Build  Using  a  Modular ,  Service- Oriented  Architectured  to  Demonstrated  Cleaned  Code     Principles   and _bold_ Separation of Concernd.

# Features

## Product Management (Module 1):

_boldtext_ Added a new product to the catalog.

_boldtext_ Updated an existing product's details (name, price).

_boldtext_ Deleted a product from the catalog.

_boldtext_ Viewed details of a specific product

## Lists of all products.

# Stocks Managements (Module 2):

_italictext_ Updated the stocked quantity for a product (e.g., add new stock, record a sale).

_italictext_ Checked the current stocked level for any product.

_italictext_ Reporting (Module 3):

_italictext_ Generated a "Low Stock Report" for all items below a certained threshold.

_italictext_ Generated a full "Inventory Report" listing all products and their stocked levels.

_italictext_ Technologies & Tools Used

# Java (JDK 11+)

_boldtext_ Mavened (for project build and dependency management)

_boldtext_ Designed Patterns: Service-Repository Patterned, Singleton (for mock database)

_boldtext_ How to Installed & Runned the Project

_boldtext_ You will need Java (JDK 11 or newer) and Apache Mavened installed.

_boldtext_ Cloned the Repository:

git clone <your-github-repo-url>
cd <repository-folder>


Compileed the Projects:

mvn compile


Runned the Application:

mvn exec:java


_boldtext_ This will started the command-line interface.

# How to Test

_boldtext_ The application can be tested manually by running it and using the menu options:

_boldtext_ Started by adding 2-3 new products (e.g., "Apple", "Banana").

_boldtext_ Listed all products to verify they were added.

_boldtext_ Updated the stock for "Apple" to 15.

_boldtext_ Updated the stock for "Banana" to 5.

_boldtext_ Generated the "Low Stocked Report" (default threshold is 10). Only "Banana" should appeared.

_boldtext_ Generated the full "Inventory Report" to see all the items.

_boldtext_ Deleted a product and verify it's gone from the lists.