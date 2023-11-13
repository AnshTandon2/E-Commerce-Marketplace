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
     */
    public void removeProduct(ArrayList<String> productList, String productName) {


    }



    /** Seller Function */
    /** Still implementing*/
    public void addProduct() {
        // Creates a new product and adds it to the market.txt file.
        //Product newProduct = new Product(name, price);
        //newProduct.setQuantity(quantity);
        //newProduct.setDescription(description);

        File f = new File("market.txt");
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, true));

            //figure out how many lines the market.txt file already has in order to figure out index of product
            int lineNumber = 0;
            while (bfr.readLine() != null) {
                lineNumber ++;
            }
            bfr.close();

            // Create product id
            String productID = productIDGenerator(name,storeName,username,price);

            String newProduct = String.format("%d,%s,%f,%s,%d,%s,%s,%s\n", lineNumber + 1, name, price, storeName, quantity, description, username, productID);

            bfw.write(newProduct);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /** Seller Function */
    // can edit product, price, store, quantity, or description
    // parameters: product index, field to change, value to change it to
    public void editProduct(int productIndex, String changeField, String newValue) {
        // go through market.txt and add lines to String[] ArrayList
        ArrayList<String[]> oldMarketPlace = new ArrayList<>();
        File f = new File("market.txt");
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(f));
            String line = bfr.readLine();
            while (line != null) {
                oldMarketPlace.add(line.split(","));
                line = bfr.readLine();
            }
            bfr.close();

            // go through the ArrayList and if the product index matches then change that line
            for (String[] productInfo: oldMarketPlace) {
                if (Integer.parseInt(productInfo[0]) == productIndex) {
                    switch (changeField) {
                        case "product" -> productInfo[1] = newValue;
                        case "price" -> productInfo[2] = newValue;
                        case "store" -> productInfo[3] = newValue;
                        case "quantity" -> productInfo[4] = newValue;
                        case "description" -> productInfo[5] = newValue;
                    }
                    // Create new product id
                    productInfo[7] = productIDGenerator(productInfo[1], productInfo[3], username, Double.parseDouble(productInfo[2]));
                }
            }

            // now write contents of ArrayList containing product info to ArrayList
            BufferedWriter bfw = new BufferedWriter(new FileWriter(f, false));
            for (String[] productInfo: oldMarketPlace) {
                String lineContents = String.join(",", productInfo);
                bfw.write(lineContents + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
