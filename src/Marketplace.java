import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Marketplace Class
 * <p>
 * Creates a marketplace class with the main functionality of getting store information.
 *
 * @author Nirmal Senthilkumar CS 180 Black
 * @version November 11, 2023
 */
public class Marketplace {
    /**
     * Arraylist for the productIDs
     */
    private static final ArrayList<String> productIDs = new ArrayList<>();
    /**
     * Arraylist for the productNames
     */
    private static final ArrayList<String> productNames = new ArrayList<>();
    /**
     * Arraylist for the productIDs
     */
    private static final ArrayList<Double> priceList = new ArrayList<>();
    /**
     * Arraylist for the priceList
     */
    private static final ArrayList<String> storeNames = new ArrayList<>();
    /**
     * Arraylist for the storeNames
     */
    private static final ArrayList<Integer> quantityList = new ArrayList<>();
    /**
     * Arraylist for the quantityList
     */
    private static final ArrayList<String> descriptionList = new ArrayList<>();
    /** Arraylist for the descriptionList*/

    /**
     * @author Justin, Lalitha, Nirmal
     * Reads the market.txt file and appends
     * Values to their corresponding ArrayLists;
     */
    public static void initializeMarketplace() {
        File file = new File("/data/market.txt");
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] data = s.nextLine().split(";");
                productIDs.add(data[0]);
                productNames.add(data[1]);
                priceList.add(Double.parseDouble(data[2]));
                storeNames.add(data[3]);
                quantityList.add(Integer.parseInt(data[4]));
                descriptionList.add(data[5]);
                // maybe will need to add a unique product id
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Justin, Lalitha, Nirmal
     * prints a list of the product information seperated by dashes
     */
    public static void printMarketplace() {
        for (int index = 0; index < productIDs.size(); index++) {
            System.out.print(getProductInfo(index));
            System.out.println("----------------------------------");
        }
    }

    /**
     * @param index the index of the product, mirrors the ID functionality
     * @return the String of the information for the product at the specified index
     * @author Justin, Lalitha, Nirmal
     * prints the product information including the index, names, prices, store associated, and the quantity status
     */
    public static String getProductInfo(int index) {
        String info = "";
        info += String.format("%d;%s;%.2f;%s", index, productNames.get(index), priceList.get(index),
                storeNames.get(index));
        if (quantityList.get(index) == 0) {
            // there is no stock of the product available
            info += String.format("%s is out of stock.\n", productNames.get(index));
        } else {
            info += String.format("There is a quantity of %d of %s\n", quantityList.get(index),
                    productNames.get(index));
        }
        return info;
    }

    /**
     * @param keyword keyword to search by
     * @return an arrayList of productInfo Strings of products that have the keyword in the name, store, or description
     * @author Nirmal, Lalitha, Justin
     */
    public static ArrayList<String> searchProduct(String keyword) {
        ArrayList<String> matchedProducts = new ArrayList<String>();
        for (int index = 0; index < productIDs.size(); index++) {
            if (productDetail(index).contains(keyword))
                matchedProducts.add(getProductInfo(index));
        }
        return matchedProducts;
    }

    /**
     * @param index the index of the product starting with 1, since users count productIndexes from one
     * @return the product name, price, store, and quantity (specify it's out of stock)
     * @author Lalitha
     * returns the Product details of :
     * Product Name:
     * Product Price:
     * Store:
     * Quantity:
     * Description:
     */
    public static String productDetail(int index) {
        // index is decremented because when user enter index 1
        // they mean the first line - which is index 0 in Java
        index -= 1;
        if (quantityList.get(index) > 0) {
            // the quantity available of the product is greather than 0
            // return the detailed description of the product along with the other info
            return String.format("\nProduct Name: %s" + "\nProduct Price: %.2f" + "\nStore: %s" + "\nQuantity: %d" +
                            "\nDescription: %s", productNames.get(index), priceList.get(index), storeNames.get(index),
                    quantityList.get(index), descriptionList.get(index));


        } else {
            // there is no stock available of the product
            // return the product name, price, store, and quantity (specify it's out of stock)
            return String.format("\nProduct Name: %s" + "\nProduct Price: %.2f" + "\nStore: %s" + "\nQuantity: Out of" +
                            " Stock", productNames.get(index), priceList.get(index), storeNames.get(index),
                    quantityList.get(index));
        }
    }

    /**
     * Sort Market - deprecated
     *
     * @param sortBy      is "price" or "quantity"
     * @param orderMethod is "asc" or "desc"
     */
    public static ArrayList<String> sortMarket(String sortBy, String orderMethod) {
        ArrayList<String> sortedProductIds = productIDs;

        if (sortBy.equalsIgnoreCase("price")) {
            //Collections.reverse(temp);
            if (orderMethod.equalsIgnoreCase("asc")) {
                sortedProductIds.sort(Comparator.comparing(o -> (priceList.get(productIDs.indexOf(o)))));
            } else if (orderMethod.equalsIgnoreCase("dsc")) {
                sortedProductIds.sort(Comparator.comparing(o -> (priceList.get(productIDs.indexOf(o)))));
                Collections.reverse(sortedProductIds);
            }
        } else if (sortBy.equalsIgnoreCase("quantity")) {
            if (orderMethod.equalsIgnoreCase("asc")) {
                sortedProductIds.sort(Comparator.comparing(o -> (quantityList.get(productIDs.indexOf(o)))));
            } else if (orderMethod.equals("dsc")) {
                sortedProductIds.sort(Comparator.comparing(o -> (quantityList.get(productIDs.indexOf(o)))));
                Collections.reverse(sortedProductIds);
            }
            // neither price or quantity is given
        }
        return sortedProductIds;
    }

    /**
     * Customer function
     */
    public static void addToCart() {

    }

    public static boolean removeFromCart(String productName, String userName) {
        File f = new File("/data/shoppingCart.txt");
        try {
            Scanner scan = new Scanner(f);
            FileWriter fw = new FileWriter(f);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @author Justin, Lalitha, Nirmal
     * gets an arrayList of Strings for the specified users shopping cart containing the productInfo of each product in
     * shoppingCart
     * @param userName the customer username to retrieve their shopping cart
     * @return productInfo String arrayList if the shopping cart exists, otherwise returns null
     */
    public static ArrayList<String> displayCart(String userName) {
        File f = new File("/data/shoppingCart.txt");
        ArrayList<String> shoppingCart = new ArrayList<>();
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");
                if (data[0].equals(userName)) {
                    shoppingCart.addAll(Arrays.asList(data).subList(3, data.length));
                }
            }
            ArrayList<String> returnList = new ArrayList<>();
            for (String s : shoppingCart) {
                returnList.add(getProductInfo(Integer.parseInt(s)));
            }
            return returnList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Shopping Cart Empty");
        }
        return null;
    }

    /**
     * Combine this code to displayCart()
     */
    public void printCart(String customerName) {
        try (BufferedReader bfr = new BufferedReader(new FileReader("shoppingcart.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                String[] product = line.split(",");
                if (product[0].equalsIgnoreCase(customerName)) {
                    System.out.printf("Product Name: %s\nProduct Price: %.2f\n" + "Quantity: %d\nTotal Cost: %" +
                                    ".2f\nStore: %s\n\n", product[1], Double.parseDouble(product[2]),
                            Integer.parseInt(product[4]),
                            Double.parseDouble(product[2]) * Integer.parseInt(product[4]), product[3]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
