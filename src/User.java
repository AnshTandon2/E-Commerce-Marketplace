import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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

    public boolean checkAccountExists() {
        File f = new File("users.txt");
        boolean returnOption = false;
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(",");
                if (this.name.equals(data[1]) && this.password.equals(data[2])) {
                    returnOption = true;
                }
            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnOption;
    }

    public boolean isBuyer(String name, String password) { //only call this method if user is verified to exist/registered
        File f = new File("users.txt");
        boolean buyerStatus = true;
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(",");
                if (data[1].equals(name) && data[2].equals(password)) {
                    if (data[3].equals("s")) {
                        buyerStatus = false;
                    }
                }

            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buyerStatus;
    }

    public void makeNewUser(String name, String password, String email, boolean buyer) {
        File f = new File("users.txt");
        try {
            FileWriter fw = new FileWriter(f);
            if (buyer) {
                fw.write("\n");
                fw.write(email + "," + name + "," + password + ",b");
                File f2 = new File("/src/Buyers/" + name +".txt");
                f2.createNewFile();
                FileWriter fw2 = new FileWriter(f2);
                fw2.write(name);
                fw2.write("\n");
                fw2.write(password);
                fw2.write("\n");
                fw2.close();
            } else {
                fw.write("\n");
                fw.write(email + "," + name + "," + password + ",s");
                File f3 = new File("/src/Sellers/" + name + ".txt");
                f3.createNewFile();
                FileWriter fw3 = new FileWriter(f3);
                fw3.write(name);
                fw3.write("\n");
                fw3.write(password);
                fw3.write("\n");
                fw3.close();
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("A fatal error has occured");
        }
    }


    public boolean accountExists(String email, String password) {
        return (this.email.equals(email) && this.password.equals(password));
    }
}
