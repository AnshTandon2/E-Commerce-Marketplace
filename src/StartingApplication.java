import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/** Starting Application Class
 * Serves as the Login/Sign up system for a user
 * The class redirects them to the corresponding menu
 * based on User Type
 *
 * @author Lalitha Chandolu, Ansh Tandon, Justin Ho-Yuk
 * @version November 12, 2023
 *
 */

public class StartingApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to the Boilermaker Bazaar! ");
        Scanner s = new Scanner(System.in);
        // welcomes the user to the application
        // redirects them to sign up or sign in page
        boolean redirected = false;
        boolean exitOnFirst = false;
        String username = "";
        String password = "";
        String userRole = "";
        do {
            try {
                int value = 0;
                System.out.println("Please select one of the three options: " +
                        "\n1. Login to Application" +
                        "\n2. Sign Up with a New Account" +
                        "\n3. Exit");
                value = Integer.parseInt(s.nextLine());
                if (value == 1) {
                    //  the user has an existing account and would like to login to the marketplace
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
                    // invalid choice was enetered
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
            if (userRole.equalsIgnoreCase("seller")) {
                System.out.println("Seller Main Menu:\n1. View Marketplace\n2. Add a Product\n" +
                        "3. Edit a Product\n4. Delete a Product\n5. View Store Statistics\n6. Import/Export " +
                        "Products\n" +
                        "7. Log Out");
                String MMChoice = s.nextLine();
                if (MMChoice.equals("1")) {
                    System.out.println("1. View Marketplace\n2. Sort by Ascending Price\n3. Sort by Descending " +
                            "price\n4. Sort by " +
                            "ascending quantity\n5. Sort by Descending Quantity\n(Anything else.) exit");
                    String sortChoice = s.nextLine();
                    if (sortChoice.equals("2")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", true); //not returning
                        // correct values
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str))); //incorrect
                        }
                    } else if (sortChoice.equals("3")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", false); //not returning
                        // correct values
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str))); //incorrect
                        }
                    } else if (sortChoice.equals("4")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", true); //not returning
                        // correct values
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str))); //incorrect
                        }
                    } else if (sortChoice.equals("5")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", false); //not returning
                        // correct values
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str))); //incorrect
                        }
                    } else if (sortChoice.equals("1")) {
                        Marketplace.printMarketplace();
                    } else {
                        System.out.println("Please go back and try again!");
                    }
                } else if (MMChoice.equals("2")) { // add a product
                    System.out.println("Enter a Product Name: ");
                    String productName = s.nextLine();
                    System.out.println("Enter a Price: ");
                    double price = s.nextDouble();
                    s.nextLine();
                    System.out.println("Enter a Product Name: ");
                    String storeName = s.nextLine();
                    System.out.println("Enter a Quantity: ");
                    int quantity = s.nextInt();
                    s.nextLine();
                    System.out.println("Enter a description: ");
                    String description = s.nextLine();
                    System.out.println(Seller.addProduct(productName, price, storeName,
                                            quantity, description, username));

                } else if (MMChoice.equals("3")) {  // edit a product


                } else if (MMChoice.equals("4")) { //delete a product

                } else if (MMChoice.equals("5")) { //view store statistics

                } else if (MMChoice.equals("6")) { //import or exports for product
                    //justin's part
                } else if (MMChoice.equals("7")) { //logged out
                    loggedOut = true;
                } else {
                        System.out.println("Please try again with valid input!");
                }

            } else {
                // the user is a Customer type
                System.out.println("Customer Main Menu\n1. View Marketplace\n2. View Shopping Cart\n3. Search for " +
                        "Product\n4. Purchase a Product\n5. View Shopping History\n6. Export/Import Shopping " +
                        "History\n7. " +
                        "View" +
                        " Store Statistics\n8. Log Out");
                String MMChoice = s.nextLine();
                if (MMChoice.equals("1")) {
                    System.out.println("1. View Marketplace\n2. Sort by Ascending Price\n3. Sort by Descending " +
                            "price\n4. Sort by " +
                            "ascending quantity\n5. Sort by Descending Quantity\n(Anything else.) exit");
                    String sortChoice = s.nextLine();
                    if (sortChoice.equals("2")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", true); //not returning
                        // correct values
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str))); //incorrect
                        }
                    } else if (sortChoice.equals("3")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", false); //not returning
                        // correct values
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str))); //incorrect
                        }
                    } else if (sortChoice.equals("4")) {
                         ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", true); //not returning
                        // correct values
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str))); //incorrect
                        }
                    } else if (sortChoice.equals("5")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", false); //not returning
                        // correct values
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str))); //incorrect
                        }
                    } else if (sortChoice.equals("1")) {
                        Marketplace.printMarketplace();
                    } else {
                        System.out.println("Please go back and try again!");
                    }
                } else if (MMChoice.equals("2")) { //display cart
                    ArrayList<String> shoppingCart = Marketplace.displayCart(username); //error here
                    for (String str : shoppingCart) {
                        System.out.println(str + "\n");
                    }
                    System.out.println("1. Remove Cart Item\n2. buy cart items\n(Anything else.) exit");
                    String cartChoice = s.nextLine();
                    if (cartChoice.equals("1")) {
                        System.out.println("Enter cart item name to be removed");
                        String removeChoice = s.nextLine();
                        Marketplace.removeFromCart(removeChoice, username);
                    } else if (cartChoice.equals("2")) {

                    } else {

                    }
                } else if (MMChoice.equals("3")) { //search for product
                    System.out.println("Enter your search term: ");
                    String keyword = s.nextLine();
                    ArrayList<String> preReq = new ArrayList<>();
                    for (String str : Marketplace.searchProduct(keyword)) {
                        System.out.println(str);
                        preReq.add(str.split(";")[0]);
                    }
                    System.out.println("1. Sort by Ascending Price\n2. Sort by Descending price\n3. Sort by ascending quantity\n4. Sort by Descending Quantity\n(Anything else.) exit");
                    String sortChoice = s.nextLine();
                    if (sortChoice.equals("1")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", true);
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("2")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", false);
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("3")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", true);
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("4")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", false);
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else {
                        System.out.println("try again");
                    }
                } else if (MMChoice.equals("4")) { //purchase a product
//                    Marketplace.purchaseProduct();
                    //need to implement logic for this
                } else if (MMChoice.equals("5")) { //view shopping history
//                    Marketplace.viewShoppingHistory();
                    //need to implement logic for this
                } else if (MMChoice.equals("6")) {  //export/import shopping history
//                    Marketplace.exportShoppingHistory();
                    //need to implement logic for this

                } else if (MMChoice.equals("7")) { //view store statistics
//                    Marketplace.viewStatistics();
                    //need to implement logic for this
                } else if (MMChoice.equals("8")) { //log out
                    loggedOut = true;
                } else {
                    System.out.println("Please try again with valid input!");
                }
            }
        }
        System.out.println("Thank you for using marketplace!");
    }
    
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
