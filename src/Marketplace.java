import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Marketplace Class
 * <p>
 * Creates a marketplace class with the main functionality of getting store information.
 *
 * @author Nirmal Senthilkumar CS 180 Black
 * @version November 11, 2023
 */
public class Marketplace {
    private static final ArrayList<String> productIDs = new ArrayList<>();
    private static final ArrayList<String> productNames = new ArrayList<>();
    private static final ArrayList<Double> priceList = new ArrayList<>();
    private static final ArrayList<String> storeNames = new ArrayList<>();
    private static final ArrayList<Integer> quantityList = new ArrayList<>();
    private static final ArrayList<String> descriptionList = new ArrayList<>();

    /**
     * Reads the market.txt file and appends
     * Values to their corresponding ArrayLists;
     */
    public static void initializeMarketplace() {
        File file = new File("market.txt");
        int counter = 0;
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] data = s.nextLine().split(";");
                productNames.add(data[0]);
                priceList.add(Double.parseDouble(data[1]));
                storeNames.add(data[2]);
                quantityList.add(Integer.parseInt(data[3]));
                descriptionList.add(data[4]);
                productIDs.add(Integer.toString(counter));
                counter++;
                // maybe will need to add a unique product id
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method to print the marketplace with all of the products
     */
    public static void printMarketplace() {
        for (int index = 0; index < productIDs.size(); index++) {
            System.out.print(productDetail(index));
            System.out.println("----------------------------------");
        }
    }

    /**
     * Get the product info for a product given the index of it in the file
     *
     * @param index the index of it in the file (from order)
     * @return the product info of a product
     */
    public static String getProductInfo(int index) {
        String info = "";
        info += String.format("%d ; %s ; %.2f ; %s ; ", index, productNames.get(index), priceList.get(index),
                storeNames.get(index));
        if (quantityList.get(index) == 0) {
            // there is no stock of the product available
            info += String.format("OUT OF STOCK\n", productNames.get(index));
        } else {
            info += String.format("Quantity: %d\n", quantityList.get(index));
        }
        return info;
    }

    public static ArrayList<String> searchProduct(String keyword) {
        ArrayList<String> matchedProducts = new ArrayList<String>();
        for (int i = 0; i < productIDs.size(); i++) {
            if (productNames.get(i).toLowerCase().contains(keyword.toLowerCase()) ||
                    storeNames.get(i).toLowerCase().contains(keyword.toLowerCase()) ||
                    descriptionList.get(i).toLowerCase().contains(keyword.toLowerCase())) {
                matchedProducts.add(productDetail(i));
            }
        }
        return matchedProducts;
    }


    public static String productDetail(int index) {
        // index is decremented because when user enter index 1
        // they mean the first line - which is index 0 in Java
        if (quantityList.get(index) > 0) {
            // the quantity available of the product is greather than 0
            // return the detailed description of the product along with the other info
            return String.format("Product Name: %s" + "\nProduct Price: %.2f" + "\nStore: %s" + "\nQuantity: %d" +
                            "\nDescription: %s\n", productNames.get(index), priceList.get(index), storeNames.get(index),
                    quantityList.get(index), descriptionList.get(index));


        } else {
            // there is no stock available of the product
            // return the product name, price, store, and quantity (specify it's out of stock)
            return String.format("Product Name: %s" + "\nProduct Price: %.2f" + "\nStore: %s" + "\nQuantity: Out of" +
                            " Stock\n", productNames.get(index), priceList.get(index), storeNames.get(index),
                    quantityList.get(index));
        }
    }

    /**
     * Nirmal can do this as he did it with initial code
     * *
     *
     * @param sortBy    is "price" or "quantity"
     * @param ASCENDING how to sort
     */
    public static ArrayList<String> sortMarket(String sortBy, boolean ASCENDING) {
        ArrayList<String> sortedProductIds = new ArrayList<>(productIDs);

        if (sortBy.equalsIgnoreCase("price")) {
            sortedProductIds.sort(Comparator.comparing(o -> (priceList.get(productIDs.indexOf(o)))));
            if (!ASCENDING) {
                Collections.reverse(sortedProductIds);
            }
        } else if (sortBy.equalsIgnoreCase("quantity")) {
            sortedProductIds.sort(Comparator.comparing(o -> (quantityList.get(productIDs.indexOf(o)))));
            if (!ASCENDING) {
                Collections.reverse(sortedProductIds);
            }
        }
        return sortedProductIds;
    }

    /**
     * method to remove from cart (unfinished)
     * @param productName product to be removed
     * @param userName customer's cart to remove from
     */
    /*public static void removeFromCart(String productName, String userName) {
        File f = new File("shoppingCart.txt");
        String toRemove = "";
        try {
            Scanner scan = new Scanner(f);
            FileWriter fw = new FileWriter(f);
            while (scan.hasNextLine()) {
                String initialData = scan.nextLine();
                String[] data = initialData.split(";");
                if (data[3].equals(productName) && data[0].equals(userName)) {
                    toRemove = initialData;
                }

            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!toRemove.equals("")) {
            String input = null;
            String fileContents = "";
            try {
                Scanner scan = new Scanner(f);
                StringBuffer sb = new StringBuffer();
                while (scan.hasNextLine()) {
                    input = scan.nextLine();
                    sb.append(input);
                }
                fileContents = sb.toString();
                fileContents = fileContents.replaceAll(toRemove + "\n", "");
                PrintWriter writer = new PrintWriter(f);
                writer.append(fileContents);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }*/

    /**
     * Display the shopping cart of the given customer
     * @param userName the customer's cart to retrieve
     * @return ArrayList of Strings of the product and the quantity seperated by " : "
     */
    public static ArrayList<String> displayCart(String userName) {
        File f = new File("shoppingCart.txt");
        ArrayList<String> shoppingCart = new ArrayList<>();
        ArrayList<String> quantities = new ArrayList<>();
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");
                if (data[0].equals(userName)) {
                    shoppingCart.addAll(Arrays.asList(data).subList(2, data.length));
                }
            }
            ArrayList<String> returnList = new ArrayList<>();
            for (int i = 0; i < shoppingCart.size() / 4; i++) {
                int nameIndex = productNames.indexOf(shoppingCart.get(4 * i));
                if (nameIndex != -1) {
                    returnList.add(shoppingCart.get(4 * i) + " : " + (shoppingCart.get(4 + 3)));
                } else {
                    returnList.add("invalid product, out of stock");
                }
            }
            return returnList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
