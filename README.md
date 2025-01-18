# LC Waikiki Test Automation Project
### About the Project

This project is designed to test the main user flows of the LC Waikiki e-commerce website through automation. Selenium, TestNG, and Maven technologies are used to create test scenarios. The aim is to ensure the correctness of critical user flows and enhance the user experience.

### Technologies

The following technologies are used in this project:

* **Selenium**: For web browser automation.

* **TestNG**: As a testing framework.

* **Maven**: For project and dependency management.

* **Allure**: For generating and analyzing test reports.

###  Tested Flows

1. ### Login:

* Click the Login button.

* Enter a valid email and proceed.

* Enter a valid password and log in.

2. ### Category Selection:

* Select "Kids & Baby" category and then "Girls (6-14 Years)".

* Choose the "Coats & Jackets" sub-menu.

3. ### Filtering:

* Apply size filters: 5-6, 6, 6-7 years.

* Apply color filter: Beige.

* Products are re-listed according to the filters.

4. ### Sorting:

* Click the sorting combobox and select "Best Sellers".

* Products are re-listed according to the selected sorting option.

5. ### Product Details and Cart Operations:

* Click on the 4th product in the first row.

* On the product detail page, select a size that is in stock and add the product to the cart.
  
* On the cart page, verify the name, color, and quantity of the selected product.

* Ensure the product price matches the total amount displayed in the "Proceed to Payment" section.
  
* Increase the product quantity by 1.

* Decrease the product quantity by 1.

6. ### Favorites 
* Click the Heart icon on the product card to add the product to favorites.
  
* Click on the "Favorites" icon.
  
* On the favorites screen, verify that the previously favorited product is listed (matching by product name).


### Project Structure
This is a Maven-based project with the following directory structure:

```
|
├── src/main/java        
│   └── pages            # Page Object Model (POM) files
├── src/test/java        
│   ├── tests            # Test case files
│   └── listeners        # Listener files (e.g., TestListener)
├── pom.xml              # Dependency configuration file
├── testng.xml           # TestNG test suite file
└── allure-results       # Allure report result files

```

  ### Installation
1. Clone this repo.
  
2. Install Maven dependencies
```
mvn clean install
 ```

### Running Tests
1. To execute TestNG tests, use the following command:
```
mvn test
```
2. To generate Allure reports:
```
allure serve
```

### Important Notes
* **testng.xml:** Used to define the order and grouping of tests.

* **allure-results:** Required for generating Allure reports. If this folder is cleaned, new   test    results must be generated before creating a report.

* **Screenshots:** Screenshots are taken for failed tests and included in the Allure reports.
  
### Video
[<!-- Uploading "lcwautomation.mp4"... -->](https://github.com/user-attachments/assets/8a258b75-d1ab-4c1d-abf5-163dc0524c24)



















  
