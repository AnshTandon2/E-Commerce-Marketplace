import java.util.HashMap;
import java.util.Map;

/**
 * Store Class
 * <p>
 * Initiates a new store for the marketplace
 * Store has an id, name, products list, and a count of total sales
 *
 * @author Lalitha Chandolu, Nirmal Senthilkumar; CS 180 Black
 * @version November 12, 2023
 */

public class Store {
    private double totalSales;
    private int quantitySold;
    private String name;

    private HashMap<Integer, Integer> productsList;

    public Store() {
        this.name = "";
        this.totalSales = 0;
        this.productsList = new HashMap<Integer, Integer>();
    }

    public Store(String name, HashMap<Integer, Integer> productsList, double sales) {
        this.name = name;
        this.totalSales = sales;
        this.productsList = productsList;
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

    public void setProductsList(HashMap<Integer, Integer> productsList) {
        this.productsList = productsList;
    }

    public HashMap<Integer, Integer> getProducts() {
        return this.productsList;
    }

    public void setProducts(HashMap<Integer, Integer> list) {
        this.productsList = list;
    }

    public String addProduct(Product product, int quantity) {
        if (this.productsList.containsKey(product.getProductID())) {
            int productQuantity = productsList.get(product.getProductID());
            // sets the product back in hash map
            // incrementing quantity available in the store by the amount specified
            this.productsList.put(product.getProductID(), productQuantity + quantity);
            return ("Quanity (" + quantity + ") of the " + product.getName() + " has been added.");
        } else {
            this.productsList.put(product.getProductID(), 1);
            return ("This product has been added to your store. ");
        }
    }

    public void addProduct(Product product) {
        if (!this.productsList.containsKey(product)) {
            this.productsList.put(product.getProductID(), 0);
        }
    }

    public String removeProduct(Product product, int quantity) {
        if (this.productsList.containsKey(product.getProductID())) {
            // sets the product back in hash map
            // incrementing quantity available in the store by the amount specified
            int productQuantity = productsList.get(product.getProductID());
            if (productQuantity - quantity > 0) {
                this.productsList.put(product.getProductID(), productQuantity - quantity);
                return ("Quanity (" + quantity + ") of the " + product.getName() + " has been removed.");
            } else {
                return ("The quantity specified of " + product.getName() + " exceeds the quantity available in your " +
                        "store. ");
            }
        }
        return ("This product is not in your store. ");
    }

    public void removeProduct(Product product) {
        this.productsList.remove(product.getProductID());
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasProductInStock(Product product) {
        if (this.productsList.containsKey(product.getProductID())) {
            int quantityAvailable = productsList.get(product.getProductID());
            return quantityAvailable > 0;
        }
        return false;
    }

    public static boolean checkQuantityAvailable(Store store, int productID, int quantity) {
        if (store.productsList.containsKey(productID)) {
            // checks whether the given quantity exceeds the quantity
            // of the product that the store currently has
            return quantity < store.productsList.get(productID);
        }
        return false;
    }

    public String makeASale(Product product, int quantity) {
        if (this.productsList.containsKey(product.getProductID())) {
            Integer productQuantity = productsList.get(product.getProductID());
            // enough quantity available for sale
            if (productQuantity - quantity > 0) {
                this.productsList.put(product.getProductID(), productQuantity - quantity);
                this.totalSales += quantity * product.getPrice();
                return ("Sale Made:\nProduct:" + product.getName() + "\nQuantity: " + quantity);
            } else {
                return ("Can't make this sale because quantity specified exceeds quantity available. ");
            }
        } else {
            return ("This product is currently unavailable in this store");
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

    // add the functionality that sellers have on their customers and their sales invoices tmr (11/12)

}
