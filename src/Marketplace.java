import java.util.*;
import java.io.*;

/**
 * Marketplace Class
 * <p>
 * Creates a marketplace class with the main functionality of getting store information.
 * @author Nirmal Senthilkumar CS 180 Black
 * @version November 11, 2023
 */
public class Marketplace {

    private static ArrayList<String> productIDs = new ArrayList<>();
    private static ArrayList<String> productNames = new ArrayList<>();
    private static ArrayList<Double> priceList = new ArrayList<>();
    private static ArrayList<String> storeNames = new ArrayList<>();
    private static ArrayList<Integer> quantityList = new ArrayList<>();
    private static ArrayList<String> descriptionList = new ArrayList<>();

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
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void printMarketplace() {
        for (int index = 0; index < productIDs.size(); index++) {
            System.out.print(getProductInfo(index));
            System.out.println("----------------------------------");
        }
    }

    public static ArrayList<String> searchProduct(String keyword) {
        ArrayList<String> matchedProducts = new ArrayList<String>();
        for(int index = 0; index < productIDs.size(); index++) {
            matchedProducts.add(getProductInfo(index));
        }
        return matchedProducts;
    }

    public static String getProductInfo(int index) {
        String info = "";
        info += String.format("%d;%s;%.2f;%s", index, productNames.get(index),
                priceList.get(index), storeNames.get(index));
        if (quantityList.get(index) == 0) {
            // there is no stock of the product available
            info += String.format("%s is out of stock.\n", productNames.get(index));
        } else {
            info += String.format("There is a quantity of %d of %s\n", quantityList.get(index), productNames.get(index));
        }
        return info;
    }

    public static String productDetail(int index) {
        // index is decremented because when user enter index 1
        // they mean the first line - which is index 0 in Java
        index -= 1;
        if (quantityList.get(index) > 0) {
            // the quantity available of the product is greather than 0
            // return the detailed description of the product along with the other info
            return String.format("\nProduct Name: %s" +
                                 "\nProduct Price: %.2f" +
                                 "\nStore: %s" +
                                 "\nQuantity: %d" +
                                 "\nDescription: %s", productNames.get(index), priceList.get(index),
                                                      storeNames.get(index), quantityList.get(index), descriptionList.get(index));


        } else {
            // there is no stock available of the product
            // return the product name, price, store, and quantity (specify it's out of stock)
            return String.format("\nProduct Name: %s" +
                                "\nProduct Price: %.2f" +
                                "\nStore: %s" +
                                "\nQuantity: Out of Stock", productNames.get(index), priceList.get(index),
                                                            storeNames.get(index), quantityList.get(index));
        }
    }

    /** Nirmal can do this as he did it with initial code
     * *
     * @param sortBy is "price" or "quantity"
     * @param orderMethod is "asc" or "desc"
     *
     */
    public static ArrayList<String> sortMarket(ArrayList<Integer> preRequisites, String sortBy, String orderMethod) {
        ArrayList<String> sortedProductIds = new ArrayList<>();

        if (sortBy.equalsIgnoreCase("price") && preRequisites == null) {
            ArrayList<Double> tempPrices = priceList;
            Collections.sort(tempPrices);
            if (orderMethod.equalsIgnoreCase("asc")) {
                for (int i = 0; i < tempPrices.size(); i++) {
                    for (int j = 0; j < tempPrices.size(); j++) {
                        if (tempPrices.get(i) == priceList.get(j)) {
                            sortedProductIds.add(Integer.toString(j));
                            //tempPrices.remove(i);
                        }
                    }
                        
                }
            } else {
                for (int i = tempPrices.size() - 1; i != 0; i--) {
                    for (int j = 0; j < tempPrices.size(); j++) {
                        if (tempPrices.get(i) == priceList.get(j)) {
                            sortedProductIds.add(Integer.toString(j));
                        }
                    }
                }
            }
        } else if (sortBy.equalsIgnoreCase("quantity") && preRequisites == null) {
            ArrayList<Integer> tempQuantity = quantityList;
            Collections.sort(tempQuantity);
            if (orderMethod.equalsIgnoreCase("asc")) {
                for (int i = 0; i < tempQuantity.size(); i++) {
                    for (int j = 0; j < tempQuantity.size(); j++) {
                        if (tempQuantity.get(i) == quantityList.get(j)) {
                            sortedProductIds.add(Integer.toString(j));
                        }
                    }
                }
            } else {
                for (int i = tempQuantity.size() - 1; i != 0; i--) {
                    for (int j = 0; j < tempQuantity.size(); j++) {
                        if (tempQuantity.get(i) == quantityList.get(j)) {
                            sortedProductIds.add(Integer.toString(j));
                        }
                    }
                }
            }
            // neither price or quantity is given
        } else if (sortBy.equalsIgnoreCase("price") && sortBy != null) {

        } else {

        }
        return sortedProductIds;
    }

    /**
     * Customer function
     */
    public static void addToCart() {

    }


    /**
     * Seller and Customer function
     */
    public static ArrayList<String> displayShoppingCart(String userName) {
        File f = new File("/data/shoppingCart.txt");
        ArrayList<String> shoppingCart = new ArrayList<>();
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");
                if (data[0].equals(userName)) {
                    for (int i = 3; i < data.length; i++) {
                        shoppingCart.add(data[i]);
                    }
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

    /** Seller Function */
    public static void removeProduct() {


    }
    /** Seller Function */
    public static void addProduct() {


    }

    /** Seller Function */
    public static void editProduct() {


    }

    /** Customer Function*/
    public static void buyShoppingCartItems() {

    }



}
