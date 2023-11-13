import java.io.*;
import java.util.*;

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
        // g2g
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
                    System.out.println("Are you signing up as a:\n [1] Seller\n[2] Buyer");
                    String roleChoice = s.nextLine();
                    // checks if they have an existing account or not
                    userRole = accountExists(username, password);
                    // they don't have an existing account - so they can make an account
                    if (userRole == null) {
                        // they want to have the role of a seller
                        File f = new File("/data/users.txt");
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

<<<<<<< HEAD
    public static boolean signIn(Scanner s) {
        // g2g
        readUsersFile();
        System.out.println("Please enter your Email: ");
        String email = s.nextLine();
        System.out.println("Please enter your Password: ");
        String password = s.nextLine();
        // verifies that the information matches
        boolean accountExists = userExists(email, password);
        if (accountExists) {
            System.out.println("You're logged in. ");
            // get the user object
            User user = User.getUserObject(email, password);
            // check if they are a customer or seller
            if (user instanceof Customer customer) {
                viewCustomerMainMenu(s, customer);
                return true;
            } else if (user instanceof Seller seller) {
                viewSellerMainMenu(s, seller);
                return true;
            }
        } else {
            System.out.println("Error. You don't have an existing account.");
            return false;
        }
        return false;
    }

    public static boolean userExists(String email, String password) {


    }

    public static boolean signUp(Scanner s) {
        System.out.println();
        System.out.println("Please create a new user account");
        System.out.println("Please enter your name: ");
        String name = s.nextLine();
        System.out.println("Please enter your unique email (this will serve as your id): ");
        String email = s.nextLine();
        boolean valid = checkEmail(email);
        // email is valid - continue
        if (valid) {
            System.out.println("Please enter your designated password");
            String password = s.nextLine();
            boolean isValidUser = false;
            if (!isValidUser) {
                System.out.println("Are you a buyer or seller? (type b for buyer; s for seller");
                String userType = s.nextLine();
                if (userType.equalsIgnoreCase("s")) {
                    Seller seller = new Seller(name, email, password);
                    isValidUser = true;
                } else if (userType.equalsIgnoreCase("b")) {
                    Customer customer = new Customer(name, email, password);
                    isValidUser = true;
=======

        boolean loggedOut = exitOnFirst;
        Marketplace.initializeMarketplace();
        while (!loggedOut) {
            if (userRole.equalsIgnoreCase("seller")) {
                System.out.println("Seller Main Menu:\n1. View Marketplace\n2. View all sales by store\n3. Add a Product\n" +
                        "4. Edit a Product\n5. Delete a Product\n6. View Store Statistics\n7. Log Out");
                String MMChoice = s.nextLine();
                if (MMChoice.equals("1")) {
                    Marketplace.printMarketplace();
                } else if (MMChoice.equals("2")) {
                    System.out.println();
                } else if (MMChoice.equals("3")) {

                } else if (MMChoice.equals("4")) {

                } else if (MMChoice.equals("5")) {

                } else if (MMChoice.equals("6")) {

                } else if (MMChoice.equals("7")) {
                    loggedOut = true;
>>>>>>> d3a12059b9c6af8dd71612a4c4ccf7c1e2ba21d5
                } else {
                    System.out.println("Please try again with valid input!");
                }


            } else {
                // the user is a Customer type
                System.out.println("Customer Main Menu:\n1. View Marketplace\n2. View Shopping Cart\n3. Search for Product\n" +
                        "4. View Shopping History\n5. Log Out");
                String MMChoice = s.nextLine();
                if (MMChoice.equals("1")) {
                    Marketplace.printMarketplace();
                    System.out.println("1. Sort by Ascending Price\n2. Sort by Descending price\n3. Sort by ascending quantity\n4. Sort by Descending Quantity\n(Anything else.) exit");
                    String sortChoice = s.nextLine();
                    if (sortChoice.equals("1")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket(null, "price", "asc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("2")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket(null, "price", "dsc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("3")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket(null, "quantity", "asc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("4")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket(null, "quantity", "dsc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else {
                        System.out.println("try again");
                    }
                } else if (MMChoice.equals("2")) {
                    ArrayList<String> shoppingCart = Marketplace.displayShoppingCart(username);
                    for (String str : shoppingCart) {
                        System.out.println(str + "\n");
                    }
                } else if (MMChoice.equals("3")) {
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
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", "asc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("2")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("price", "dsc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("3")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", "asc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else if (sortChoice.equals("4")) {
                        ArrayList<String> sortedIndexes = Marketplace.sortMarket("quantity", "dsc");
                        for (String str : sortedIndexes) {
                            System.out.println(Marketplace.getProductInfo(Integer.parseInt(str)));
                        }
                    } else {
                        System.out.println("try again");
                    }
                } else if (MMChoice.equals("4")) {

                } else if (MMChoice.equals("5")) {
                    loggedOut = true;
                } else {
                    System.out.println("Please try again with valid input!");
                }

            }
        }
    }
    public static String accountExists (String username, String password) {
        // parses the file of all of the existing user in the marketplace
        File f = new File("/data/users.txt");
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
