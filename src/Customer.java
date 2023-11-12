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
 *
 * Customer info is saved in the following format:
 * First Line: email Name
 * Second Line to productLength+1 Line: Product.toString() + quantity;
 * productLength+2 Line: Shopping Cart:
 * ProductLength+3 - productLength+3+ShoppingCartLength: Product.toString() + quantity;
 *
 * @author Ankita Majumdar, Lalitha Chandolu, Nirmal Senthilkumar; CS 180 Black
 * @version November 11, 2023
 */
public class Customer extends User {
    private File purchaseHistory;
    private HashMap<Product, Integer> purchaseHistoryList;
    private HashMap<Product, Integer> shoppingCart;

    public Customer(String name, String email, String password) {
        super(name, email, password);
        // the purchase history file of every user will follow this notation
        String fileNotation = (email+"-CustomerHistory.txt");
        this.purchaseHistory = new File(fileNotation);
        if (!purchaseHistory.exists()) {
            try {
                FileWriter fw = new FileWriter(this.purchaseHistory, false);
                BufferedWriter bfw = new BufferedWriter(fw);
                bfw.write(String.format(this.getEmail() + ";" + this.getName()+"\n"));
            } catch (IOException ignored) {}
        }
        else {
            try {
                FileReader fr = new FileReader(this.purchaseHistory);
                BufferedReader bfr = new BufferedReader(fr);
                String line = bfr.readLine();
                ArrayList<String> list = new ArrayList<>();
                while (line != null) {
                    list.add(line);
                    line = bfr.readLine();
                }
                list.remove(0);
                boolean shoppingCartEntries = false;
                for (String s : list) {
                    if (s.contains("Shopping Cart:")) {
                        shoppingCartEntries = true;
                        continue;
                    }
                    String[] split = s.substring(8, s.length() - 1).split(";");
                    if (shoppingCartEntries) {
                        shoppingCart.put(new Product(split[0], Double.parseDouble(split[1]),
                                split[2].substring(0,split[2].length()-1)), Integer.parseInt(split[3]));
                    }
                    else {
                        purchaseHistoryList.put(new Product(split[0], Double.parseDouble(split[1]),
                                split[2].substring(0,split[2].length()-1)), Integer.parseInt(split[3]));
                    }
                }
            } catch (IOException ignored) {}
        }
        shoppingCart = new HashMap<Product, Integer>();
    }

    public HashMap<Product, Integer> getProductHistoryMap() {
        return purchaseHistoryList;
    }

    public void updatePurchaseHistoryFile() {
        try {
            FileWriter fw = new FileWriter(this.purchaseHistory, false);
            BufferedWriter bfw = new BufferedWriter(fw);
            String productInfo;
            bfw.write(String.format(this.getEmail() + ";" + this.getName())+"\n");
            for (Map.Entry<Product, Integer> entry : purchaseHistoryList.entrySet()) {
                bfw.write(entry.getKey().toString()+";"+entry.getValue()+"\n");
            }
            bfw.write("Shopping Cart:");
            for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();
                bfw.write(product +";"+quantity+"\n");
            }
            bfw.flush();
            bfw.close();
        } catch (IOException ignored) {}
    }
    
    public void modifyPurchaseHistoryFile(ArrayList<Product> list) {
        // takes the existing purchase history file for the user 
        // and appends a new product purchase to the customer's purchase history
        try {
            FileWriter fw = new FileWriter(this.purchaseHistory, true);
            BufferedWriter bfw = new BufferedWriter(fw);
            int counter = 1;
            for (Product p : list) {
                if (!purchaseHistoryList.containsKey(p)) bfw.write(p.toString()+"\n");
            }
        } catch (IOException ignored) {}
    }

    public ArrayList<Product> getPurchaseHistoryList() {
        // get purchase history
        return new ArrayList<Product>(purchaseHistoryList.keySet());
    }


    public HashMap<Product, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public void addProduct(Product product, int quantity) {
        if (this.shoppingCart.containsKey(product)) {
            // if the product is already in the customer's shopping cart
            // gets the current quantity of the product
            // specified in the hash map
            Integer productQuantity = shoppingCart.get(product);
            this.shoppingCart.put(product, productQuantity + quantity);
        } else {
            this.shoppingCart.put(product, quantity);
        }
    }

    public void removeProduct(Product product, int quantity) {
        if (this.shoppingCart.containsKey(product)) {
            Integer productQuantity = shoppingCart.get(product);
            this.shoppingCart.put(product, productQuantity - quantity);
        }
    }

    public void clearShoppingCart() {
        this.shoppingCart.clear();
    }

    public void viewShoppingCart() {
        System.out.println("Current Shopping Cart: ");
        for (Product p : shoppingCart.keySet()) {
            String productInfo = String.format("Product: %s, Price %.2f, Quantity: %d",
                    p.getName(), p.getPrice(), shoppingCart.get(p));
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
}
