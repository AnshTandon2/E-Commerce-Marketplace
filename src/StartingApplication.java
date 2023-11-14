import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Starting Application Class
 * Serves as the Login/Sign up system for a user
 * The class redirects them to the corresponding menu
 * based on User type, and allows the user to interact
 * with our actual application.
 * <p>Purdue University - CS 180 - Project 4</p>
 *
 * @author Lalitha Chandolu, Ansh Tandon, Justin Ho-Yuk
 * @version November 14, 2023
 */

public class StartingApplication {
    /**
     * This is the main method that generates the menu to go through the entire application.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Boilermaker Bazaar! ");
        Scanner s = new Scanner(System.in);
        // welcomes the user to the application
        // redirects them to sign up or sign in page
        boolean redirected = false;
        boolean exitOnFirst = false;
        String username = "";
        String password;
        String userRole = "";
        do {
            try {
                int value;
                System.out.println("Please select one of the three options: " + "\n1. Login to Application" + "\n2. " + "Sign Up with a New Account" + "\n3. Exit");
                value = Integer.parseInt(s.nextLine());
                if (value == 1) {
                    //  the user has an existing account and would like to log in to the marketplace
                    System.out.println("Enter username");
                    username = s.nextLine();
                    System.out.println("Enter password");
                    password = s.nextLine();
                    // verifies that the existing account exists
                    userRole = accountExists(username, password);
                    if (userRole == null) {
                        // user inputted wrong information or user doesn't currently exist
                        System.out.println("The username or password is incorrect");
                        // should go back again to main menu due to error
                    } else {
                        // signed in successfully
                        System.out.println("Success, logging you in now");
                        redirected = true;
                    }
                } else if (value == 2) {
                    // would like to make a new account in the marketplace
                    System.out.println("Enter your name");
                    String name = s.nextLine().toLowerCase();
                    // allows each user to have a unique identifier
                    System.out.println("Enter your Purdue username (the part before your @purdue.edu)");
                    username = s.nextLine();
                    System.out.println("Enter new password");
                    password = s.nextLine();
                    System.out.println("Are you signing up as a:\n[1] Seller\n[2] Buyer");
                    String roleChoice = s.nextLine();
                    // checks if they have an existing account or not
                    userRole = accountExists(username, password);
                    // they don't have an existing account - so they can make an account
                    if (userRole == null) {
                        // they want to have the role of a seller
                        File f = new File("users.txt");
                        try {
                            FileWriter fw = new FileWriter(f, true);
                            BufferedWriter bfw = new BufferedWriter(fw);
                            // populates a list of sellers with the username (purdue email)
                            if (roleChoice.equals("1")) {
                                // s is indicator for seller user type
                                bfw.write(username + ";" + password + ";" + name + ";" + "s");
                            } else if (roleChoice.equals("2")) {
                                // c is indicator for customer user type
                                bfw.write(username + ";" + password + ";" + name + ";" + "c");
                            } else {
                                System.out.println("Please try again!");
                            }
                            bfw.flush();
                            bfw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // account exists - go back to main menu
                        System.out.println("Account already exists");
                    }
                } else if (value == 3) {
                    // would like to exit the application
                    System.out.println("Thank you for using Purdue Bazaar!");
                    redirected = true;
                    exitOnFirst = true;
                } else {
                    // invalid choice was entered
                    System.out.println("Please try again. Make sure you enter a valid choice.");
                }
            } catch (NumberFormatException e) {
                // user didn't enter a number
                System.out.println("There was an error in your input, please try again");
            }
        } while (!redirected);

        boolean loggedOut = exitOnFirst;
        Marketplace.initializeMarketplace();
        while (!loggedOut) {
            if (userRole.equalsIgnoreCase("s")) {
                System.out.println("Seller Main Menu:\n1. View Marketplace\n2. View all sales by store\n3. Add a " +
                        "Product\n" + "4. Edit a Product\n5. Delete a Product\n6. View Store Statistics\n7. " +
                        "Import/Export " + "Products\n" + "8. Log Out");
                String MMChoice = s.nextLine();
                switch (MMChoice) {
                    case "1" -> {
                        System.out.println("1. View Dashboard\n2. Sort by Ascending Price\n3. Sort by Descending " +
                                "price\n4. Sort by " + "ascending quantity\n5. Sort by Descending Quantity\n" +
                                "(Anything" + " else.) exit");
                        String sortChoice = s.nextLine();
                        switch (sortChoice) {
                            case "1" -> Marketplace.printMarketplace();
                            case "2" -> {
                                ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", true); //not returning

                                // correct values
                                for (String str : sortedIndexes) {
                                    System.out.println(Marketplace.productDetail(Integer.parseInt(str))); //incorrect
                                }
                            }
                            case "3" -> {
                                ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", false); //not
                                // returning

                                // correct values
                                for (String str : sortedIndexes) {
                                    System.out.println(Marketplace.productDetail(Integer.parseInt(str))); //incorrect
                                }
                            }
                            case "4" -> {
                                ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", true); //not
                                // returning

                                // correct values
                                for (String str : sortedIndexes) {
                                    System.out.println(Marketplace.productDetail(Integer.parseInt(str))); //incorrect
                                }
                            }
                            case "5" -> {
                                ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", false); //not
                                // returning

                                // correct values
                                for (String str : sortedIndexes) {
                                    System.out.println(Marketplace.productDetail(Integer.parseInt(str))); //incorrect
                                }
                            }
                            default -> System.out.println("Please go back and try again!");
                        }
                    }
                    case "2" -> { //list stores that the seller is associated with and the products in it
                        System.out.println(Seller.listProductsByStore(username));
                    }
                    case "3" -> { //add product
                        System.out.println("Enter a Product Name: ");
                        String productName = s.nextLine();
                        System.out.println("Enter a Price: ");
                        double price = s.nextDouble();
                        s.nextLine();
                        System.out.println("Enter the Store Name it's sold at: ");
                        String storeName = s.nextLine();
                        System.out.println("Enter a Quantity: ");
                        int quantity = s.nextInt();
                        s.nextLine();
                        System.out.println("Enter a description: ");
                        String description = s.nextLine();
                        System.out.println(Seller.addProduct(productName, price, storeName, quantity, description,
                                username));
                        Marketplace.updateMarketplace();
                    }

                    case "4" -> { //edit a product
                        System.out.println("What is the name of the product that you want to edit?");
                        String product = s.nextLine();
                        System.out.println("What store does this product belong to?");
                        String store = s.nextLine();
                        if (Seller.productExists(product, store)) {
                            System.out.println("Which part of the product would you like to edit?");
                            System.out.println("1. Product Name\n2. Product Price\n3. Store Name of Product\n4. " +
                                    "Quantity " + "of Product\n5. Description of the product\n6. Exit");
                            String part = s.nextLine();
                            switch (part) {
                                case "1" -> { //change name
                                    System.out.println("Enter the new name of the product");
                                    System.out.println(Seller.editProduct(product, store, "name", s.nextLine()));
                                }
                                case "2" -> { //change price
                                    System.out.println("Enter the new price of the product");
                                    System.out.println(Seller.editProduct(product, store, "price", s.nextLine()));
                                }
                                case "3" -> { //change store
                                    System.out.println("Enter the new store of the product");
                                    System.out.println(Seller.editProduct(product, store, "store", s.nextLine()));
                                }
                                case "4" -> { //change quantity
                                    System.out.println("Enter the new quantity of the product");
                                    System.out.println(Seller.editProduct(product, store, "quantity", s.nextLine()));
                                }
                                case "5" -> { //change description
                                    System.out.println("Enter the new description of the product");
                                    System.out.println(Seller.editProduct(product, store, "description", s.nextLine()));
                                }
                                case "6" -> {
                                } //exit
                                default -> {
                                    System.out.println("Please make a valid selection.");
                                }
                            }
                            Marketplace.updateMarketplace();
                        } else
                            System.out.println("Product doesn't exist and cannot be modified.");
                    }

                    case "5" -> { //delete a product
                        System.out.println("What product would you like to remove?");
                        String product = s.nextLine();
                        System.out.println("What store should the product be removed from?");
                        String store = s.nextLine();
                        System.out.println(Seller.removeProduct(product, store));
                        Marketplace.updateMarketplace();
                    }

                    case "6" -> { //view store statistics

                    }
                    case "7" -> {//import or exports for product
                        //justin's part

                    }
                    case "8" ->  //logged out
                            loggedOut = true;
                    default -> System.out.println("Please try again with valid input!");
                }
            } else {
                // the user is a Customer type
                System.out.println("Customer Main Menu\n1. View Dashboard\n2. View Shopping Cart\n3. Search for " +
                        "Product\n4. Purchase a Product\n5. View Shopping History\n6. Export Shopping Cart" +
                        "History\n7. Log Out");
                String MMChoice = s.nextLine();
                switch (MMChoice) {
                    case "1" -> {
                        String sortChoice;
                        boolean endLoop = false;
                        do {
                            System.out.println("1. View Marketplace\n2. Sort by Ascending Price\n3. Sort by " +
                                    "Descending " + "price\n4. Sort by ascending quantity\n5. Sort by Descending " +
                                    "Quantity\n6. Exit");
                            sortChoice = s.nextLine();
                            switch (sortChoice) {
                                case "1" -> Marketplace.printMarketplace();
                                case "2" -> {
                                    ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", true); //not
                                    for (String str : sortedIndexes) {
                                        System.out.println(Marketplace.productDetail(Integer.parseInt(str)));
                                    }
                                }
                                case "3" -> {
                                    ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", false); //not
                                    for (String str : sortedIndexes) {
                                        System.out.println(Marketplace.productDetail(Integer.parseInt(str)));
                                    }
                                }
                                case "4" -> {
                                    ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", true); //not
                                    for (String str : sortedIndexes) {
                                        System.out.println(Marketplace.productDetail(Integer.parseInt(str)));
                                    }
                                }
                                case "5" -> {
                                    ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", false);
                                    for (String str : sortedIndexes) {
                                        System.out.println(Marketplace.productDetail(Integer.parseInt(str)));
                                    }
                                }
                                case "6" -> {
                                    endLoop = true;
                                }
                                default -> {
                                    System.out.println("Invalid input. Please go back and try again!");
                                }
                            }
                        } while (!endLoop);
                    }
                    case "2" -> {  //display cart
                        ArrayList<String> shoppingCart = Marketplace.displayCart(username); //error here
                        assert shoppingCart != null;
                        for (String str : shoppingCart) {
                            System.out.println(str + "\n");
                        }
                        String cartChoice;
                        do {
                            System.out.println("1. Remove Cart Product\n2. Buy All Cart Products\n3. Add Cart Product");
                            cartChoice = s.nextLine();
                            switch (cartChoice) {
                                case "1" -> {
                                    System.out.println("Enter cart item name to be removed");
                                    String removeChoice = s.nextLine();
                                    //Marketplace.removeFromCart(removeChoice, username);
                                }
                                case "2" -> {
                                }
                                //TODO: Implement buy all products from cart method
                                case "3" -> {
                                }
                                //TODO: Implement add cart product method
                                default -> System.out.println("Please enter valid cart choice!");
                            }
                        } while (!cartChoice.equals("1") && !cartChoice.equals("2") && !cartChoice.equals("3"));
                    }
                    case "3" -> {  //search for product
                        boolean search = true;
                        while (search) {
                            System.out.println("Enter your search term: ");
                            String keyword = s.nextLine();
                            ArrayList<String> searchedProducts = Marketplace.searchProduct(keyword);
                            if (searchedProducts.isEmpty()) {
                                System.out.println("No products match that search term.");
                            } else {
                                for (String str : Marketplace.searchProduct(keyword)) {
                                    System.out.println(str);
                                }
                            }
                            while (true) {
                                System.out.println("Would you like to search for something else? (yes/no)");
                                String result = s.nextLine();
                                if (result.equals("yes"))
                                    break;
                                else if (result.equals("no")) {
                                    search = false;
                                    break;
                                } else {
                                    System.out.println("invalid input, try again");
                                }
                            }
                        }
                    }
                    case "4" -> {  //purchase a product
                        boolean flag = false;
                        System.out.println("Enter product name: ");
                        String name = s.nextLine();
                        System.out.println("Enter store name: ");
                        String finalee = "";
                        String store = s.nextLine();
                        int quant = -1;

                        //run through market.txt file
                        try {
                            File f = new File("market.txt");
                            BufferedReader br = new BufferedReader(new FileReader(f));
                            ArrayList<String> list = new ArrayList<>();
                            String line;
                            while ((line = br.readLine()) != null) {
                                list.add(line);
                            }
                            for (int i = 0; i < list.size(); i++) {
                                String[] arr = list.get(i).split(",");
                                if (arr[0].equals(name) && arr[2].equals(store)) {
                                    flag = true;
                                    while (Integer.parseInt(arr[3]) - quant < 0) {
                                        System.out.println("Enter valid quantity you want of " + arr[0] + " from " + arr[2]);
                                        quant = s.nextInt();
                                    }
                                    int newQuantity = Integer.parseInt(arr[3]) - quant;
                                    arr[3] = newQuantity + "";
                                    StringBuilder formedString = new StringBuilder();
                                    for (String string : arr) {
                                        formedString.append(string);
                                    }
                                    list.set(i, formedString.toString());
                                    finalee = list.get(i);

                                }
                            }
                            br.close();
                            if (flag) {
                                BufferedWriter bw = new BufferedWriter(new FileWriter("market.txt", false));
                                for (String a : list) {
                                    bw.write(a);
                                    bw.newLine();
                                }
                                bw.close();

                                //now updating shopping cart to add that product'
                                //finale is now the new product but with updated count
                                //quant --> quantity they bought, so replace that in finalee
                                String[] arr = finalee.split(",");
                                arr[3] = quant + "";
                                StringBuilder reformatedd = new StringBuilder();
                                for (String a : arr) {
                                    reformatedd.append(a);
                                }

                                BufferedWriter bww = new BufferedWriter(new FileWriter("shoppingCart.txt", true));
                                bww.write(reformatedd.toString());
                                bww.close();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    //Marketplace.purchaseProduct();
                    //need to implement logic for this
                    case "5" -> { //view shopping history
                        boolean exit = false;
                        while (!exit) {
                            System.out.println("1. Sort by amount spent ascending.\n2. Sort by amount spent " +
                                    "descending.\n3. Sort by items purchased ascending.\n4. Sort by items purchased " + "descending.\n5. Exit");
                            String smallMenuChoice = s.nextLine();
                            switch (smallMenuChoice) {
                                case "1" -> {
                                    System.out.println(Customer.viewShoppingHistory(username, "price", true));
                                    exit = true;
                                }
                                case "2" -> {
                                    System.out.println(Customer.viewShoppingHistory(username, "price", false));
                                    exit = true;
                                }
                                case "3" -> {
                                    System.out.println(Customer.viewShoppingHistory(username, "quantity", true));
                                    exit = true;
                                }
                                case "4" -> {
                                    System.out.println(Customer.viewShoppingHistory(username, "quantity", false));
                                    exit = true;
                                }
                                case "5" -> exit = true;
                                default -> System.out.println("Please enter a valid input and try again.");
                            }
                        }
                    }
                    case "6" ->   //export shopping history
                            Customer.exportPurchaseHistory(username);
                    //need to implement logic for this
                    case "7" -> {  //log out
                        System.out.println("Thank you for using marketplace!");
                        loggedOut = true;
                    }
                    default -> System.out.println("Please try again with valid input!");
                }
            }
        }
    }

    /**
     * A method to check if an account exists based no the given username and password.
     */
    public static String accountExists(String username, String password) {
        // parses the file of all of the existing user in the marketplace
        File f = new File("users.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                // splits the line by the ";" token
                String[] temp = line.split(";");
                // email and password given match
                if (temp[0].equals(username) && temp[1].equals(password)) {
                    // returns the userRole (s for Seller, c for Customer)
                    return temp[3];
                }
                line = bfr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // the info given doesn't match with an existing user
        return null;
    }
}
