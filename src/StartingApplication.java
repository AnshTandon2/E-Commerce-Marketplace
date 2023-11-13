import java.util.*;
import java.io.*;

/** Starting Application Class
 * Serves as the Login/Sign up system for a user
 * Verifies that they use an @purdue.edu email and
 * whether they are a buyer or seller
 * The class redirects them to the corresponding menu
 *
 * @author Lalitha Chandolu, Ansh Tandon, Justin Ho-Yuk
 * @version November 12, 2023
 *
 */

public class StartingApplication {

    // public static AccountManager am = new AccountManager();

    public static void main(String[] args) {
        // g2g
        System.out.println("Welcome to the Boilermaker Bazaar! ");
        Scanner s = new Scanner(System.in);
        // welcomes the user to the application
        // redirects them to sign up or sign in page
        boolean redirected = false;
        do {
            try {
                int value = 0;
                System.out.println("Please select one of the three options: " +
                        "1. Login to Application" +
                        "2. Sign Up with a New Account" +
                        "3. Exit");
                value = Integer.parseInt(s.nextLine());
                if (value == 1) {
                    redirected = signIn(s);
                } else if (value == 2) {
                    redirected = signUp(s);
                } else if (value == 3) {
                    System.out.println("Thank you for using Purdue Bazaar!");
                    redirected = true;
                } else {
                    System.out.println("Please try again. Make sure you enter a valid choice.");
                }
            } catch (NumberFormatException e) {
                // user didn;t enter a number
                System.out.println("There was an error in your input, please try again");
            }
        } while (!redirected);
    }

    public static boolean signIn(Scanner s) {
        // g2g
        readUsersFile();
        System.out.println("Please enter your Email: ");
        String email = s.nextLine();
        System.out.println("Please enter your Password: ");
        String password = s.nextLine();
        // verifies that the information matches
        boolean accountExists = User.accountExists(email, password);
        if (accountExists) {
            System.out.println("You're logged in. ");
            // get the user object
            User user = User.getUserObject(email, password);
            // check if they are a customer or seller
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                viewCustomerMainMenu(s, customer);
                return true;
            } else if (user instanceof Seller) {
                Seller seller = (Seller) user;
                viewSellerMainMenu(s, seller);
                return true;
            }
        } else {
            System.out.println("Error. You don't have an existing account.");
            return false;
        }
        return false;
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
                } else {
                    System.out.println("You didn't enter something right, please try again");
                }
            }
            boolean canSignIn = false;
            boolean signedIn = false;
            while (!canSignIn) {
                // allows the new user to choose if  they want to sign
                // in to the marketplace application
                System.out.println("Would you like to sign in to the marketplace now? (type y or n) ");
                String choice = s.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    // redirects them to signIn method
                    canSignIn = true;
                    signedIn = signIn(s);
                } else if (choice.equalsIgnoreCase("n")) {
                    // ends the program here
                    canSignIn = true;
                    System.out.println("Goodbye");
                } else {
                    // repeats the loop for user input
                    canSignIn = false;
                    System.out.println("Sorry, wrong input was given. Please try again.");
                }
            }
        } else {
            System.out.println("Your email was invalid. Make sure you are a Purdue Student.");
            return false;
        }
        return true;
    }

    public static void viewSellerMainMenu(Scanner s, Seller seller) {
        System.out.println("Welcome Seller.");
        boolean choiceIsValid = false;
        while (!choiceIsValid) {
            System.out.println(" Enter a number between 1 to 4: \n " +
                    "1. Manage Stores\n" +
                    "2. View Store Statistics\n" +
                    "3. View Store Selling History\n" +
                    "4. Log Out");
            String choice = s.nextLine();

            if (choice.equals("1")) {
                // Submenu: Manage Stores Options
                choiceIsValid = true;
                System.out.println("Enter a number between 1 - 4: \n");
                System.out.println("1. Add a product");
                System.out.println("2. Delete a product");
                System.out.println("3. Modify a product");
                System.out.println("4. Delete a store");
                choice = s.nextLine();

                if (choice.equals("1")) {
                    // seller.addProduct(); // make in seller
                } else if (choice.equals("2")) {
                    // seller.deleteProduct();
                } else if (choice.equals("3")) {
                    // seller.modifyProduct();
                } else if (choice.equals("4")) {
                    // seller.deleteAStore();
                }

            } else if (choice.equals("2")) {
                // View Store Statistics Option
                //placeholder for seller stats method);
                choiceIsValid = true;

            } else if (choice.equals("3")) {
                // View Store Selling History
                choiceIsValid = true;

            } else if (choice.equals("4")) {
                choiceIsValid = true;
                System.out.println("Goodbye! We hope you visit again.");
                logOut();

            } else {
                System.out.println("Invalid input was given. Please try again with valid input.");
                choiceIsValid = false;
            }

        }
    }

    public static void viewCustomerMainMenu(Scanner s, Customer customer) {
        System.out.println("Welcome Customer.");
        boolean choiceIsValid = false;
        while (!choiceIsValid) {
            System.out.println(" Enter a number between 1 to 4: \n " +
                    "1. View Overall Marketplace\n" +
                    "2. Search for a Store\n" +
                    "3. Search for a product\n" +
                    "4. View Shopping Cart\n" +
                    "5. Sort Marketplace\n" +
                    "6. Export Purchase History\n" +
                    "7. Log Out");
            String choice = s.nextLine();
            if (choice.equals("1")) {
                // use findProductByName("");

            } else if (choice.equals("2")) {
                // use find getStores();
                System.out.println(" Enter a number between 1 to 3: \n " +
                        "1. Sort stores by products sold (ascending) \n" +
                        "2. Sort stores by products sold (descending) \n" +
                        "3. Search for a product\n");

            } else if (choice.equals("3")) {
                System.out.println(" Enter a number between 1 to 4: \n " +
                        "1. Find product by name\n" +
                        "2. Find product by description\n" +
                        "3. Find product by Store\n");
                choice = s.nextLine();

            } else if (choice.equals("4")) {

            } else if (choice.equals("5")) {

            } else if (choice.equals("6")) {

            } else if (choice.equals("7")) {
                choiceIsValid = true;
                System.out.println("Goodbye! We hope you visit again.");
                logOut();

            } else {
                System.out.println("Invalid input was given. Please try again with valid input.");
                choiceIsValid = false;
            }
        }
    }

    public static void readUsersFile() {
        File file = new File("users.txt");
        try {
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                // 3rd element is token for Seller or Customer
                String[] tokens = line.split(",");
                if(tokens[3].equals("c")) {
                    Customer c = new Customer(tokens[1], tokens[0], tokens[2]);
                    User.addUser(c);
                } else if (tokens[3].equals("s")) {
                    Seller s = new Seller(tokens[1], tokens[0], tokens[2]);
                    User.addUser(s);
                }
            } // end of while loop
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void logOut() {
        File file = new File("users.txt");
        file.delete();
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bfw = new BufferedWriter(fw);
            for (User user : User.getExistingUsers()) {
                String userType;
                if (user instanceof Seller) {
                    userType= "s";
                } else {
                    userType = "c";
                }
                String formatLine = String.format("%s,%s,%s,%s\n", user.getEmail(), user.getName(),
                        user.getPassword(), userType);
                bfw.append(formatLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkEmail(String email) {
        // verifies that the email is a valid Purdue email
        if(email.contains("@")) {
            String[] tokens = email.split("@");
            if(tokens[1].equals("purdue.edu")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

} // end of the java file
