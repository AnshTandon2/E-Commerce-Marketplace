import java.util.*;

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

    private String name;

    private HashMap<Product, int> productsList;

    private ArrayList<Product> list;

    public Store(){
        this.name = "";
        this.totalSales = 0;
        this.productsList = new ArrayList<Product, int>();
        this.list = new ArrayList<Product>();
    }

    public Store(String name, HashMap<Product> productsList, double sales) {
        this.name = name;
        this.totalSales = sales;
        this.productsList = productsList;
        this.list = getStoreProducts();
    }

    public void getStoreProducts() {
        for (Product p: this.productList.keySet()) {
            this.list.add(p);
        }
    }

    public HashMap<Product, int> getProducts() {
        return this.productsList;
    }

    public void setProducts(HashMap<Product, int> list) {

        this.productsList = list;
    }

    public String addProduct(Product product, int quantity) {
        if (this.productsList.containsKey(product)) {
            productQuantity = productsList.get(product);
            // sets the product back in hash map
            // incrementing quantity available in the store by the amount specified
            this.productsInCart.put(product, productQuantity + quantity);
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
            productQuantity = productsList.get(product);
            if (productQuantity - quanity > 0)
                this.productsInCart.put(product, productQuantity - quantity);
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

    public boolean hasProductInStock(Product p) {
        if(this.productsList.containsKey(product)) {
            quantityAvailable = productsList.get(product);
            if (quantityAvailable > 0) {
                return true;
            }
        }
        return false;
    }


    public String makeASale(Product product, int quantity) {
        if (this.productsList.containsKey(product)) {
            productQuantity = productsList.get(product);
            // enough quanity available for sale
            if (productQuantity - quanity > 0) {
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
