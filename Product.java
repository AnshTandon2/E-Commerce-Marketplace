import java.util.*;


/**
 * Product class:
 * creates a new product object
 * with a name, price, and description associated with it
 *
 * @author Lalitha Chandolu, CS 180 Black
 * @version November 11, 2023
 */

public class Product {

    private String name;

    private double price;

    private String description;

    // store associated with the product

    public Product(String name, double price, String productDescription) {
        this.name = name;
        this.price = price;
        this.productDescription = productDescription;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
    //public int getQuantity() {
        //return this.quantity;
    //}

    //public void setQuantity(int quantity) {
        //this.quantity = quantity;
    //}

    //public String getStore() {
        //return this.storeName;
    //}


    //public void addStore(Store store) {
        //if (!this.storeList.contains(store)) {
            //this.storeList.add(store);
        //}
    //}


    //public void removeStore(Store store) {

        //if (this.storeList.contains(store) {
            //storeList.remove(store);
        //}
    //}

}
