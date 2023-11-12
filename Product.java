import java.util.*;


/**
 * Product class:
 * creates a new product object
 *
 * @author Lalitha Chandolu, CS 180 Black
 * @version November 11, 2023
 */

public class Product {

    public static int productIdCounter = 1;

    private String name;

    private double price;

    private int productId;

    private String productDescription;

    private ArrayList<Store> storeList;
    // list of stores that sell the product

    public Product(String name, double price, String productDescription, ArrayList<Store> stores) {
        this.name = name;
        this.productId = productIdCounter;
        this.price = price;
        this.productDescription = productDescription;
        productIdCounter++;

        this.storeList = stores;
    }

    public Product(String name, double price, String productDescription) {
        this.name = name;
        this.productId = productIdCounter;
        this.price = price;
        this.productDescription = productDescription;
        productIdCounter++;

        this.storeList = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductId() {
        return this.productId;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public void setProductDescription(String desc) {
        this.productDescription = desc;
    }

    //public int getQuantityAvail() {
        //return this.quantityAvailTotal;
    //}

    //public void setQuantityAvail(int quantity) {
        //this.quantityAvailTotal = quantity;
    //}

    //public boolean inStock() {
        return this.inStock;
    }

    public ArrayList<Store> getStores() {
        return this.storeList;
    }

    public void addStore(Store store) {
        if (!this.storeList.contains(store)) {
            this.storeList.add(store);
        }
    }

    public void removeStore(Store store) {

        if (this.storeList.contains(store) {
            storeList.remove(store);
        }
    }

}
