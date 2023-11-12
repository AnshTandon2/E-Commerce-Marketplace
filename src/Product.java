/**
 * Product class:
 * creates a new product object
 * with a name, price, and description associated with it
 *
 * @author Lalitha Chandolu, CS 180 Black
 * @version November 11, 2023
 */

public class Product {
    private static int ProductIDCounter;
    private String name;
    private double price;
    private String description;
    private int ProductID;

    // store associated with the product

    public Product(String name, double price, String productDescription) {
        this.name = name;
        this.price = price;
        this.description = productDescription;
        this.ProductID = ProductIDCounter;
        ProductIDCounter++;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
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

    public void setPrice(double price) {
        this.price = price;
    }

    public static int getProductIDCounter() {
        return ProductIDCounter;
    }

    public static void setProductIDCounter(int productIDCounter) {
        ProductIDCounter = productIDCounter;
    }

    public String getProductDescription() {
        return this.description;
    }

    public void setProductDescription(String desc) {
        this.description = desc;
    }

    public String toString() {
        return "Product<" + getName() + ";" + getPrice() + ";" + getProductDescription() + ">";
    }
}
