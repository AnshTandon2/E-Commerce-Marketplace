# Purdue Boilermaker Bazaar 
(CS 180 - Project 4 Option 3)

###
By: Ansh, Nirmal, Lalitha, Ankita, and Justin

## Overview:
Boilermaker Bazaar is an application where Purdue students can buy and sell their Purdue merchandise (apparel, textbooks, game tickets, etc) to one another through their individual stores. Customers can view products to buy by store, description, or name, and sellers can create stores and list their products they would like to sell. The target market for this application is specifically for Purdue students who enjoy buying and selling, want to make some money, or find vintage or unique pieces of Purdue merchandise.

### 1. Running the Messenger

##### To run the messenger, follow these steps: 


### 2. Submission on Brightspace/Vocareum

### 3. The Classes

Project 4 using Inheritance, File IO, Exception Handling, and Interfaces.

## Getting Started
**Steps (Using Intellij):**
1. Press the green Code button on the top right.
2. Copy the HTTPS link and navigate to Intellij.
3. In Intellij, press get from Version Control and paste the link copied into the box.
4. Build the project and run the main method.

**Steps (Using Terminal)**
1. Press the green Code button on the top right.
2. Copy the HTTPS link and navigate to a terminal window.
3. Type ```git clone``` and paste the link after and press enter.
4. Use ```javac filename.java``` to compile each of the classes in the file.
5. Type  ```java main.java``` to run the program.

## Using the Program
Our program uses the terminal for user input and output. Whenever user input is needed, the user should either enter the number that is associated with an option or type in the words required and press enter.
**Login Page:**<br>
You will be met with the following options upon running the main method.
```
enter format of the log in page
```
Sign-in to retrieve details if you have already used the application, otherwise Sign-up to create either a Customer or Seller account.

# Functions of the Classes

**Classes Being Implemented:**
1. User
2. Seller
3. Customer
4. Store
5. Product
6. Marketplace
7. Shopping Cart
8. Account Manager
9. Main
10. Starting Application

## User

#### Fields
| Name | Type | Modifier |
| --- | --- | --- |
| name | String | private |
| email | String | private |
| password | String| private |

#### Constructors
| Name | Parameters | Modifier |
| --- | --- | --- |
| User | String name, String email, String password | public |

#### Methods
| Name | Parameters | Return Type | Modifier | Description |
| --- | --- | --- | --- | --- |
| getName() | None | String | public | Returns the name of the user |
| getEmail() | None | String | public | Returns the email of the user |
| getPassword() | None | String | public | Returns the password of the user |
| setName() | String name | String | public | Sets the name of the user to given name |
| setPassword() | String password | String | public | Sets the password of the user to given password|
| accountExists() | String email, String password | boolean | public | Verifies if the account exists already in comparison to one other account |

## Customer

#### Fields
| Name | Type | Modifier |
| --- | --- | --- |
| shoppingCart | ShoppingCart | private |
| purchaseHistory | File | private |
| customerHistoryFiles | ArrayList<File> | private static |

#### Constructors
| Name | Parameters | Modifier | Description |
| --- | --- | --- | --- |
| Customer | String name, String email, String password | public | Initiates a customer object with a name, email, and password and opens a file for its purchase history and shopping cart |

#### Methods
| Name | Parameters | Return Type | Modifier | Description |
| --- | --- | --- | --- | --- |
| initiatePurchaseHistoryFile() | None | void | public | Initializes customer's purchase history file when they create an account on Boilermaker Bazaar and adds it to the static list of customer history files |
| modifyPurchaseHistoryFile() | ArrayList<Product> list | void | public | Modifies the existing purchase history file for the user by adding a new product purchase to the customer's purchase history |
| exportPurchaseHistory() | None | void | public | Returns the customer Purchase History |
| getCustomerPurchaseHistory() | String name, int userId | File | public static | Retrieves a purchase history file when given the customer's name and unique userId |
| addToShoppingCart() | String product | void | public | Adds an item to the customer's shopping cart |
| removeFromShoppingCart() | String product | void | public | Removes an item from the customer's shopping cart |
| viewShoppingCart() | None | void | public | Allows customer view of what is in their shopping cart and data persistence permits user access even after logging out |
