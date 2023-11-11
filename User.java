import java.util.*;
import java.time.*;

/**
 * User Class
 * <p>
 * Initiates a user object
 * Parent class of Buyer and Seller
 * Includes private fields including user email and password
 *
 * @author Ansh Tandon, Lalitha Chandolu; CS 180 Black
 * @version November 11, 2023
 */


public class User {
    private String name;
    private String email;
<<<<<<< HEAD

    private int userId;

=======
    private int userId;
>>>>>>> d0b3a12e9426ad4c67f076538a6d4fed8ad403a1
    private String password;

    public static int userCounter = 1;

<<<<<<< HEAD
    public User(String name, String email, String password, int userId) {
=======
    public User(String name, String email, String password) {
>>>>>>> d0b3a12e9426ad4c67f076538a6d4fed8ad403a1
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

<<<<<<< HEAD
    public String getPassword() { return this.password; }

    public int getUserId() { return this.userId; }
}
=======
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
>>>>>>> d0b3a12e9426ad4c67f076538a6d4fed8ad403a1
