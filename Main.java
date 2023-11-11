import java.util.*;

public Class Main {

        public static AccountManager am = new AccountManager()

        public static void main (String[] args) {
            Scanner s = new Scanner(System.in);
            System.out.println("Welcome to the Purdue Bazaar! ");
            System.out.println("Please log in with your Email & Password: ");
            userLogin(Scanner s);
        }

        public boolean userLogin(Scanner s) {
            System.out.println("Please enter your name: ");
            String name = s.nextLine();
            System.out.printf("Hi, %s!", name;)
            System.out.println("Email: ");
            String email = s.nextLine();
            System.out.println("Password: ");
            String password = s.nextLine();
            boolean accountExists = Login.verifyLoginInformation(name, email, password);
            if (accountExists) {
                signIn();
                printStartDashboard();
            } else {
                accountSetUp(name, email, password);
            }
        }

        public void signIn() {

        }

        public void accountSetUp(String name, String email, String password) {
            System.out.println();
            System.out.println("It seems like you don't have an existing account.");
            boolean unchecked = true;
            while (unchecked) {
                System.out.println("Would you like to make an account (type yes or no)? ");
                String makeAccount = s.nextLine();
                if(makeAccount.equalsIgnoreCase("yes")){
                    unchecked = false;
                    System.out.println("Are you a buyer or seller? (type b for buyer; s for seller");
                    String userType = s.nextLine();
                    if (userType.equalsIgnoreCase("s")){
                        Seller seller = new Seller(name,email,password);
                        am.addSeller();
                    } else if (userType.equalsIgnoreCase("b")){
                        Buyer buyer = new Buyer(name, email, password);

                    }
                } else if(makeAccount.equalsIgnoreCase("no")){
                    System.out.println("Okay, goodbye!");
                    unchecked = false;
                } else{
                    System.out.println("Sorry your input didn't match. Please try again.");
                }
            }
        }

        public void printStartDashboard() {
            // should print the start dashboard
            MarketPlace.printDashboard();


        }

}