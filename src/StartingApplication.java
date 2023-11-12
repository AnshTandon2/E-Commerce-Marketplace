import java.util.ArrayList;

public class StartingApplication {

    public static AccountManager am = new AccountManager();

    public StartingApplication() {
        System.out.println("Welcome to the Purdue Bazaar! ");

        runner();
    }

    public void runner() {
        Scanner s = new Scanner(System.in);

        try {
            int value = 0;
            while (!(value >= 1 && value <= 3)) {
                System.out.println("Please select one of the three options: " +
                        "1. Login to Application" +
                        "2. Sign Up to Application" +
                        "3. Exit");

                value = Integer.parseInt(s.nextLine());
                if (value == 1) {
                    signIn(s);
                } else if (value == 2) {
                    signUp(s);
                } else if (value == 3) {
                    System.out.println("Thank you for using Purdue Bazaar!");
                } else {
                    System.out.println("Please try again!");
                }
            }
        } catch (Exception e) {
            System.out.println("Error in way you entered number, please try again");
            runner();
        }

        //ends the application completely

    }


    public void signIn(Scanner s) {
        System.out.println("Please enter your name: ");
        String name = s.nextLine();
        System.out.printf("Hi, %s!", name;)
        System.out.println("Email: ");
        String email = s.nextLine();
        System.out.println("Password: ");
        String password = s.nextLine();
//        boolean accountExists = Login.verifyLoginInformation(name, email, password); //fix this
        ArrayList<User> accounts = getAccounts();
        boolean flag = false;
        for (User a : accounts) {
            if (a.getEmail().equals(email) && a.getPassword().equals(password)) {
                System.out.println("Logged In!");
                flag = true;
                if (a instanceof Seller) {
                    Seller seller = (Seller) a;
                    seller.nextPart(); //further prompt implementation of seller
                } else {
                    Customer customer = (Customer) a;
                    customer.nextPart(); //further prompt implementation of seller
                }
            }
        }
        if (!flag) {
            int input = 0;
            while (!input.equals(1) || !input.equals(2)) {
                try {
                    System.out.println("Error in signing in, please select from the following options: " +
                            "1. Try again to Sign In" +
                            "2. Go back to main");
                    if (input == 1) {
                        this.signIn();
                    } else if (input == 2) {
                        this.runner();
                    } else {
                        System.out.println("Entered something wrong try again!");
                    }
                } catch (Exception e) {
                    System.out.println("You didn't enter a number, please enter an integer");
                }
            }

        }

        if (accountExists) {
            signIn();
            printStartDashboard();
        } else {
            accountSetUp(name, email, password);
        }
    }

    public void signUp(Scanner s) {
        System.out.println();
        System.out.println("It seems like you don't have an existing account, so please create a new User");
        System.out.println("Please enter your name: ");
        String name = s.nextLine();
        System.out.println("Please enter your unique email (this will serve as your id): "); //check if it is unique
        // later
        String email = s.nextLine();
        System.out.println("Please enter your designated password");
        boolean unchecked = true;
        while (unchecked) {
            System.out.println("Would you like to make an account (type yes or no)? ");
            String makeAccount = s.nextLine();
            if(makeAccount.equalsIgnoreCase("yes")) {
                unchecked = false;

                while (true) {
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
    }
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

}
