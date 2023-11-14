import java.io.*;
import java.util.*;
import java.util.Collections;
import java.util.Comparator;

/**
 * Seller Class
 * <p>
 * Seller class manages the seller's ability to add a product,
 * remove a product, or edit a product (any of it's fields)
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
     * @param storeName name of store product to be removed from
     * @author Lalitha Chandolu, Nirmal Senthilkumar
     */
    public static String removeProduct(String productName, String storeName) {
        // can edit product, price, store, quantity, or description
        // go through market.txt and add lines to String[] ArrayList
        String returnString = "Product doesn't exist in this store, cannot be removed.";
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
            // Example line in market.txt file
            //Purdue Tote Bag;10.00;sandyStore;36;A nice tote bag;sandyruk
            ArrayList<String[]> removeProductList = new ArrayList<>();
            for (String[] productInfo : marketplaceList) {
                if ((productInfo[0].equals(productName)) && (productInfo[2].equals(storeName))) {
                    removeProductList.add(productInfo);
                    returnString = "Product removed.";
                }
            }
            marketplaceList.removeAll(removeProductList);
            f = new File("market.txt");
            // now write contents of ArrayList containing product info to ArrayList
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, false));
            for (String[] productInfo : marketplaceList) {
                String lineContents = String.join(";", productInfo);
                bfw.write(lineContents + "\n");
            }
            bfw.flush();
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnString;
    }

    public static String[] getProduct(String productName, String storeName) {
        File f = new File("market.txt");
        ArrayList<String[]> marketplaceList = new ArrayList<>();
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();
            while (line != null) {
                marketplaceList.add(line.split(";"));
                line = bfr.readLine();
            }
            bfr.close();
            for (String[] productInfo : marketplaceList) {
                if ((productInfo[0].equals(productName)) && (productInfo[2].equals(storeName))) {
                    return productInfo;
                }
            }
        } catch (IOException ignored) {
        }
        return null;
    }

    /**
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
            for (String[] productInfo : marketplaceList) {
                System.out.println(productInfo[0] + " : " + productName + " and " + productInfo[2] + ":" + storeName);
                if ((productInfo[0].equals(productName)) && (productInfo[2].equals(storeName))) {
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
     * @param productName productName field to be added
     * @param price price field to be added
     * @param storeName storeName field to be added
     * @param quantity quantity field to be added
     * @param description description field to be added
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
            for (String[] productInfo : marketplaceList) {
                if ((productInfo[0].equals(productName)) && (productInfo[2].equals(storeName))) {
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
        return "Error in adding the product.";
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
            for (String[] productInfo : marketplaceList) {
                if ((productInfo[0].equals(productName)) && (productInfo[2].equals(storeName))) {
                    switch (changeField) {
                        case "name" -> productInfo[0] = newValue;
                        case "price" -> productInfo[1] = newValue;
                        case "store" -> productInfo[2] = newValue;
                        case "quantity" -> productInfo[3] = newValue;
                        case "description" -> productInfo[4] = newValue;
                    }
                }
            }
            // clear the existing file
            // remake the market.txt file
            f = new File("market.txt");
            // now write contents of ArrayList containing product info to ArrayList
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, false));
            for (String[] productInfo : marketplaceList) {
                String lineContents = String.join(";", productInfo);
                bfw.write(lineContents + "\n");
            }
            bfw.flush();
            bfw.close();
            return "Product was modified successfully.";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Product doesn't exist in this store.";
    }

    /**
     * List Products Method
     * Lists the products associated with the given Seller username
     *
     * @param  username (username of the seller)
     * @return String (product names list)
     * @author Lalitha Chandolu
     */
    public String listProducts(String username) {
        // methods lists all products sold by this Seller
        // go through market.txt and add lines to String[] ArrayList1
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
            // Remove all products from ArrayList that don't belong to this seller
            for (String[] productLine : marketplaceList) {
                //productLine[5] represents the username of the Seller associated with the product
                if (!productLine[5].equals(username)) {
                    marketplaceList.remove(productLine);
                }
            }

            String sellerProductList = "List of Products:\n";
            // Go through ArrayList and add the products that this seller owns to the string
            for (String[] productLine : marketplaceList) {
                if (productLine[5].equals(username)) {
                    sellerProductList += (String.join(",", productLine) + "\n");
                }
            }
            return sellerProductList;
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
     * @author Justin
     * @version November 13, 2023
     */
    public boolean exportStoreInformation(String merchantName, String storeName) {
        File readingFile = new File("market.txt");
        File exportFile = new File("exportFile.csv");
        boolean exported = false;
        try {
            exportFile.createNewFile();
            Scanner scan = new Scanner(readingFile);
            FileWriter fw = new FileWriter(exportFile);
            fw.write("Store,Item,Price");
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");
                //Book;60.00;claraStore;5;A nice book;clarank
                if (data[2].equals(storeName) && data[5].equals(merchantName)) {
                    fw.write( data[2] + "," + data[0] + "," + data[1]);
                    fw.write("\n");
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** Seller Function*/
    public static void viewStoreStatistics(String userName) {
        File purchases = new File("purchases.txt");
        ArrayList<String> storesBroughtFrom = new ArrayList<>();
        try {
            Scanner scan = new Scanner(purchases);
            while (scan.hasNextLine()) {
                String initialData = scan.nextLine();
                String[] data = initialData.split(";");
                if (data[4].equals(userName)) {
                    storesBroughtFrom.add(initialData);
                }
            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : storesBroughtFrom) {
            String[] recordedData = s.split(";");
            System.out.println("You brought " + recordedData[3] + " " + recordedData[0] + " from " + recordedData[2] + " for " + recordedData[1]);
        }

        Scanner userScan = new Scanner(System.in);

        System.out.println("1. Sort by amount spent\n2. Sort by items brought\n(Anything else. ) exit");
        String sortChoice = userScan.nextLine();
        if (sortChoice.equals("1")) {
            int counter = 0;
            int index = 0;
            for (int i = 0; i < storesBroughtFrom.size(); i++) {
                for (int j = 0; j < storesBroughtFrom.size(); j++) {
                    String[] data = storesBroughtFrom.get(j).split(";");
                    if (Double.parseDouble(data[1]) * Double.parseDouble(data[3]) > counter) {
                        index = j;
                    }
                }
                String[] recordedData = storesBroughtFrom.get(index).split(";");
                System.out.println("You brought " + recordedData[3] + " " + recordedData[0] + " from " + recordedData[2] + " for " + recordedData[1]);
                storesBroughtFrom.remove(index);
            }
        } else if (sortChoice.equals("2")) {
            int counter = 0;
            int index = 0;
            for (int i = 0; i < storesBroughtFrom.size(); i++) {
                for (int j = 0; j < storesBroughtFrom.size(); j++) {
                    String[] data = storesBroughtFrom.get(j).split(";");
                    if (Double.parseDouble(data[3]) > counter) {
                        index = j;
                    }
                }
                String[] recordedData = storesBroughtFrom.get(index).split(";");
                System.out.println("You brought " + recordedData[3] + " " + recordedData[0] + " from " + recordedData[2] + " for " + recordedData[1]);
                storesBroughtFrom.remove(index);
            }
        }
    }

    /**
     * Import Store Information
     * Takes a pathname from the seller and imports the store information
     * to the market.txt file
     *
     * @param pathname
     * @throws FileNotFoundException
     * @throws IOException
     * @return boolean (true if import occured; false otherwise)
     * @author Justin
     * @version November 13, 2023
     */
    public boolean importStoreInformation(String pathname) {
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
                FileWriter fw = new FileWriter(writeToFile);
                imported = true;
                //Same format as the market.txt
                while (scan.hasNextLine()) {
                    // takes each line in the file they gave
                    // writes it to the market.txt file
                    String data = scan.nextLine();
                    String[] tokens = data.split(",");
                    //Book;60.00;claraStore;5;A nice book;clarank
                    String newProductLine = tokens[2] + ";" + tokens[3] + ";" +
                            tokens[1] + ";" + tokens[4] + ";" +
                            tokens[5] + ";" + tokens[0];
                    fw.write(newProductLine);
                    fw.write("\n");
                    fw.flush();
                }
                fw.close();
            }
        } catch (IOException e) {
        }
        return imported;
    }
}
