import java.util.*;
import java.io.*;
public class ShoppingCart {
    private ArrayList<String> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addProduct(String product) {
        items.add(product);
    }

    public void removeProduct(String product) {
        items.remove(product);
    }

    public void viewShoppingCart() {
        for (String item : items) {
            System.out.println(item);
        }
    }
}