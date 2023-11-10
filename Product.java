import java.util.*;

public class Product {

    public static int quantityAvail;
    // how many of the product are available across all stores

    public static int productIdCounter = 1;

    private boolean inStock;

    private String name;

    private double price;

    private int productId;

    private String productDescription;

    private ArrayList<Store> storeList;
    // list of stores that sell the product

    public Product(String name, double price, String productDescription,
                   int quantity, ArrayList<Store> stores) {
        this.name = name;
        this.productId = productIdCounter;
        productIdCounter++;
        this.price = price;
        this.productDescription = productDescription;
        quantityAvail = quantity;
        if (quantity > 0) {
            this.inStock = true;
        }
        this.storeList = stores;
    }

    public String getProductName() {
        return this.name;
    }

    public int getProductId() {
        return this.productId;
    }

    public double getPrice() {
        return this.price;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public boolean inStock() {
        return this.inStock;
    }

    public ArrayList<Store> getStores() {
        return this.storeList;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void setProductId(int id) {
        this.productId = id;
    }

    public void setPrice (double price) {
        this.price = price;
    }

    public void setProductDescription(String desc) {
        this.productDescription = desc;
    }

    public void addStore(Store store) {
        if (!this.storeList.contains(store)) {
            this.storeList.add(store);
        }
    }

    public void removeStore(Store store) {
        storeList.remove(store);
    }

}
