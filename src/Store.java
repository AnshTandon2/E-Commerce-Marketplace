import java.util.HashMap;
import java.util.Map;

/**
 * Store Class
 * <p>
 * Initiates a new store for the marketplace
 * Owned and modifiable by a Seller user
 * Store has an id, name, products list, and a count of total sales
 *
 * @author Lalitha Chandolu, Nirmal Senthilkumar; CS 180 Black
 * @version November 12, 2023
 */

public class Store {

    private static int StoreIDCounter = 1;

    private double totalSales;
    private int quantitySold;
    private String name;
    private int StoreID;
    private HashMap<Integer, Integer> productsList;
    // Products list hash map structure <Product ID, Quantity in Store>

    private HashMap<String, HashMap<Integer, Integer>> customerHistories;
    // customer histories hash map structure <email, <Product ID, Quantity Bought>>

    public Store() {
        this.name = "";
        this.totalSales = 0;
        this.quantitySold = 0;
        this.productsList = new HashMap<Integer, Integer>();
        this.StoreID = StoreIDCounter;
        StoreIDCounter++;
    }

    public Store(String name, HashMap<Integer, Integer> productsList, double sales) {
        this.name = name;
        this.totalSales = sales;
        this.productsList = productsList;
        this.StoreID = StoreIDCounter;
        StoreIDCounter++;
        this.customerHistories = new HashMap<String, HashMap<Integer, Integer>>();
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public HashMap<Integer, Integer> getProductsList() {
        return productsList;
    }

    public void setProducts(HashMap<Integer, Integer> list) {
        this.productsList = list;
    }

    public void setProductsList(HashMap<Integer, Integer> productsList) {
        this.productsList = productsList;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String addProduct(Product product, Integer quantity) {
        if (this.productsList.containsKey(product.getProductID())) {
            int productQuantity = productsList.get(product.getProductID());
            // sets the product back in hash map
            // incrementing quantity available in the store by the amount specified
            this.productsList.put(product.getProductID(), productQuantity + quantity);
            return ("Quanity (" + quantity + ") of the " + product.getName() + " has been added.");
        } else {
            this.productsList.put(product.getProductID(), quantity);
            return ("This product has been added to your store. ");
        }
    }

    public String addProduct(Product product) {
        if (!this.productsList.containsKey(product.getProductID())) {
            this.productsList.put(product.getProductID(), 0);
            return ("This product has been added to your store, but there is no current stock. ");
        } else {
            return ("This product already exists in your store. ");
        }
    }

    public String removeProduct(Product product, int quantity) {
        if (this.productsList.containsKey(product.getProductID())) {
            // sets the product back in hash map
            // incrementing quantity available in the store by the amount specified
            int productQuantity = productsList.get(product.getProductID());
            if (productQuantity - quantity > 0) {
                this.productsList.put(product.getProductID(), productQuantity - quantity);
                return ("Quantity (" + quantity + ") of the " + product.getName() + " has been removed.");
            } else {
                return ("The quantity specified of " + product.getName() + " exceeds the quantity available in your store." +
                        "\n This quantity can't be removed");
            }
        }
        return ("This product is not in your store. ");
    }

    public String removeProduct(Product product) {
        if (this.productsList.containsKey(product.getProductID())) {
            this.productsList.remove(product.getProductID());
            return ("This product has been removed from your store. ");
        }
        return ("This product is not in your store. ");
    }

    public boolean hasProductInStock(Product product) {
        if (this.productsList.containsKey(product.getProductID())) {
            int quantityAvailable = productsList.get(product.getProductID());
            return quantityAvailable > 0;
        }
        return false;
    }

    public static boolean checkQuantityAvailable(Store store, Product product, int quantity) {
        if(store.productsList.containsKey(product.getProductID())) {
            // checks whether the given quantity exceeds the quantity
            // of the product that the store currently has
            if (quantity < store.productsList.get(product.getProductID())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean makeAPurchase(Product product, int quantity) {
        if (this.productsList.containsKey(product.getProductID())) {
            Integer productQuantity = productsList.get(product.getProductID());
            if (productQuantity - quantity > 0) {
                this.productsList.put(product.getProductID(), productQuantity - quantity);
                this.totalSales += quantity * product.getPrice();
                // purchase went through properly
                return true;
            } else {
                // the quantity demanded exceeds the quantity the store has
                return false;
            }
        } else {
            // this store does not sell that product
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("Store: " + getName() + "\n");
        for (Map.Entry<Integer, Integer> entry : getProductsList().entrySet()) {
            returnString.append(entry.getKey().toString()).append(entry.getValue());
        }
        returnString.append("\nTotalSales: ").append(getTotalSales()).append("\n");
        return returnString.toString();
    }

}
