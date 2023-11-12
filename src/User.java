import java.util.*;

/**
 * User Class
 * <p>
 * Initiates a user object
 * Parent class of Buyer and Seller
 * Includes private fields including user email and password
 *
 * @author Ansh Tandon, Lalitha Chandolu, Ankita Majumdar; CS 180 Black
 * @version November 11, 2023
 */

public abstract class User {
    private String name;
    private String email;
    private String password;

    private static ArrayList<User> userList = new ArrayList<User>();
    
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<User> getExistingUsers() { return this.userList;}

    public void addUser(User user) {
        if (user instanceof Customer || user instanceof Seller) {
            userList.add(user);
        }
    }

    public static boolean accountExists(String email, String password) {
        for (User u: userList) {
            if (email.equals(u.getEmail()) && password.equals(u.getPassword())) {
                return true;
            }
        }
        return false;
    }
}
