import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Customer Class
 * <p>
 * Initializes a customer object and
 * manages their buying history and identification
 * information.
 *
 * @author Ankita Majumdar, Lalitha Chandolu; CS 180 Black
 * @version November 11, 2023
 */
public class Customer extends User {
    private ShoppingCart shoppingCart;
    private File purchaseHistory;
    private static ArrayList<File> customerHistoryFiles = new ArrayList<>();

    // private static ArrayList<Customer> customerArrayList;
    // other attributes from User class that customer has include:
        // name
        // email
        // password
        // userId

    public Customer(String name, String email, String password) {
        super(name, email, password);
        // the purchase history file of every user will follow this notation
        String fileNotation = (name + "_" + userId);
        this.purchaseHistory = new File(fileNotation);
        initiatePurchaseHistoryFile();
        this.shoppingCart = new ShoppingCart();

        // adds this customer object to the ArrayList of customers
        // customerArrayList.append(this);
    }

    public void initiatePurchaseHistoryFile() {
        // creates the header for the customer's purchase history file when they
        // create an account on Boilermaker Bazaar
        FileWriter fw = new FileWriter(this.purchaseHistory, false);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.append(String.format("Purchase History Report for %s:", this.getName()));
        // appends the new purchase file made for this customer
        // to the static list of customer history files
        customerHistoryFiles.append(this.purchaseHistory);
    }
    
    public void modifyPurchaseHistoryFile(ArrayList<Product> list) {
        // takes the existing purchase history file for the user 
        // and appends a new product purchase to the customer's purchase history
        FileWriter fw = new FileWriter(this.purchaseHistory, false);
        BufferedWriter bfw = new BufferedWriter(fw);
        String productInfo;
        try {
            int counter = 1;
            for (Product p : list) {
                productInfo = String.format("%d. Product: %s ; Product Description: %s ; Price %.2f",
                        counter, p.getName(), p.getProductId, p.getPrice());
                bfw.append(productInfo);
                counter++;
            }
        } catch (NullPointerException e) {
            // catch case for if ArrayList provided is null
            return;
        }
    }
    
    public void exportPurchaseHistory() {
        // returns the customer's purchase history file
        return this.purchaseHistory();
    }

    public static File getCustomerPurchaseHistory(String name, int userId) {
        // allows another class to receive a purchase history file
        // as long as they know the customer's name and unique userId
        for(File f: customerHistoryFiles) {
            if f.getName().equals(name + "_" + userId) {
                return f;
            }
        }
        return -1;
    }

    public void addToShoppingCart(String product) {
        // adds an item to the customer's shopping cart
        shoppingCart.addProduct(product);
    }

    public void removeFromShoppingCart(String product) {
        // removes an item from the customer's shopping cart
        shoppingCart.removeProduct(product);
    }

    public void viewShoppingCart() {
        // allows the customer to view what is in their shopping cart
        // data persistence allows user to sign out and still be able
        // to view cart when they sign in again
        shoppingCart.viewShoppingCart();
    }

    public static void main(String[] args) {
        Customer justin = new Customer("Justin", "justin@example.com", "password123");

        // Example usage
        if (justin.login("justin@example.com", "password123")) {
            justin.addToShoppingCart("Product 1");
            justin.addToShoppingCart("Product 2");

            justin.viewShoppingCart();

            justin.removeFromShoppingCart("Product 1");

            justin.viewShoppingCart();

            justin.exportPurchaseHistory();
        } else {
            System.out.println("Invalid login information.");
        }
    }
}
