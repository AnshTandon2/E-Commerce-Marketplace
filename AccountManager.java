import java.util.ArrayList;
import java.util.HashMap;

public class AccountManager {

    private final String INFO_FILE;

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