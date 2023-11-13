import java.util.*;
import java.io.*;

/**
 * Seller Class
 * <p>
 * Initializes a seller object and
 * manages their selling history and identification
 * information.
 *
 * @author Lalitha Chandolu, Nirmal Senthilkumar; Justin CS 180 Black
 * @version November 13, 2023
 */
public class Seller {
    private String username;

    public Seller(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

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
        File f = new File("data/market.txt");
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
     *
     * @param
     */
    public void removeProduct(String productName, String customerUsername) {
        StringBuilder productList = new StringBuilder();
        try (BufferedReader bfr = new BufferedReader(new FileReader("data/market.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                String []product = line.split(";");
                if (product[0].equalsIgnoreCase(customerUsername)) {
                    // nothing happens then
                } else {
                    productList.append(line).append("\n");
                }
            }
            bfr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/market.txt"))) {
            writer.write(productList.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /** Add Product to Store
     * Seller can add a new product to their existing store
     *
     * @param productName
     * @param price
     * @param storeName
     * @param quantity
     * @param description
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
     */
    public static String editProduct(String productName, String storeName, String changeField, String newValue) {
        // can edit product, price, store, quantity, or description
        // go through market.txt and add lines to String[] ArrayList
        ArrayList<String[]> marketplaceList = new ArrayList<>();
        File f = new File("data/market.txt");
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
            f = new File("data/market.txt");
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

    public void exportStoreInformation(String merchantName, String storeName) {
        File readingFile = new File("data/market.txt");
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

    public void importStoreInformation(String pathname) throws FileNotFoundException, IOException {
        File f = new File(pathname);
        File writeToFile = new File("data/market.txt");
        if (!f.exists()) {
            throw new FileNotFoundException();
        } else {
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
