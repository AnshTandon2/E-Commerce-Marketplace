# Boilermaker Bazaar 
(Project 5 Option 3)

###
By: Ansh, Nirmal, Lalitha, Ankita, and Justin

<!-- #### Code submitted on Vocareum by Nirmal \|  Project Report Submitted by Ansh
#### Code is on the main branch (default) on GitHub -->

## Overview:
Boilermaker Bazaar is an application where Purdue students can buy and sell their Purdue merchandise (apparel, textbooks, game tickets, etc) to one another through their individual stores. Customers can view products to buy by store, description, or name, and sellers can create stores and list their products they would like to sell. The target market for this application is specifically for Purdue students who enjoy buying and selling, want to make some money, or find vintage or unique pieces of Purdue merchandise.

### 1. Running the Messenger

##### To run the messenger, follow these steps: 


### 2. Submission on Brightspace/Vocareum

### 3. The Classes

Inheritance, File IO, Exception Handling, Interfaces, JavaFX, NetworkIO, DataBases

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
Download and upload the 4 txt (shopping cart, market, purchases, users) files and the 5 java files to IntelliJ or another IDE. Go to the StartingApplication and run the terminal by clicking the run button.
The terminal takes user input starting with login information.
You will be met with the following options upon running the main method.
Sign-in to retrieve details if you have already used the application, otherwise Sign-up to create either a Customer or Seller account.

# Functions of the Classes

**Classes Being Implemented:**
1. Seller
2. Customer
3. Marketplace
4. Starting Application
5. Client
6. Server
7. Client.Marketplace

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
| addToCart() | String productName, int quantity, String storeName | void | public | Adds anothe rproduct to the cart file |
| removeFromCart() | String customerUsername, String productName, String productId | void | public | Takes in a file with shopping cart information and splits different customers by product and cumtomer name. |
| buyShoppingCartItems() | String customerName | void | public | Empties shopping cart on the basis of buying and then adds those purchases to the purchase history list. |
| exportPurchaseHistory() | String userName | void | public static | Exports all the information on the files of purchase history and writes them to a file in a list format. |
| viewShoppingHistory() | String userName, String sortBy, boolean ASCENDING | String | public static | Reads the purchase file and looks for instance of username given in the file. Then the information is extracted and put into a stringbuilder to return a string telling the customer what their file consists of. |
| displayCart() | String userName | ArrayList<String> | public static | Reads through the shopping cart file using the username and adds the items correlated with the user given to an array list and returns the list.  |

## Seller

#### Methods
| Name | Parameters | Return Type | Modifier | Description |
| --- | --- | --- | --- | --- |
| removeProduct() | String productName, String storeName | String | public | Allows seller to remove products from the current product line by checking the given productName and the Store name. If the instance of both exist then it will be removed or error will occur |
| productExists() | String productName, String storeName | boolean | public | Returns a boolean if the product exists in the specified Seller's store |
| addProduct() | String productName, double price, String storeName, int quantity, String description, String sellerUserName | String | public | Allows seller to take the product info and add it to their store.  |
| editProduct() | String productName, String storeName, String changeField, String newValue | String | public | Modifies the existing product value in the market file and adds the new parameters in for that product |
| ListPurchaseHistoryByStore() | String username | String | public static | Sellers can view a list of their sales by store, including customer information and revenues from the sale. |
| exportStoreInformation() | String merchantName, String storeName | boolean | public static | Allows a seller to export a csv file of the information of their store and CSV contains rows of products sold in the store. Each csv contains information for one of their unique stores |
| importStoreInformation() | String userName, String pathname | boolean | public static | Imports information to the market file by checking for the user and pathname instance and then adding the store information to those users in the file |
| viewStoreStatistics() | String userName, int userChoice | String | public | Seller can view a dashboard of their store statistics. The data includes a list of customers with the number of items that they have purchased or a list of products with the number of sales made depending on the sortChoice that the sellers choose on their dashboard in their menu |

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
| updateMarketplace()| None | void | public static | Modifies the market file when changes are being made to the product information stored by traversing through the array lists and chaning the data points|
| printMarketplace() | None | void | public static | Modifies the existing hashmap list for better organization in other classes | Prints the array list of product info by calling the getProductInfo method and separating each row by a line |
| getProductPrice() | String productName, String productSeller | String | public static | Returns a string that provides the product price when given the name of a product.|
| searchProduct() | String keyword | ArrayList<String> | public static | Uses the description key string to identify products in product list with specified and retrieve list of products available that contain specifed description |
| productDetail() | int index | String | public static | Creates a String that contains a formatted version of information about products the format is such: ""\nProduct Name: %s" + "\nProduct Price: %.2f" + "\nStore: %s" + "\nQuantity: %d" + "\nDescription: %s". If the stock is unavailable then the format will be: "\nProduct Name: %s" + "\nProduct Price: %.2f" + "\nStore: %s" + "\nQuantity: Out of Stock" |
| sortMarket() | String orderMethod, String sortBy | ArrayList<String> | public static | This method gets the productID of a list of products and uses the values to compare a pair of products and based on the price or quantity of values and their order(ascending/descending). The output is an array list of products IDs that are either ascending or descending by quantity or price.|

## Starting Application

#### Methods
| Name | Parameters | Return Type | Modifier | Description |
| --- | --- | --- | --- | --- |
| main() | String[] args | void | public static | First welcomes the user to the application and shows them the sign in, sign up, or exit options. If sign in option is selected, user will be prompted to enter username and password. Then their input will be tested to see if their account exists. If it does pass the test, they will be confirmed as logged in and pushed to the main menu. If tests don't pass, then print an error message. If sign up option is selected, user will be prompted to enter their name, email, and password. Then they will be asked to give their user status(Seller or Buyer). Their account will be checked to see if it already exists. If it does not, then based on the status given the user information will be either added to the Seller or shoppingCart(stores buyers and their shopping carts) files. Once account is successfully made, user is redirected back to sign in page and follows those steps again until either error or main menu. Lastly if exit option is selected, the system will print a thank you message and stop running. Once past login user will be prompted to either a menu for seller or customer based on their role. The customer has acces to shoppingcart and other purchase history and list of stores. The seller is provided with the function of managing stores and products and viewing customer and statistics.  |
| accountExists() | String email, String password | String | public static | Takes in the email and password parameters and checks for any instances of those variables in the Seller and shoppingCart files by iterating through the files using a filereader. |

### Connections between classes:
Starting Application is known as the main class. This class deals with the front end that provides the login and sign up options for the user. Once the user has logged onto the application the Starting Application class starts using the methods from MarketPlace, Customer, and Seller to help users with completing functions. The cart functions and purchasehistory functions are primarily initialized in the Customer class. On the other hand, Seller class deals primarily with product info and store info. All of which is made available through the Starting Application.

## Client
#### Menu Arrays
3 Initiated Arrays: 
The beforeLoginOptions array contains options for a user who has not yet logged in. sellerMenuOptions and buyerMenuOptions contain options for a seller and a buyer, respectively, after logging in.

#### Methods
| Name | Parameters | Description |
| --- | --- | --- | 
| displayLoginPage() | ArrayList<String> data | The method creates a JFrame, JPanel, JLabels, JTextFields, and a JPasswordField to construct the login interface. It uses a loop to keep prompting the user for input until valid username and password are provided or the user cancels the operation. It catches exceptions related to null values to handle potential errors. It compares the entered username and password with the data in the ArrayList<String> data. If a match is found, it retrieves the user's role. It returns an array containing the username, password, and user role if authentication is successful. If the user cancels or the authentication fails, it returns null. |
| displaySignupPage() | ArrayList<String> data | Similar to the login method, this method creates a JFrame, JPanel, JLabels, JTextFields, and a JComboBox to construct the signup interface. It uses a loop to keep prompting the user for input until valid information is provided or the user cancels the operation. It catches exceptions related to null values to handle potential errors. It uses a JComboBox to allow the user to choose between "Customer" and "Seller" for their role. It checks that all required fields (name, username, password, and user role) are filled before proceeding. If any field is empty, it displays an error message and returns to the signup screen. It checks whether the entered username or password already exists in the data provided. If a match is found, it displays an error message and returns to the signup screen. If the entered information is valid and does not match any existing accounts, it displays a success message and returns a formatted string containing the new user's information. |
| displayGUIMarketplace() | ArrayList<String> data | It parses the data provided (presumably containing information about products in the marketplace) and populates a 2D array (tempe) to be used as data for the JTable. It configures the JTable with column names and sets up a DefaultTableModel to control the data displayed. The table is set to be non-editable. It uses a JScrollPane to allow scrolling through the table, which is useful when there are many products. Then, it sets preferred widths for each column in the table to ensure proper display and configures the UIManager to set a minimum size for the JOptionPane and makes it resizable. Finally, it displays the marketplace information in a JOptionPane dialog with the created GUI components. |
| customerMarketplace() | ArrayList<String> data | The method takes an ArrayList<String> data containing information about products in the marketplace and a String type indicating the type of user (customer or seller). It provides the user with a set of sorting choices through a dialog. The selected choice is stored in the sortChoice variable. There are 3 sorting options. "None" which calls displayGUIMarketplace to display the marketplace without any sorting. "Ascending/Descending Price" sorts the products based on their prices in ascending or descending order. "Ascending/Descending Quantity" sorts the products based on their quantities in ascending or descending order. After sorting the data, it calls the displayGUIMarketplace method to show the sorted marketplace. |
| viewAllSalesByStore() | ArrayList<String> temp, String username | It iterates over the provided sales data (temp) and filters out the sales records where the seller's username (data[5]) matches the provided username. The filtered records are stored in the list ArrayList. Then, it sorts the filtered sales records based on the third element (o[2]) in each record. This assumes that the third element represents a value that can be compared, such as a store name or another sortable attribute. It constructs a StringBuilder (str) to build a formatted string containing information about sales by store. It iterates through the sorted sales records, calculating and appending details about each transaction to the string. Finally, it displays the information using a JOptionPane dialog with an information message type. It calls the updateMarketplace method from the Client.Marketplace class. The purpose of this method is not provided in the code snippet, but it seems to be related to updating the marketplace in some way after displaying the sales data. |
| addProduct() | ArrayList<String> temp, String username | It creates a graphical user interface (GUI) using Java Swing components to collect information about the new product, such as product name, price, store name, quantity, and description. It checks whether the seller owns the specified store (storeNames). If the store is not owned by the seller, it checks if the store doesn't already exist in the marketplace (marketStoreNames). If neither condition is met, it displays an error message and returns null. If all validations pass, it constructs a new product entry in the specified format and returns it as a string. It also displays a success message using JOptionPane. |

## Server

| Name | Parameters | Return Type | Modifier | Description |
| --- | --- | --- | --- | --- |
| main() | String[] args | void | public static | Waits for a client (GUI user) to request to connect, and then listens for a file name to send. Once it receives it from a client, it sends the details line by line and ends with the "STOP" delimiter at the end of the file. It then either waits for the client to send "STOP" to allow the server to wait for a new file to send, or a text file name for data to then be relayed line by line to be written into the Server's text files, succeeded by the "STOP" delimiter to liberate the server to listen for the next file. |


## Testing

In order to test the file, we have a Test.java file that provides our test cases. Within the actual file, you will be able to see comments that indicate what the correct output should be. Each of the methods in the test file are commented and they can be uncommented with ctrl + / to be run as a test case. We have implemented all of this to the main method of that file. 

### To run the GUI Testers, please read below: 

Testing consisted of creating 4 files: market.txt, purchases.txt, shoppingCart.txt, testImportFile.txt, users.txt. Each one contained test cases which consisted of data on users who were either sellers and customers. The market file had to mostly incorporate product details available on the market. During the testing phase of our code design, we went through a comprehensive and repetitive process to make sure everything worked smoothly. We made nearly 30 test cases in the TESTS.md file, where we took a close look at every function related to login and sign-up, using preset user info from our test cases. For customer functions, we used mainly "lchandol" as the username and "hackIt" as the password, while "davidkj" and "ignOps" were the go-to credentials for testing seller functions. Each member on the team used their own test cases for local testing purposes and to find edge-cases. Logging in as different types of users revealed different dashboards, so we carefully checked each function available. We cross-checked functions with our test files to ensure the changes were right and verified everything in the terminal or user interface. Our testing wasn't just about doing things the right way; we intentionally threw in some errors, like putting in the wrong format or calling for stuff that didn't exist. If the system didn't spit out a clear error message, we had debug until things were running smoothly, making sure our system was solid and reliable.



1) Open up the terminal and navigate to the terminal window.
2) Use javac filename.java to compile both the clietn and server files that are in GUI directory
3) After compiling, run the server once (will remain on all the time) and then start the Client.java file to start entire application. 


