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
    private File StoreHistory;
    private ArrayList<Store> stores;
    // fields from parent code
    // email
    // name
    // password

    public Seller(String info, ArrayList<Store> storeList) {
        // call to super constructor has to be the first line
        super(info.split(",")[0], info.split(",")[1], info.split(",")[2]);
        // calls the parent constructor and sends the seller's name, email, and password
        try {
            if (this.stores.isEmpty()) {
                this.stores = new ArrayList<>();
            } else {
                this.stores = storeList;
            }
        } catch (NullPointerException e) {
        }
    }

    public Seller(String name, String email, String password) {
        super(name, email, password);
        // the purchase history file of every user will follow this notation
        String fileNotation = (email + "-storeHistory.txt");
        StoreHistory = new File(fileNotation);
        if (!StoreHistory.exists()) {
            try {
                FileWriter fw = new FileWriter(StoreHistory, false);
                BufferedWriter bfw = new BufferedWriter(fw);
                bfw.write(String.format(this.getEmail() + ";" + this.getName() + "\n"));
            } catch (IOException ignored) {
            }
        } else {
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
                boolean productsNextLine = false;
                for (String s : list) {
                    String storeName = "";
                    HashMap<Product, Integer> productMap = new HashMap<>();
                    if (s.startsWith("Store: ")) {
                        storeName = s.substring(6);
                        productsNextLine = true;
                    }
                    if (productsNextLine) {
                        assert line != null;
                        String[] split = line.split("Product");
                        for (String product : split) {
                            String[] productAndQuantity = product.split("<");
                            String[] productDetail = productAndQuantity[1].split(";");
                            Product newProduct = new Product(productDetail[0], Double.parseDouble(productDetail[1]),
                                    productDetail[2]);
                            productMap.put(newProduct, Integer.parseInt(productAndQuantity[2]));
                        }
                        productsNextLine = false;
                    }
                    if (s.startsWith("TotalSales: ")) {
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
            String productInfo;
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
            int counter = 1;
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
