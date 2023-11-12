import java.util.*;
import java.io.*;

/**
 * Shopping Cart Class
 * <p>
 * Initiates a shopping cart object for every customer to view and modify
 * Uses HashMap to pair user's Product with the quantity requested for purchase
 *
 * @author Lalitha Chandolu; CS 180 Black
 * @version November 11, 2023
 */

public class ShoppingCart {
    // hash mapimplemented to have pair of Product (object), Quantity (int)
    private HashMap<Product, int> productsInCart;

    public ShoppingCart() {
        this.productsInCart = new HashMap<Product, int>();
    }

    public void addProduct(Product product, int quantity) {
        if (this.productsInCart.containsKey(product)) {
            // if the product is already in the customer's shopping cart
            // gets the current quantity of the product
            // specified in the hash map
            productQuantity = productsInCart.get(product);
            this.productsInCart.put(product, productQuantity + quantity);
        } else {
            this.productsInCart.put(product, quantity);
        }
    }

    public void removeProduct(Product product, int quantity) {
        if (this.productInCart.containsKey(product)) {
            productQuantity = productsInCart.get(product);
            this.productsInCart.put(product, productQuantity - quantity);
        }
    }

    public void clearShoppingCart() {
        this.productsInCart = this.productsInCart.clear();
    }

    public void viewShoppingCart() {
        System.out.println("Current Shopping Cart: ");
        for (Product p : productsInCart.keySet()) {
            String productInfo = String.format("Product: %s, Price %.2f, Quantity: %d",
                                p.getName(), p.getPrice(), productInCart.get(p));
            System.out.println(p);
        }
        System.out.println("------------------------------");
        System.out.println("Current Subtotal (no taxes): " + this.calculateCartTotal());
    }

    public double calculateCartTotal() {
        double total = 0;
        for (Product p : this.productsInCart.keySet()) {
            // adds the price of the product to the total
            // multiplies the price of the product by the quantity in the cart
            total += (p.getPrice() * productsInCart.get(p));
        }
        return total;
    }
}
