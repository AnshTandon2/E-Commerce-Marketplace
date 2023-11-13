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
     *
     * @param
     */
    public void removeProduct(ArrayList<String> productList, String productName) {


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
    public void addProduct(String productName, double price, String storeName, int quantity, String description) {
        // Creates a new product and adds it to the market.txt file

        File f = new File("market.txt");
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, true));

            //figure out how many lines the market.txt file already has
            // example format:
            //Purdue Tote Bag;10.00;sandyStore;36;A nice tote bag;sandyruk
            String newProduct = String.format("%s;%f;%s;%d;%s,%s\n", productName, price, storeName, quantity, description, username);
            bfw.write(newProduct);

        } catch (IOException e) {
            e.printStackTrace();
        }

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
    public void editProduct(String productName, String storeName, String changeField, String newValue) {
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
                }
            }

            // now write contents of ArrayList containing product info to ArrayList
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, false));
            for (String[] productInfo: marketplaceList) {
                String lineContents = String.join(";", productInfo);
                bfw.write(lineContents + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
