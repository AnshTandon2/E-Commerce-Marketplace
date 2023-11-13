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
    private final HashMap<Integer, Integer> purchaseHistory; //hashmap with products purchased and quantity
    private final HashMap<Integer, Integer> shoppingCart; //hashmap with products in cart and the quantity
    private String fileNotation;

    public Customer(String name, String email, String password) {
        super(name, email, password);
        // the purchase history file of every Customer will follow this notation
        this.purchaseHistory = new HashMap<>();
        this.shoppingCart = new HashMap<>();
        this.fileNotation = ("/data/" + getEmail() + "-CustomerHistory.txt");
        addUser(this);
    }

    public void updateCustomerHistory(int productID, int quantity) {
        try {
            File customerHistory = new File(getFileNotation());
            FileReader fr = new FileReader(customerHistory);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            ArrayList<String> list = new ArrayList<>();
            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
            bfr.close();
            fr.close();
            FileWriter fw = new FileWriter(customerHistory, false);
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write(list.get(0));
            bfw.write(list.get(1));
            bfw.write(list.get(2));
            bfw.write(list.get(3) + ";" + productID);
            bfw.write(list.get(4) + ";" + quantity);
            bfw.flush();
            bfw.close();
            fw.close();
        } catch (IOException ignored) {
        }
    }

    public void populateCustomerHistory() {
        File customerHistory = new File(getFileNotation());
        if (!customerHistory.exists()) { //if the file doesn't exist (Customer is new)
            try {
                FileWriter fw = new FileWriter(customerHistory, false);
                BufferedWriter bfw = new BufferedWriter(fw);
                //First Line: [example@.email.com;John Doe]
                bfw.write(String.format(getName() + "\n" + getEmail() + "\n" + getPassword() + "\n"));
            } catch (IOException ignored) {
            }
        } else { //file exists, reading from it
            try {
                FileReader fr = new FileReader(customerHistory);
                BufferedReader bfr = new BufferedReader(fr);
                String line = bfr.readLine();
                ArrayList<String> list = new ArrayList<>();
                while (line != null) {
                    list.add(line);
                    line = bfr.readLine();
                }
                //super.setName();
                //list.remove(0);
                //list.remove(1);
                //list.remove(2);//throws the name, email, and password lines away
                String[] productPurchasedHistoryRaw = list.get(3).split(";");
                String[] quantityPurchasedHistoryRaw = list.get(4).split(";");
                for (int i = 0; i < productPurchasedHistoryRaw.length; i++) {
                    purchaseHistory.put(Integer.parseInt(productPurchasedHistoryRaw[i]),
                            Integer.parseInt(quantityPurchasedHistoryRaw[i]));
                }
                String[] productShoppingCartRaw = list.get(3).split(";");
                String[] quantityShoppingCartRaw = list.get(4).split(";");
                for (int i = 0; i < productShoppingCartRaw.length; i++) {
                    shoppingCart.put(Integer.parseInt(productShoppingCartRaw[i]),
                            Integer.parseInt(quantityShoppingCartRaw[i]));
                }
                bfr.close();
                fr.close();
            } catch (IOException ignored) {
            }
        }
    }


    public void viewAllProducts() {
        ArrayList<Product> temp = getProducts(); //get all the information from product file
        System.out.println("All Products: ");
        for (int i = 0; i < temp.size(); i++) {
            System.out.println(i + ") " + temp.get(i));
        }
    }

    public boolean addProduct(Store store, int productID, int quantity) {
        boolean canAdd;
        if (this.shoppingCart.containsKey(productID)) {
            // if the product is already in the customer's shopping cart
            // gets the current quantity of the product
            // specified in the hash map
            if (Store.checkQuantityAvailable(store, productID,
                    shoppingCart.get(productID) + quantity)) {
                this.shoppingCart.put(productID, shoppingCart.get(productID) + quantity);
                return true;
            } else {
                return false;
            }
        } else {
            if (Store.checkQuantityAvailable(store, productID, quantity)) {
                this.shoppingCart.put(productID, shoppingCart.get(productID) + quantity);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean removeProduct(Integer productID, int quantity) {
        if (this.shoppingCart.containsKey(productID)) {
            if (quantity <= shoppingCart.get(productID)) {
                this.shoppingCart.put(productID, shoppingCart.get(productID) - quantity);
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Product> getProducts() { //read the product file
        File file = new File("products.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList<Product> allProducts = new ArrayList<Product>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                allProducts.add(new Product(tokens[0], Double.parseDouble(tokens[1]), tokens[2]));
            } // end of while loop
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    public void exportHistory() {

    }

    public void setProducts() { //set the products.txt

    }
    public void clearShoppingCart() {
        shoppingCart.clear();
    }

    /* move to main method (no printing here), just call get shopping cart and do it there
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
     */

    public double calculateCartTotal() {
        double total = 0;
        for (Map.Entry<Integer, Integer> product : shoppingCart.entrySet()) {
            // adds the price of the product to the total
            // multiplies the price of the product by the quantity in the cart
            total += (Product.getProduct(product.getKey()).getPrice() * product.getValue());
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
            for (Integer p : this.purchaseHistory.keySet()) {
                fw.write("Product ID: " + p + " Quantity: " + purchaseHistory.get(p));
                fw.write("\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Integer> getPurchaseHistory() {
        return purchaseHistory;
    }

    public HashMap<Integer, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public String getFileNotation() {
        return fileNotation;
    }

    public void setFileNotation(String fileNotation) {
        this.fileNotation = fileNotation;
    }
}
