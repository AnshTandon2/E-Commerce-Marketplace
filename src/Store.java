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

    private HashMap<Product, Integer> productsList;

    public Store() {
        this.name = "";
        this.totalSales = 0;
        this.productsList = new HashMap<Product, Integer>();
    }

    public Store(String name, HashMap<Product, Integer> productsList, double sales) {
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

    public HashMap<Product, Integer> getProductsList() {
        return productsList;
    }

    public void setProductsList(HashMap<Product, Integer> productsList) {
        this.productsList = productsList;
    }

    public HashMap<Product, Integer> getProducts() {
        return this.productsList;
    }

    public void setProducts(HashMap<Product, Integer> list) {
        this.productsList = list;
    }

    public String addProduct(Product product, int quantity) {
        if (this.productsList.containsKey(product)) {
            int productQuantity = productsList.get(product);
            // sets the product back in hash map
            // incrementing quantity available in the store by the amount specified
            this.productsList.put(product, productQuantity + quantity);
            return ("Quanity (" + quantity + ") of the " + product.getName() + " has been added.");
        } else {
            this.productsList.put(product, 1);
            return ("This product has been added to your store. ");
        }
    }

    public void addProduct(Product product) {
        if (!this.productsList.containsKey(product)) {
            this.productsList.put(product, 0);
        }
    }

    public String removeProduct(Product product, int quantity) {
        if (this.productsList.containsKey(product)) {
            // sets the product back in hash map
            // incrementing quantity available in the store by the amount specified
            int productQuantity = productsList.get(product);
            if (productQuantity - quantity > 0) {
                this.productsList.put(product, productQuantity - quantity);
                return ("Quanity (" + quantity + ") of the " + product.getName() + " has been removed.");
            } else {
                return ("The quantity specified of " + product.getName() + " exceeds the quantity available in your " +
                        "store. ");
            }
        }
        return ("This product is not in your store. ");
    }

    public void removeProduct(Product product) {
        this.productsList.remove(product);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasProductInStock(Product product) {
        if (this.productsList.containsKey(product)) {
            int quantityAvailable = productsList.get(product);
            return quantityAvailable > 0;
        }
        return false;
    }

    public static boolean checkQuantityAvailable(Store store, Product product, int quantity) {
        if(store.productsList.containsKey(product)) {
            // checks whether the given quantity exceeds the quantity
            // of the product that the store currently has
            if (quantity < store.productsList.get(product)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public String makeASale(Product product, int quantity) {
        if (this.productsList.containsKey(product)) {
            Integer productQuantity = productsList.get(product);
            // enough quantity available for sale
            if (productQuantity - quantity > 0) {
                this.productsList.put(product, productQuantity - quantity);
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
        for (Map.Entry<Product, Integer> entry : getProductsList().entrySet()) {
            returnString.append(entry.getKey().toString()).append(entry.getValue());
        }
        returnString.append("\nTotalSales: ").append(getTotalSales()).append("\n");
        return returnString.toString();
    }

    // add the functionality that sellers have on their customers and their sales invoices tmr (11/12)

}
