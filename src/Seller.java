import java.io.*;
import java.util.*;

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
     * @param storeName name of store product to be removed from
     * @return String (whether product was removed)
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

            // go through the ArrayList and if the product index matches then change that line
            // Example line in market.txt file
            //Purdue Tote Bag;10.00;sandyStore;36;A nice tote bag;sandyruk
            for (String[] productInfo : marketplaceList) {
                if ((productInfo[0].equals(productName)) && (productInfo[2].equals(storeName))) {
                    marketplaceList.remove(productInfo);
                    returnString = "Product removed.";
                }
            }
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
     * List Products By Store Method
     * List the sales for each store the Seller owns
     *
     * @param  username (username of the seller)
     * @return String (product names list)
     * @author Lalitha Chandolu
     */
    public static String listStoreHistory(String username) {
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
                returnString.append(productLine[4]).append(" purchased ").append(productLine[3]).append(" ").append(productLine[1]).append(
                        "(s) from ").append(productLine[2]).append(" for $").append(productLine[1]).append(String.format(" for a " +
                        "total of $%.2f.\n", Double.parseDouble(productLine[3]) * Double.parseDouble(productLine[1])));
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
     * @version November 13, 2023
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
                    fw.write( data[2] + "," + data[0] + "," + data[1]);
                    fw.write("\n");
                    exported = true;
                }
            }
        } catch (IOException e) {
        }
        return exported;
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
    public static boolean importStoreInformation(String pathname) {
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
                if (scan.hasNextLine()) {
                    // should delete header of csv
                    scan.nextLine();
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
                }
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
     * and a list of products with the number of sales.
     * Sellers can choose to sort the dashboard.
     * Will also display revenue per store
     *
     * @param username (seller username)
     * @param sortChoice (sort by which type)
     * @author Ansh, Lalitha
     */
    public static String viewStoreStatistics(String username, int sortChoice) {
        Map<String, Map<String, Integer>> storeStats = new HashMap<>();
        Map<String, Map<String, Integer>> productStatistics = new HashMap<>();
        File f = new File("purchases.txt");
        // Purdue Tote Bag;18.00;davidStore;2;tandon39;davidkg
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();
            while (line != null) {
                String[] data = line.split(",");
                String productName = data[0];
                double priceItem = Double.parseDouble(data[1]);
                String storeName = data[2];
                int quantityPurchased = Integer.parseInt(data[3]);
                String customerName = data[4];
                line = bfr.readLine();

                // Sort Types
                // 1. Sort by List of Customers
                // 2. Sort by Products Bought

                if (data[5].equals(username)) {
                    // If the store's statistics don't exist already, create them
                    Map<String, Integer> storeData = storeStats.computeIfAbsent(storeName, k -> new HashMap<>());
                    storeData.put(customerName, storeData.getOrDefault(customerName, 0) + 1);
                    Map<String, Integer> productStats = productStatistics.computeIfAbsent(storeName, k ->
                            new HashMap<>());
                    int updatedQuantity = productStats.getOrDefault(productName, 0) + quantityPurchased;
                    productStats.put(productName, updatedQuantity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder statisticBuilder = new StringBuilder();
        // For every store
        for (Map.Entry<String, Map<String, Integer>> entry : storeStats.entrySet()) {
            String storeName = entry.getKey();
            if (sortChoice == 1) {
                Map<String, Integer> storeStatistics = entry.getValue();
                statisticBuilder.append(storeName).append(":\n");
                // Print each customer and how many transactions they've made
                for (Map.Entry<String, Integer> customerEntry : storeStatistics.entrySet()) {
                    String customerName = customerEntry.getKey();
                    int transactionCount = customerEntry.getValue();
                    statisticBuilder.append(customerName).append(" has made ")
                            .append(transactionCount).append(" transaction(s).\n");
                }
            } else if (sortChoice == 2) {
                // Now, for every product sold in a store, print the quantity that has been sold
                if (productStatistics.containsKey(storeName)) {
                    for (Map.Entry<String, Integer> productEntry : productStatistics.get(storeName).entrySet()) {
                        String productName = productEntry.getKey();
                        int productSales = productEntry.getValue();
                        statisticBuilder.append(productName).append(" has had a total quantity of ").append(productSales)
                                .append(" sold.\n");
                    }
                }
            }
        }
        return statisticBuilder.toString();
    }

}
