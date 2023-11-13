/*
 * Program Name
 * <p>
 * Brief description of program
 * @author Ansh Tandon, CS 180 Black
 * @version Date of Completion
 *
 */// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello and welcome!");

        String userName = "";
        String password = "";
        String email = "";

        boolean loggedIn = false;
        boolean exitFromFirst = false;
        do {
            try {
                System.out.println("Select an option:\n1. Login\n2. Create New Account\n3. Exit");
                String firstPgOption = scan.nextLine();
                if (Integer.parseInt(firstPgOption) == 1) {
                    System.out.println("Please Enter your Username");
                    String un = scan.nextLine();
                    System.out.println("Please enter your password"); 
                    String pw = scan.nextLine();
                    if (!User.accountExists(un, pw)) {
                        System.out.println("Username or password incorrect");
                    } else {
                        userName = un;
                        password = pw;
                        loggedIn = true;
                    }
                } else if (Integer.parseInt(firstPgOption) == 2) {
                    System.out.println("Please enter your new Username");
                    String newUN = scan.nextLine();
                    System.out.println("Please enter your new password");
                    String newPW = scan.nextLine();
                    System.out.println("Please enter your new email");
                    String newEmail = scan.nextLine();
                    System.out.println("Would you like to be a buyer (enter 1) or seller (enter 2)?");
                    String sellerBuyer = scan.nextLine();
                    if (User.accountExists(newEmail, newPW)) {
                        System.out.println("Account already exists");
                    } else {
                        if (Integer.parseInt(sellerBuyer) == 1) {
                            User.addUser(new User(newUN, newEmail, newPW));
                        } else if (Integer.parseInt(sellerBuyer) == 2) {
                            User.addUser(new User(userName, newEmail, newPW));
                        }
                        //loggedIn = true;
                    }
                } else if (Integer.parseInt(firstPgOption) == 3) {
                    exitFromFirst = true;
                } else {
                    System.out.println("Please enter a valid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid choice");
            }
        } while (!loggedIn);
        
        boolean loggedOut = false;

        if (exitFromFirst) {
            loggedOut = true;
        }
       
        User user = new User(userName, email, password);
        while (!loggedOut) {

            if (user instanceof Seller) {
                System.out.println("Main Menu Options:");
                System.out.println("1. Search for items");
                System.out.println("2. View Purchase history");
                System.out.println("3. View Statistics Dashboard");
                System.out.println("4. Logout");
                String mainMenuOption = scan.nextLine();

                if (mainMenuOption.equals("1")) {

                } else if (mainMenuOption.equals("2")) {

                } else if (mainMenuOption.equals("3")) {

                } else if (mainMenuOption.equals("4")) {
                    loggedOut = true;
                } else {
                    System.out.println("Please enter a valid choice!");
                }
                

            } else {
                System.out.println("Main Menu Options:");
                System.out.println("1. Manage my stores");
                System.out.println("2. View Selling history of stores");
                System.out.println("3. View Statistics Dashboard");
                System.out.println("4. Logout");
                String mainMenuOption = scan.nextLine();

                if (mainMenuOption.equals("1")) {

                } else if (mainMenuOption.equals("2")) {

                } else if (mainMenuOption.equals("3")) {

                } else if (mainMenuOption.equals("4")) {
                    loggedOut = true;
                } else {
                    System.out.println("Please enter a valid choice!");
                }
                
            }
        }

        System.out.println("Thank you for visiting! Application shutting down");

        scan.close();

        
        // Press Ctrl+R or click the green arrow button in the gutter to run the code.
        /*for (int i = 1; i <= 5; i++) {

            // Press Ctrl+D to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Cmd+F8.
            System.out.println("i = " + i);
        } */
    }
}