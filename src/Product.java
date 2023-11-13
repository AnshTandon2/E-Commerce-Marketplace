import java.io.*;
import java.util.ArrayList;

/**
 * Product class:
 * Creates a new product object
 * with a name, price, product ID, and description associated with it
 *
 * @author Lalitha Chandolu, Nirmal Senthilkumar; CS 180 Black
 * @version November 11, 2023
 */

public class Product {
    private static int ProductIDCounter = 1;
    private String name;
    private Double price;
    private String description;
    private int ProductID;
    private static ArrayList<Product> products;

    // store associated with the product

    public Product(String name, double price, String productDescription) {
        this.name = name;
        this.price = price;
        this.description = productDescription;
        this.ProductID = ProductIDCounter;
        ProductIDCounter++;
    }

    public Product(int productID, String name, double price, String productDescription) {
        this.name = name;
        this.price = price;
        this.description = productDescription;
        this.ProductID = productID;
    }

    public void populateProductList() {
        File productFile = new File("/data/Products.txt");
        try {
            FileReader fr = new FileReader(productFile);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            products = new ArrayList<>();
            Product.ProductIDCounter = Integer.parseInt(line);
            line = bfr.readLine();
            while (line != null) {
                String[] productDetail = line.substring(8, line.length() - 1).split(";");
                products.add(new Product(Integer.parseInt(productDetail[0]), productDetail[1],
                        Double.parseDouble(productDetail[2]), productDetail[3]));
                line = bfr.readLine();
            }
        } catch (IOException ignored) {
        }
    }

    public static void saveProduct(Product product) {
        if (products.contains(product))
            return;
        try {
            File productFile = new File("/data/products.txt");
            FileWriter fw = new FileWriter(productFile, false);
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write(product.toString());
        } catch (IOException ignored) {
        }
    }

    public static Product getProduct(int productID) {
        for (Product product : products) {
            if (product.getProductID() == productID)
                return product;
        }
        return null;
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

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // public static int getProductIDCounter() {
        //return ProductIDCounter;
    //}

    // public static void setProductIDCounter(int productIDCounter) {
        //ProductIDCounter = productIDCounter;
    //}

    public String toString() {
        return "Product<" + getProductID() + ";" + getName() + ";" + getPrice() + ";" + getProductDescription() + ">";
    }
}
