import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Seller Class
 * <p>
 * Seller class manages the seller's ability to add a product,
 * remove a product, or edit a product (any of its fields)
 *
 * @author Lalitha Chandolu, Nirmal Senthilkumar; Justin CS 180 Black
 * @version November 13, 2023
 */
public class Seller {

    /**
     * Remove Product
     * Sellers can remove products from the current product line
     * Given the productName and the Store name
     * Only if the product exists; if not, no change occurs
     *
     * @param productName name of product to be removed
     * @param storeName   name of store product to be removed from
     * @return String (whether product was removed)
     * @author Nirmal Senthilkumar
     * @author Lalitha Chandolu, Nirmal Senthilkumar
     */
    public static String removeProduct(String productName, String storeName) {
        // can edit product, price, store, quantity, or description
        // go through market.txt and add lines to String[] ArrayList
        String returnString = "Product doesn't exist in this store, cannot be removed.";
        // stores the products in the market.txt file
        ArrayList<String[]> marketplaceList = new ArrayList<>();
        File f = new File("market.txt");
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();
            while (line != null) {
                marketplaceList.add(line.split(";"));
                line = bfr.readLine();
            }
            bfr.close();

<<<<<<< Updated upstream
            // go through the ArrayList and if the product index matches then change that line
            // Example line in market.txt file
            //Purdue Tote Bag;10.00;sandyStore;36;A nice tote bag;sandyruk
            for (int i = 0; i < marketplaceList.size(); i++) {
                if ((marketplaceList.get(i)[0].equals(productName)) && (marketplaceList.get(i)[2].equals(storeName))) {
                    marketplaceList.remove(i);
                    returnString = "Product removed.";
                    break;
=======
                    HashMap<Integer, Integer> temp2 = new HashMap<>();
                    HashMap<String, HashMap<Integer, Integer>> temp3 = new HashMap<>();

                    for (int j = 0; j < merchandise.length; j++) {
                        temp.put(Integer.parseInt(merchandise[j]), Integer.parseInt(merchStock[j]));
                        temp2.put(Integer.parseInt(soldProduct[j]), Integer.parseInt(soldQuantity[j]));
                        temp3.put(soldCustomers[j], temp2);

                    }
                    this.stores.add(new Store(storeName, temp, 0, Integer.parseInt(storeID), temp3));
>>>>>>> Stashed changes
                }
            }
            f = new File("market.txt");
            // now write contents of ArrayList containing product info to ArrayList
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, false));
            for (String[] productList : marketplaceList) {
                String lineContents = String.join(";", productList);
                bfw.write(lineContents + "\n");
            }
            bfw.flush();
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    /**
     * Product Exists Method
     * Returns a boolean if the product exists in the specified Seller's store
     *
     * @param productName product name to be checked if it exists
     * @param storeName   store name to be checked if product exists in it
     * @return true if it exists, false if it doesn't
     * @author Nirmal Senthilkumar
     */
    public static boolean productExists(String productName, String storeName) {
        File f = new File("market.txt");
        ArrayList<String[]> marketplaceList = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            // reads the market.txt file
            String line = bfr.readLine();
            while (line != null) {
                // adds a new String[] list for each product in marketplace
                // split at each semi-colon
                marketplaceList.add(line.split(";"));
                line = bfr.readLine();
            }
            bfr.close();
            for (String[] productList : marketplaceList) {
                //System.out.println(productList[0] + " : " + productName + " and " + productList[2] + ":" + storeName);
                if ((productList[0].equals(productName)) && (productList[2].equals(storeName))) {
                    return true;
                }
            }
        } catch (IOException ignored) {
        }
        return false;
    }

    /**
     * Add Product to Store
     * Seller can add a new product to their specified store
     * if the product doesn't currently exist
     *
     * @param productName    productName field to be added
     * @param price          price field to be added
     * @param storeName      storeName field to be added
     * @param quantity       quantity field to be added
     * @param description    description field to be added
     * @param sellerUserName sellerUserName field to be added
     * @author Lalitha Chandolu, Nirmal Senthilkumar
     */
    public static String addProduct(String productName, double price, String storeName, int quantity,
                                    String description, String sellerUserName) {
        // Creates a new product and adds it to the market.txt file
        File f = new File("market.txt");
        ArrayList<String[]> marketplaceList = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            // reads the current marketplace file
            String line = bfr.readLine();
            while (line != null) {
                marketplaceList.add(line.split(";"));
                line = bfr.readLine();
            }
            bfr.close();
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, true));
            boolean exists = false;
            for (String[] productList : marketplaceList) {
                if ((productList[0].equals(productName)) && (productList[2].equals(storeName))) {
                    exists = true;
                    return ("Product already exists.");
                }
            }
            if (!exists) {
                // since the product doesn't exist - seller can add the product to their specified store
                //Purdue Tote Bag;10.00;sandyStore;36;A nice tote bag;sandyruk
                String newProduct = String.format("%s;%.2f;%s;%d;%s;%s\n", productName, price, storeName, quantity,
                        description, sellerUserName);
                // writes the new product to the file
                bfw.write(newProduct);
                bfw.flush();
                bfw.close();
                return String.format("Product was added successfully to %s.", storeName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ("Error in adding the product.");
    }

    /**
     * Edit Product in Store
     * Seller can add a new product to their existing store
     *
     * @param productName name of product to be updated
     * @param storeName   store of product to be updated
     * @param changeField can be the following values:
     *                    "name"
     *                    "price"
     *                    "store"
     *                    "quantity"
     *                    "description"
     * @param newValue    the value to update with
     * @author Lalitha Chandolu, Nirmal Senthilkumar
     */
    public static String editProduct(String productName, String storeName, String changeField, String newValue) {
        // can edit product, price, store, quantity, or description
        // go through market.txt and add lines to String[] ArrayList
        ArrayList<String[]> marketplaceList = new ArrayList<>();
        File f = new File("market.txt");
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();
            while (line != null) {
                marketplaceList.add(line.split(";"));
                line = bfr.readLine();
            }
            bfr.close();
            // go through the ArrayList and if the product index matches then change that line
            //Purdue Tote Bag;10.00;sandyStore;36;A nice tote bag;sandyruk
            for (int i = 0; i < marketplaceList.size(); i++) {
                if ((marketplaceList.get(i)[0].equals(productName)) && (marketplaceList.get(i)[2].equals(storeName))) {
                    if (changeField.equals("name")) {
                        Marketplace.productNames.add(i, newValue);
                        Marketplace.productNames.remove(i + 1);
                        return ("Product was modified successfully.");
                    } else if (changeField.equals("price")) {
                        Marketplace.priceList.add(i, Double.parseDouble(newValue));
                        Marketplace.priceList.remove(i + 1);
                        return ("Product was modified successfully.");
                    } else if (changeField.equals("store")) {
                        Marketplace.storeNames.add(i, newValue);
                        Marketplace.storeNames.remove(i + 1);
                        return ("Product was modified successfully.");
                    } else if (changeField.equals("quantity")) {
                        Marketplace.quantityList.add(i, Integer.parseInt(newValue));
                        Marketplace.quantityList.remove(i + 1);
                        return ("Product was modified successfully.");
                    } else if (changeField.equals("description")) {
                        Marketplace.descriptionList.add(i, newValue);
                        Marketplace.descriptionList.remove(i + 1);
                        return ("Product was modified successfully.");
                    }
                }
            }
            // clear the existing file
            // remake the market.txt file
            /*f = new File("market.txt");
            // now write contents of ArrayList containing product info to ArrayList
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, false));
            for (String[] productList : marketplaceList) {
                String lineContents = String.join(";", productList);
                bfw.write(lineContents + "\n");
            }
            bfw.flush();
            bfw.close();*/
            //return ("Product was modified successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ("Product doesn't exist in this store.");
    }

    /**
     * List Information By Store
     * Sellers can view a list of their sales by store, including customer information
     * and revenues from the sale.
     * <p>
     * STILL NEEDS TO BE IMPLEMENTED
     *
     * @param username (username of the seller)
     * @return String (Store Information)
     * @author Nirmal Senthilkumar
     */
    public static String ListPurchaseHistoryByStore(String username) {
        // methods lists all products sold by this Seller
        // go through market.txt and add lines to String[] ArrayList1
        ArrayList<String[]> storeList = new ArrayList<>();
        File f = new File("purchases.txt");
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();
            while (line != null) {
                String[] data = line.split(";");
                if (data[5].equals(username)) {
                    storeList.add(data);
                }
                line = bfr.readLine();
            }
            bfr.close();
            storeList.sort(Comparator.comparing(o -> ((o[2]))));
            if (storeList.isEmpty())
                return "No Recorded Sales";
            // Go through ArrayList and add the products that this seller owns to the string
            StringBuilder returnString = new StringBuilder("List of Stores and all transactions\n");
            String store = storeList.get(0)[2];
            returnString.append("\nStore: ").append(store).append("\n");
            double total = 0;
            for (String[] productLine : storeList) {
                if (!productLine[2].equals(store)) {
                    returnString.append(String.format("Store Total Revenue: $%.2f\n", total));
                    total = 0;
                    store = productLine[2];
                    returnString.append("\nStore: ").append(store).append("\n");
                }
                returnString.append(productLine[4]).append(" purchased ").append(productLine[3]).append(" ").
                        append(productLine[1]).append("(s) from ").append(productLine[2]).append(" for $").
                        append(productLine[1]).append(String.format(" for a " + "total of $%.2f.\n",
                                Double.parseDouble(productLine[3]) * Double.parseDouble(productLine[1])));
                total += Double.parseDouble(productLine[3]) * Double.parseDouble(productLine[1]);
            }
            returnString.append(String.format("Store Total Revenue: $%.2f\n", total));
            return returnString.toString();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Export Store Information
     * Allows a seller to export a csv file of the information of their store
     * CSV contains rows of products sold in the store
     * Each csv contains information for one of their unique stores
     *
     * @param merchantName
     * @param storeName
     * @author Justin, Lalitha
     */
    public static boolean exportStoreInformation(String merchantName, String storeName) {
        // reads the market.txt file
        File readingFile = new File("market.txt");
        // makes the new file to be exported
        File exportFile = new File("exportFile.csv");
        boolean exported = false;
        try {
            exportFile.createNewFile();
            Scanner scan = new Scanner(readingFile);
            FileWriter fw = new FileWriter(exportFile);
            // header for the csv file
            fw.write("Store,Item,Price\n");
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");
                //Book;60.00;claraStore;5;A nice book;clarank
                if (data[2].equals(storeName) && data[5].equals(merchantName)) {
                    fw.write(data[2] + "," + data[0] + "," + data[1]);
                    fw.write("\n");
                    exported = true;
                }
            }
            fw.flush();
        } catch (IOException e) {
        }
        return exported;
    }

    /**
     * Import Store Information
     * Takes a pathname from the seller and imports the store information
     * to the market.txt file
     *
     * @param pathname the pathname to import the store information from
     * @return boolean (true if import occured; false otherwise)
     * @author Justin
     */
    public static boolean importStoreInformation(String username, String pathname) {
        // main method asks Seller for the file path as a command
        File f = new File(pathname);
        boolean imported = false;
        // File should be in the format:
        // SellerUserName, StoreName, ProductName, Price, Quantity, Description

        try {
            File writeToFile = new File("market.txt");
            if (!f.exists()) {
                imported = false;
                // file doesn't exist
                // trying to import a non-existing file
            } else {
                // writes the contents of the file into market.txt file
                Scanner scan = new Scanner(f);
                FileWriter fw = new FileWriter(writeToFile, true);
                imported = true;
                //Same format as the market.txt
                while (scan.hasNextLine()) {
                    fw.write(scan.nextLine());
                    fw.write("\n");
                }
                fw.flush();
                fw.close();
            }
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return imported;
    }

    /**
     * View Store Statistics
     * Sellers can view a dashboard that lists statistics for each of their stores.
     * Data will include a list of customers with the number of items that they have purchased
     * Or a list of products with the number of sales made
     * Depending on the sortChoice that the sellers choose on their dashboard in their menu
     *
     * @param username   (seller username)
     * @param sortChoice (sort by which type)
     * @author Ansh Tandon, Lalitha Chandolu
     */
    public static String viewStoreStatistics(String username, int sortChoice) {
        // Example line in Purchases.txt
        //Purdue Tote Bag;18.00;davidStore;2;tandon39;davidkg
        File purchases = new File("purchases.txt");
        ArrayList<String> purchasesInSellerStores = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(purchases);
            while (fileReader.hasNextLine()) {
                // reads through all the lines in purchases.txt
                String initialData = fileReader.nextLine();
                String[] data = initialData.split(";");
                // store belongs to the specified user (Seller)
                if (data[5].equals(username)) {
                    purchasesInSellerStores.add(initialData);
                }
            }
            fileReader.close();
        } catch (IOException e) {
            // e.printStackTrace();
        }
        // Sort Types
        // 1. Sort by List of Customers
        // 2. Sort by Products Bought

        String storeStatistics = "";
        if (sortChoice == 1) { // sort by list of customers
            HashMap<String, Integer> customerPurchases = new HashMap<String, Integer>();
            for (int i = 0; i < purchasesInSellerStores.size(); i++) {
                String[] purchaseInfo = purchasesInSellerStores.get(i).split(";");
                customerPurchases.put(purchaseInfo[4], Integer.parseInt(purchaseInfo[3]));
            }
            /*for (String purchase : purchasesInSellerStores) {
                // Example of String Purchase:
                // Purdue Tote Bag;18.00;davidStore;2;tandon39;davidkg
                String[] purchaseInfo = purchase.split(";");
                if (customerPurchases.containsKey(purchaseInfo[4])) {
                    // finds the number of purchases that that Customer made
                    int quantity = customerPurchases.get(purchaseInfo[4]);
                    customerPurchases.put(purchaseInfo[4], quantity + Integer.parseInt(purchaseInfo[3]));
                } else {
                    // sets a new key, value pair for the customer
                    customerPurchases.put(purchaseInfo[4], Integer.parseInt(purchaseInfo[3]));
                }
            }*/
            storeStatistics += ("Sorted by List of Customers and their purchases:\n ");
            for (String customer : customerPurchases.keySet()) {
                storeStatistics += String.format("%s has purchased %d items from your stores.\n", customer,
                        customerPurchases.get(customer));
            }

<<<<<<< Updated upstream
        } else if (sortChoice == 2) { // sort by products sold
            HashMap<String, Integer> productsPurchased = new HashMap<String, Integer>();
            for (String store : purchasesInSellerStores) {
                HashMap<String, Integer> productsBought = new HashMap<String, Integer>();
                for (String purchase : purchasesInSellerStores) {
                    // Example of String Purchase:
                    // Purdue Tote Bag;18.00;davidStore;2;tandon39;davidkg
                    String[] purchaseInfo = purchase.split(";");
                    if (productsPurchased.containsKey(purchaseInfo[0])) {
                        int quantitySold = productsPurchased.get(purchaseInfo[0]);
                        productsPurchased.put(purchaseInfo[0], quantitySold + Integer.parseInt(purchaseInfo[3]));
                    } else {
                        productsPurchased.put(purchaseInfo[0], Integer.parseInt(purchaseInfo[3]));
=======
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String a : sales) {
            String[] arr = a.split(",");
            System.out.println("Product name: " + arr[0]);
            System.out.println("Quantity purchased: " + arr[1]);
            System.out.println("Customer name: " + arr[2]);
            System.out.println("");//get revenu
        }

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
        ArrayList<Product> allProducts = new ArrayList<>();
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

    public ArrayList<Store> getStores() {
        return this.stores;
    }

    public boolean verifyProductIsNew() {

    }

    public void updateStoreFile() {


    }


    public void editProduct() { // to be completed

    }

    public void deleteProduct() {

    }

    public File getFile() {

    }

    public void importProducts() {  //to be done

    }


    public void exportProducts() { //don't need to implement

    }

    public void viewDashboard() {
        System.out.println("Enter your option: " +
                "1. View customers sorted by number of items purchased" +
                "2. View products sorted by sales per year" +
                "3. Back");

    }

    public void viewDashbaord(String order) {

    }

    public void viewProductsInCart() {
        System.out.println("Carts that have your products: ");

    }

    public ArrayList<Product> getCart() {
        ArrayList<Product> arrayList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.getEmail() + ".txt"));
            //read through

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void importStoreFile(String pathName) {
        File f = new File(pathName);
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) { 
                //format: [StoreName],[NewProductName],[NewProductQuantity],[NewProductPrice],[NewProductDescription];
                String[] data = scan.nextLine().split(",");
                for (Store s : stores) {
                    if (data[0].equals(s.getName())) {
                        s.addProduct(new Product(data[1], Double.parseDouble(data[3]), data[4]));
>>>>>>> Stashed changes
                    }
                }
            }
            storeStatistics += ("Sorted by List of Products Purchased with number of Sales:\n ");
            for (String product : productsPurchased.keySet()) {
                storeStatistics += String.format("%s has been purchased a total of %d times from your stores.\n",
                        product, productsPurchased.get(product));
            }
        }
        return storeStatistics;
    }

} // end of Seller Class