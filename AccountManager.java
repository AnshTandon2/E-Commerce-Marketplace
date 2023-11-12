import java.util.ArrayList;
import java.util.HashMap;

public class AccountManager {

    private final String INFO_FILE;
    //option within userdashboard asking to edit ur account information. If you are a buyer or seller, you will have
    // a dashboard with all your account and one of them would be to access your account details.
    // list of options that user picks (in the dashboard --> list of options of view store, add product, or change
    // account details, or view account details, display statistics)
    // big dashboard,
    // statistics --> statistics menu 
    // buy --> view store, choose store to buy from, and then choose product (
    // editing the store, viewing current store, shopping, 
    // main method
    // marketplace file --> (runner) 
    // testing file --> main (test market place)
    // check the file
    // error exceptions
    // have testers for each of your classes 
    // buyer --> ass


    public static ArrayList<User> seller = new ArrayList<User>();
    public static ArrayList<User> customers = new ArrayList<User>();

    public AccountManager(String infoFile) {
        this.INFO_FILE = infoFile;
//        sellerArrayList.clear();
//        customerArrayList.clear();

        try (BufferedReader bfr = new BufferedReader(new FileReader(passwordFile))) {
            String userString = bfr.readLine();
            while (userString != null) {
                if (userString.substring(0, userString.indexOf("<")).equalsIgnoreCase("Seller")) {
                    Seller seller = new Seller(userString, true, true);
                    sellerArrayList.add(seller);
                } else {
                    Customer customer = new Customer(userString, true);
                    customerArrayList.add(customer);
                }
                userString = bfr.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (Exception e) {
            System.out.println("Error: Users Did Not Populate");
        }

    }


    public boolean signUp(String email, String password) throws Exception {
        int userID = 0;
        try {
            userID = Users.getUserId(email);
            if (this.profile(userID)) {
                this.render("error", "message", "already registered try to sign in");
                return false;
            } else {
                User user = User.register(email, password).user;
                this.render("profile", "user", user);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrack();
        }

    }


    public boolean signIn(String email, String password) throws Exception {
        int userID = 0;

        try {
            userID = Users.getUserId(email);
            if (this.profile(userID)) {
                User user = User.login(email, password);
                if (userID.getPassword().equals(password)) {
                    this.render("profile", "user", user);
                    return true;
                } else {
                    this.render("error", "message", "incorrect password");
                    return false;
                }
            }
        } catch (Exception e) {
            this.render("error", "message", "could not sign in");
            e.printStackTrack(); //error exception handling.
            return false;
        }

    }

}