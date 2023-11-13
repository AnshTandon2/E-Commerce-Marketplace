import java.util.*;
import java.io.*;

public class StartingApplication {

    public static AccountManager am = new AccountManager();

    public static void main(String[] args) {
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

    public static boolean signIn(Scanner s) {
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
            String password =

                        System.out.println("Are you a buyer or seller? (type b for buyer; s for seller");
                        String userType = s.nextLine();
                        if (userType.equalsIgnoreCase("s")) {
                            Seller seller = new Seller(name, email, password);
                            seller.nextPart();
                            break;
//                    am.addSeller();
                        } else if (userType.equalsIgnoreCase("b")) {
                            Customer customer = new Customer(name, email, password);
                            customer.nextPart();
                            break;
                        } else {
                            System.out.println("You didn't enter something right, please try again");
                        }
                    }
                    break;
                } else if (makeAccount.equalsIgnoreCase("no")) {
                    System.out.println("Okay, goodbye!");
                    unchecked = false;
                } else {
                    System.out.println("Sorry your input didn't match. Please try again.");
                }
            }

        } else {

        }
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
            choiceIsValid = true;
            System.out.println("1. Add a product");
            System.out.println("2. Delete a product");
            System.out.println("3. Modify a product");
            System.out.println("4. Delete a store");
        } else if (choice.equals("2")) {
            // view store statistics
            //placeholder for seller stats method);
            choiceIsValid = true;
        } else if (choice.equals("3")) {
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
    
    public static void viewCustomerMainMenu(Scanner s, Customer customer) {
        
        
    }


    /**
    public void customerFile(String email) {
        File file = new File(email + ".txt");
        if (file.exists()) {
            continue;
        } else {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Error in creating new file");
                e.printStackTrace();
            }
        }
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.write("Type - Customer\n");
            pw.write("EmailID: " + email + "\n");
            pw.write("Shopping Cart: ");
            pw.write("\n");
            pw.write("Purchase History: ");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sellerFile(String email) {
        File file = new File(email + ".txt");
        if (file.exists()) {
            continue;
        } else {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Error in creating new file");
                e.printStackTrace();
            }
        }
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.write("Type - Seller\n");
            pw.write("EmailID: " + email + "\n");
            pw.write("Shopping Cart: ");
            pw.write("\n");
            pw.write("Purchase History: ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAccounts() {
        File file = new File("accounts.txt");
        ArrayList<User> users = new ArrayList<>();
        if (file.exists()) {
            continue;
        } else {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Error in creating new file");
                e.printStackTrace();
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ArrayList<String> vars = new ArrayList<>();
            String s;
            while ((s = br.readLine()) != null) {
                vars.add(s);
            }
            for (String temp: vars) {
                String[] arr = temp.split(",");
                users.add(new User(arr[0], arr[1], arr[2]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
        //read through csv and get all of the users that are there in the csv, and return a new file of them
        //make sure we encrypt the passwords with the csv
    }

    public ArrayList<User> addAccounts() {
        //adds new accounts to the array list of users
    }

    */

}
