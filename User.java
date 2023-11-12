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
<<<<<<< HEAD
>>>>>>> 157e4498a2163c0e5599a3e555aa70d6c6c6e66c
//    private String name;
    private String username; //use as id
    private String email;
//    private int userId;
    private String password;

    //remove 
=======
    private String name;
    private String email;
    private String password;
    
    public static int userCounter = 1;

>>>>>>> 0bfe2190a8640280e9373b0507615174f853b352
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        userCounter++;
        map.put(email, password);
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
