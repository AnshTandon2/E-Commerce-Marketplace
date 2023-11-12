import java.util.ArrayList;
import java.util.HashMap;

/**
 * Store Class
 * <p>
 * Initiates a new store for the marketplace
 * Store has an id, name, products list, and a count of total sales
 *
 * @author Lalitha Chandolu; CS 180 Black
 * @version November 11, 2023
 */

public class Store {

    private double totalSales;

    private int quantitySold;

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

    private String name;

    private HashMap<Product, Integer> productsList;

    private ArrayList<Product> list;

    public Store(){
        this.name = "";
        this.totalSales = 0;
        this.productsList = new HashMap<Product, Integer>();
        this.list = new ArrayList<Product>();
    }

    public Store(String name, HashMap<Product, Integer> productsList, double sales) {
        this.name = name;
        this.totalSales = sales;
        this.productsList = productsList;
        getStoreProducts();
    }

    public void getStoreProducts() {
        for (Product p: this.productsList.keySet()) {
            this.list.add(p);
        }
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
            this.list.add(product);
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
                return ("The quantity specified of " + product.getName() + " exceeds the quantity available in your store. ");
            }
        }
        return ("This product is not in your store. ");
    }

    public void removeProduct(Product product) {
        if (this.productsList.containsKey(product)) {
            this.productsList.remove(product);
            this.list.remove(product);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasProductInStock(Product product) {
        if(this.productsList.containsKey(product)) {
            int quantityAvailable = productsList.get(product);
            return quantityAvailable > 0;
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
            } else {
                return ("Can't make this sale because quantity specified exceeds quantity available. ");
            }
        } else {
            return ("This product is currently unavailable in this store");
        }
    }

    // add the functionality that sellers have on their customers and their sales invoices tmr (11/12)

}
