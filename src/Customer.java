import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Customer Class
 * <p>
 * Initializes a customer object and
 * manages their buying history and identification
 * information.
 * <p>
 * Customer info is saved in the following format:
 * First Line: email Name
 * Second Line to productLength+1 Line: Product.toString() + quantity;
 * productLength+2 Line: Shopping Cart:
 * ProductLength+3 - productLength+3+ShoppingCartLength: Product.toString() + quantity;
 *
 * @author Nirmal Senthilkumar, Ankita Majumdar, Lalitha Chandolu; CS 180 Black
 * @version November 11, 2023
 */
public class Customer extends User {
    private final File customerHistory; //file that holds the purchase history and shopping cart
    private final HashMap<Product, Integer> purchaseHistory; //hashmap with products purchased and quantity
    private final HashMap<Product, Integer> shoppingCart; //hashmap with products in cart and the quantity

    public Customer(String name, String email, String password) {
        super(name, email, password);
        super.addUser(this);
        // the purchase history file of every Customer will follow this notation
        String fileNotation = ("/data/" + email + "-CustomerHistory.txt");
        this.customerHistory = new File(fileNotation);
        this.purchaseHistory = new HashMap<>();
        this.shoppingCart = new HashMap<>();
        if (!customerHistory.exists()) { //if the file doesn't exist (Customer is new)
            try {
                FileWriter fw = new FileWriter(this.customerHistory, false);
                BufferedWriter bfw = new BufferedWriter(fw);
                //First Line: [example@.email.com;John Doe]
                bfw.write(String.format(this.getEmail() + ";" + this.getName() + "\n"));
            } catch (IOException ignored) {
            }
        } else { //file exists, reading from it
            try {
                FileReader fr = new FileReader(this.customerHistory);
                BufferedReader bfr = new BufferedReader(fr);
                String line = bfr.readLine();
                ArrayList<String> list = new ArrayList<>();
                while (line != null) {
                    list.add(line);
                    line = bfr.readLine();
                }
                list.remove(0); //throw the email and name line, unneeded
                boolean shoppingCartEntries = false;
                for (String s : list) {
                    if (s.startsWith("Shopping Cart:")) { //if the data in the txt pertains to Shopping Cart
                        shoppingCartEntries = true; //start writing the following data to shoppingCart HashMap
                        continue; //from next iteration write only to shopping cart
                    }
                    //Ex. Product<productName;Price;Description>;Quantity
                    // split at semicolon, then drop the Product< in the beginning, and when adding description drop
                    // the closing '>' using substring
                    String[] split = s.substring(8).split(";");
                    if (shoppingCartEntries) {
                        shoppingCart.put(new Product(split[0], Double.parseDouble(split[1]), split[2].substring(0,
                                split[2].length() - 1)), Integer.parseInt(split[3]));
                    } else {
                        purchaseHistory.put(new Product(split[0], Double.parseDouble(split[1]),
                                split[2].substring(0, split[2].length() - 1)), Integer.parseInt(split[3]));
                    }
                }
            } catch (IOException ignored) {
            }
        }
    }


    public HashMap<Product, Integer> getProductHistoryMap() {
        return purchaseHistory;
    }

    public void updatePurchaseHistoryFile() {
        try {
            FileWriter fw = new FileWriter(this.customerHistory, false);
            BufferedWriter bfw = new BufferedWriter(fw);
            String productInfo;
            bfw.write(String.format(this.getEmail() + ";" + this.getName()) + "\n");
            for (Map.Entry<Product, Integer> entry : purchaseHistory.entrySet()) {
                bfw.write(entry.getKey().toString() + ";" + entry.getValue() + "\n");
            }
            bfw.write("Shopping Cart:\n");
            for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                bfw.write(product + ";" + quantity + "\n");
            }
            bfw.flush();
            bfw.close();
        } catch (IOException ignored) {
        }
    }

    public void modifyPurchaseHistoryFile(ArrayList<Product> list) {
        // takes the existing purchase history file for the user
        // and appends a new product purchase to the customer's purchase history
        try {
            FileWriter fw = new FileWriter(this.customerHistory, true);
            BufferedWriter bfw = new BufferedWriter(fw);
            int counter = 1;
            for (Product p : list) {
                if (!purchaseHistory.containsKey(p))
                    bfw.write(p.toString() + "\n");
            }
        } catch (IOException ignored) {
        }
    }

    public ArrayList<Product> getPurchaseHistory() { // get purchase history
        return new ArrayList<>(purchaseHistory.keySet());
    }

    public HashMap<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void addProduct(Store store, Product product, int quantity) {
        boolean canAdd;
        if (this.shoppingCart.containsKey(product)) {
            // if the product is already in the customer's shopping cart
            // gets the current quantity of the product
            // specified in the hash map
            Integer productQuantity = shoppingCart.get(product);
            canAdd = Store.checkQuantityAvailable(store, product, quantity);
            if (canAdd) {
                this.shoppingCart.put(product, productQuantity + quantity);
            } else {
                System.out.println("Can't add that quantity into the cart because it would exceed the quantity sold.");
            }
        } else {
            canAdd = Store.checkQuantityAvailable(store, product, quantity);
            if (canAdd) {
                this.shoppingCart.put(product, quantity);
            } else {
                System.out.println("Can't remove that quantity. ");
            }
        }
    }

    public void removeProduct(Product product, int quantity) {
        if (this.shoppingCart.containsKey(product)) {
            Integer productQuantity = shoppingCart.get(product);
            if (!(quantity > productQuantity)) {
                this.shoppingCart.put(product, productQuantity - quantity);
            } else {
                System.out.println("Could not remove that quantity; quantity exceeded.");
            }
        }
    }

    public void clearShoppingCart() {
        this.shoppingCart.clear();
    }

    public void viewShoppingCart() {
        System.out.println("Current Shopping Cart: ");
        for (Product p : shoppingCart.keySet()) {
            String productInfo = String.format("Product: %s, Price %.2f, Quantity: %d", p.getName(), p.getPrice(),
                    shoppingCart.get(p));
            System.out.println(p);
        }
        System.out.println("------------------------------");
        System.out.println("Current Subtotal (no taxes): " + this.calculateCartTotal());
    }

    public double calculateCartTotal() {
        double total = 0;
        for (Product p : this.shoppingCart.keySet()) {
            // adds the price of the product to the total
            // multiplies the price of the product by the quantity in the cart
            total += (p.getPrice() * shoppingCart.get(p));
        }
        return total;
    }

    public void exportPurchaseHistory() {
        File f = new File(super.getName() + "PurchaseHistory.csv");
        try {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            fw.write(super.getName() + " Purchase History");
            fw.write("\n");
            fw.write("\n");

            for (Product p : this.purchaseHistory.keySet()) {
                fw.write("Product Name: " + p.getName() + " Quantity: " + purchaseHistory.get(p));
                fw.write("\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
