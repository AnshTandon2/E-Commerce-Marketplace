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
1. Seller
2. Customer
3. Marketplace
4. Starting Application

## Customer

#### Fields
| Name | Type | Modifier |
| --- | --- | --- |
| name | String | private |
| email | String | private |
| password | String | private |

#### Constructors
| Name | Parameters | Modifier |
| --- | --- | --- |
| Customer | String name, String email, String password | public |

#### Methods
| Name | Parameters | Return Type | Modifier | Description |
| --- | --- | --- | --- | --- |
| getName() | None | String | public | Returns the name of the user |
| getEmail() | None | String | public | Returns the email of the user |
| getPassword() | None | String | public | Returns the password of the user |
| setName() | String name | String | public | Sets the name of the user to given name |
| setEmail() | String email | String | public | Sets the email of the user to given email |
| setPassword() | String password | String | public | Sets the password of the user to given password |
| addToCart() | String productName, int quantity, String storeName | void | public | Adds anothe rproduct to the cart file |
| removeFromCart() | String customerUsername, String productName, String productId | void | public | Takes in a file with shopping cart information and splits different customers by product and cumtomer name. |
| buyShoppingCartItems() | String customerName | void | public | Empties shopping cart on the basis of buying and then adds those purchases to the purchase history list. |
| buyItem() | String customerName,String productId, int quantity | boolean | public static | Adds an instance of customer or seller to user list |


## Seller

#### Fields
| Name | Type | Modifier |
| --- | --- | --- |
| stores | ArrayList<Store> | private |
| StoreHistory | File | private |


#### Constructors
| Name | Parameters | Modifier | Description |
| --- | --- | --- | --- |
| Seller | String info, ArrayList<Stores> storeList | public | Reads the info files with all the information about stores and searches for instances of seller and adds those stores into an array list for seller |
| Seller | String name, String email, String password | public | Calls User constructor and sends the name, email, and password into it while creating a seller object |

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

## Market Place

#### Fields
| Name | Type | Modifier |
| --- | --- | --- |
| productIDs | ArrayList<String>  | private static |
| productNames | ArrayList<String>  | private static |
| priceList | ArrayList<Double> | private static |
| storeNames | ArrayList<String>  | private static |
| quantityList | ArrayList<Integer>  | private static |
| descriptionList | ArrayList<String>  | private static |

#### Methods
| Name | Parameters | Return Type | Modifier | Description |
| --- | --- | --- | --- | --- |
| initializeMarketplace()| None | void | public static | Initializes a file for market info and splits info by ";" and uses different indexes of the split string to add into the different characteristic arrays (productIDs, productNames, priceList, storeNames, quantityList, descriptionList)|
| printMarketplace() | None | void | public static | Modifies the existing hashmap list for better organization in other classes | Prints the array list of product info by calling the getProductInfo method and separating each row by a line |
| getProductInfo() | int index | String | public static | Returns a string that provides the index, product name, price, and store name when given an index of a product. In addition, the product's quantity is checked through an if statement, and then based of its availability it is displayed as out of stock or the quantity presently available. |
| searchProduct() | String keyword | ArrayList<String> | public static | Uses the description key string to identify products in product list with specified and retrieve list of products available that contain specifed description |
| productDetail() | int index | String | public static | Creates a String that contains a formatted version of information about products the format is such: ""\nProduct Name: %s" + "\nProduct Price: %.2f" + "\nStore: %s" + "\nQuantity: %d" + "\nDescription: %s". If the stock is unavailable then the format will be: "\nProduct Name: %s" + "\nProduct Price: %.2f" +
"\nStore: %s" + "\nQuantity: Out of Stock" |
| sortMarket() | String orderMethod, String sortBy | ArrayList<String> | public static | This method gets the productID of a list of products and uses the values to compare a pair of products and based on the price or quantity of values and their order(ascending/descending). The output is an array list of products IDs that are either ascending or descending by quantity or price.|
| removeFromCart() | String productName, String userName | void | public static | Removes a product from the made cart by checking if its instance exists and then deleting |
| displayCart() | String userName | ArrayList<String> | public static | Creates an array list out of the shopping cart file and then returns the list of the shopping cart contents |
| printCart() | String customerName | void | public | Creates a cart list : "Product Name: %s\nProduct Price: %.2f\n" +"Quantity: %d\nTotal Cost: %.2f\nStore: %s\n\n"|

## Starting Application

#### Methods
| Name | Parameters | Return Type | Modifier | Description |
| --- | --- | --- | --- | --- |
| main() | String[] args | void | public static | First welcomes the user to the application and shows them the sign in, sign up, or exit options. If sign in option is selected, user will be prompted to enter username and password. Then their input will be tested to see if their account exists. If it does pass the test, they will be confirmed as logged in and pushed to the main menu. If tests don't pass, then print an error message. If sign up option is selected, user will be prompted to enter their name, email, and password. Then they will be asked to give their user status(Seller or Buyer). Their account will be checked to see if it already exists. If it does not, then based on the status given the user information will be either added to the Seller or shoppingCart(stores buyers and their shopping carts) files. Once account is successfully made, user is redirected back to sign in page and follows those steps again until either error or main menu. Lastly if exit option is selected, the system will print a thank you message and stop running. |
| signUp() | String email, String password | boolean | public | Initializes customer's purchase history file when they create an account on Boilermaker Bazaar and adds it to the static list of customer history files |
| accountExists() | String email, String password | String | public static | Takes in the email and password parameters and checks for any instances of those variables in the Seller and shoppingCart files by iterating through the files using a filereader. |





