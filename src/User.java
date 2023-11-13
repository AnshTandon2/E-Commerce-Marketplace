import java.io.File;
import java.io.FileWriter;
import java.io.*;
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

public class User {
    private String name;
    private String email;
    private String password;

    private static ArrayList<User> userList = new ArrayList<User>();
    
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static void generateUserList() {
        File f = new File("/data/users.txt");
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(",");
                userList.add(new User(data[1], data[0], data[2]));

            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateUsers() {
        File f = new File("/data/users.txt");
        try {
            FileWriter fw = new FileWriter(f);
            f.delete();
            f.createNewFile();
            for (User user : userList) {
                if (user instanceof Customer) {
                    fw.write(user.getEmail() + "," + user.getName() + "," + user.getPassword() + ",c");
                } else {
                    fw.write(user.getEmail() + "," + user.getName() + "," + user.getPassword() + ",s");
                }
            }
        
            fw.close();    
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static ArrayList<User> getExistingUsers() { return userList;}

    public static void addUser(User user) {
        if (user instanceof Customer || user instanceof Seller) {
            userList.add(user);
        }
    }

    public static User getUserObject(String email, String password) {
        for(User user: userList) {
            if((email.equals(user.getEmail()) && (password.equals(user.getPassword())))) {
                return user;
            }
        }
        return null;
    }

    public static User getUserObject(String email) {
        for(User user: userList) {
            if((email.equals(user.getEmail()))) {
                return user;
            }
        }
        return null;
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
