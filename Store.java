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

    private static int storeIdCounter = 1;

    private double totalSales;

    private int storeId;

    private String name;

    private ArrayList<Product> productsList;

    public Store(){
        this.storeId = storeIdCounter;
        storeIdCounter++;
        this.name = "";
        this.totalSales = 0;
        this.productsList = new ArrayList<Product>();
    }

    public Store(String name, ArrayList<Product> productsList, double sales) {
        this.storeId = storeIdCounter;
        storeIdCounter++;
        this.name = name;
        this.totalSales = sales;
        this.productsList = productsList;
    }

    public int getStoreId() {

        return this.storeId;
    }

    public ArrayList<Product> getProducts() {

        return this.productsList;
    }

    public void setStoreId(int storeId) {

        this.storeId = storeId;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void setProductList(ArrayList<Product> list) {

        this.productsList = list;
    }

    public void addProduct(Product product) {
        if (!productsList.contains(product)) {
            productsList.add(product);
        }
    }

    public void removeProduct(Product product) {
        productsList.remove(product);
    }

}
