import java.util.*;
import java.time.*;

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


public class User {
    private String name;
    private String email;
    private int userId;

    private String password;

    public static int userCounter = 1;


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userId = userCounter;
        userCounter++;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }


    public String getPassword() { 
        return this.password; 
    }

    public int getUserId() { 
        return this.userId; 
    }

    public void setEmail(String email) {
        this.email = email;
    }    

    public void setPassword(String password) {
        this.password = password;
    }    

    public boolean accountExists(String email, String password) {
        return (this.email.equals(email) && this.password.equals(password));
    }    
}
