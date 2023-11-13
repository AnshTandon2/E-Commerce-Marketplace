import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Seller Class
 * <p>
 * Initializes a seller object and
 * manages their selling history and identification
 * information.
 *
 * @author Nirmal Senthilkumar; Ansh Tandon CS 180 Black
 * @version November 12, 2023
 */
public class Seller extends User {
    // fields inherited from parent code: name, email, password
    private final File StoreHistory;
    private ArrayList<Store> stores;

    public Seller(String name, String email, String password) {
        super(name, email, password);
        // the purchase history file of every user will follow this notation
        String fileNotation = (email + "-storeHistory.txt");
        StoreHistory = new File(fileNotation);
        if (!StoreHistory.exists()) { //if file doesn't exist (Seller is new)
            try {
                FileWriter fw = new FileWriter(StoreHistory, false);
                BufferedWriter bfw = new BufferedWriter(fw);
                //First Line: [example@.email.com;John Doe]
                bfw.write(String.format(this.getEmail() + ";" + this.getName() + "\n"));
            } catch (IOException ignored) {
            }
        } else { //file exists, reading from it
            try {
                FileReader fr = new FileReader(StoreHistory);
                BufferedReader bfr = new BufferedReader(fr);
                String line = bfr.readLine();
                ArrayList<String> list = new ArrayList<>();
                while (line != null) {
                    list.add(line);
                    line = bfr.readLine();
                }
                list.remove(0);
                String storeName = "";
                HashMap<Product, Integer> productMap = new HashMap<>();
                for (String s : list) {
                    if (s.startsWith("Store: ")) { //if the line contains details on Store
                        storeName = s.substring(7); //get storeName while omitting the "Store: " in the beginning of
                        // the line
                    } else if (s.startsWith("Product<")) {//if the line contains details on the products
                        String[] split = line.split("Product<"); //split the list of products using the word Product
                        for (String product : split) {
                            //ex. Product<productName;Price;Description>;Quantity
                            //split the quantity at the end with the product details
                            String[] productAndQuantity = product.split(">");
                            //get product details and split them up into the Array: [productName, Price, Description]
                            String[] productDetail = productAndQuantity[1].split(";");
                            Product newProduct = new Product(productDetail[0], Double.parseDouble(productDetail[1]),
                                    productDetail[2]);
                            //add quantity to the map using the product as the key
                            productMap.put(newProduct, Integer.parseInt(productAndQuantity[2]));
                        }
                    } else if (s.startsWith("TotalSales: ")) {//if the line contains data on TotalSales
                        stores.add(new Store(storeName, productMap, Double.parseDouble(s.substring(12))));
                    }
                }
            } catch (IOException ignored) {
            }
        }
    }

    public void updatePurchaseHistoryFile() {
        try {
            FileWriter fw = new FileWriter(this.StoreHistory, false);
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write(String.format(this.getEmail() + ";" + this.getName()) + "\n");
            for (Store store : stores) {
                bfw.write(store.toString());
            }
            bfw.flush();
            bfw.close();
        } catch (IOException ignored) {
        }
    }

    public void modifyPurchaseHistoryFile(ArrayList<Store> list) {
        // takes the existing store history file for the user
        // and appends a new list of stores to it
        try {
            FileWriter fw = new FileWriter(this.StoreHistory, true);
            BufferedWriter bfw = new BufferedWriter(fw);
            for (Store store : list) {
                if (!stores.contains(store))
                    bfw.write(store.toString());
            }
        } catch (IOException ignored) {
        }
    }


    // be able to view total sales by their store
    // should have their own sales invoice per store
    // sellers should be able to sort sales per product
    // should be able to get a file list for each store which specified the
    // products currently there along with the quantities sold

}
