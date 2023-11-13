import java.util.*;
import java.io.*;

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

    /** List Products Method
     * Lists the products associated with the given Seller username
     * @param username
     * @return String (product names list)
     *
     * @author Lalitha Chandolu
     * @version November 13, 2023
     */
    public String listProducts(String username) {
        // methods lists all of the products sold by this Seller
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
            for (String[] productLine: marketplaceList) {
                //productLine[5] represents the username of the Seller associated with the product
                if (!productLine[5].equals(username)) {
                    marketplaceList.remove(productLine);
                }
            }

            String sellerProductList = "List of Products:\n";
            // Go through ArrayList and add the products that this seller owns to the string
            for (String[]productLine: marketplaceList) {
                if (productLine[5].equals(username)) {
                    sellerProductList += (String.join(",", productLine) + "\n");
                }
            }
            return sellerProductList;
        } catch (IOException e){
            return null;
        }
    }

    /** Remove Product
     * Sellers can remove products from the current product line
     * Given the productName and the Store name
     *
     * @param productName
     * @param storeName
     *
     * @author Lalitha Chandolu
     * @version November 13, 2023
     */
    public static String removeProduct(String productName, String storeName) {
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
            // Example line in market.txt file
            //Purdue Tote Bag;10.00;sandyStore;36;A nice tote bag;sandyruk
            for (String[] productInfo: marketplaceList) {
                if ((productInfo[0].equals(productName)) && (productInfo[2].equals(storeName))) {
                    marketplaceList.remove(productInfo);
                }
            }
            f.delete();
            f = new File("market.txt");
            // now write contents of ArrayList containing product info to ArrayList
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, false));
            for (String[] productInfo: marketplaceList) {
                String lineContents = String.join(";", productInfo);
                bfw.write(lineContents + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Product doesn't exist in this store.\n";
    }


    /** Add Product to Store
     * Seller can add a new product to their specified store
     * if the product doesn't currently exist
     *
     * @param productName
     * @param price
     * @param storeName
     * @param quantity
     * @param description
     * @param sellerUserName
     *
     * @author Lalitha Chandolu
     * @version November 13, 2023
     */
    public static String addProduct(String productName, double price, String storeName, int quantity,
                                    String description, String sellerUserName) {
        // Creates a new product and adds it to the market.txt file

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
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, true));
            boolean exists = false;
            for (String[] productInfo: marketplaceList) {
                if ((productInfo[0].equals(productName)) && (productInfo[2].equals(storeName))) {
                    exists = true;
                    return ("Can't add this product to the store, as it already exists.\n");
                }
            }
            if (!exists) {
                String newProduct = String.format("%s;%f;%s;%d;%s,%s\n", productName, price, storeName,
                                    quantity, description, sellerUserName);
                bfw.write(newProduct);
                return String.format("Product was added successfully to %s.\n", storeName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /** Edit Product in Store
     * Seller can add a new product to their existing store
     *
     * @param productName
     * @param storeName
     * @param changeField
     * @param newValue
     *
     * @author Lalitha Chandolu
     * @version November 13, 2023
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
            for (String[] productInfo: marketplaceList) {
                if ((productInfo[0].equals(productName)) && (productInfo[2].equals(storeName))) {
                    switch (changeField) {
                        case "product name" -> productInfo[0] = newValue;
                        case "price" -> productInfo[1] = newValue;
                        case "store" -> productInfo[2] = newValue;
                        case "quantity" -> productInfo[3] = newValue;
                        case "description" -> productInfo[4] = newValue;
                    }
                    return "Product was modified successfully.\n";

                }
            }
            // clear the existing file
            // remake the market.txt file
            f.delete();
            f = new File("market.txt");
            // now write contents of ArrayList containing product info to ArrayList
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, false));
            for (String[] productInfo: marketplaceList) {
                String lineContents = String.join(";", productInfo);
                bfw.write(lineContents + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Product doesn't exist in this store.\n";
    }


    /** Export Store Information
     * Allows a seller to export a csv file of the information of their store
     * CSV contains rows of products sold in the store
     * Each csv contains information for one of their unique stores
     *
     * @param merchantName
     * @param storeName
     *
     * @author Justin
     * @version November 13, 2023
     */
    public void exportStoreInformation(String merchantName, String storeName) {
        File readingFile = new File("market.txt");
        File exportFile = new File("exportFile.csv");
        try {
            exportFile.createNewFile();
            Scanner scan = new Scanner(readingFile);
            FileWriter fw = new FileWriter(exportFile);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");
                if (data[2].equals(storeName) && data[5].equals(merchantName)) {
                    fw.write("Store:" + data[2] + "Item:" + data[0] + "Price:" + data[1]);
                    fw.write("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Import Store Information
     * Takes a pathname from the seller and
     * @param pathname
     * @throws FileNotFoundException
     * @throws IOException
     *
     * @author Justin
     * @version November 13, 2023
     */
    public void importStoreInformation(String pathname) throws FileNotFoundException, IOException {
        // main method asks Seller for the file path as a command
        File f = new File(pathname);
        File writeToFile = new File("market.txt");
        if (!f.exists()) {
            // file doesn't exist
            // trying to import a non-existing file
            throw new FileNotFoundException();
        } else {
            // writes the contents of the file into market.txt file
            Scanner scan = new Scanner(f);
            FileWriter fw = new FileWriter(writeToFile);
            //Same format as the market.txt
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                fw.write(data);
                fw.write("\n");
                fw.flush();
            }
            fw.close();
        }
    }
}
