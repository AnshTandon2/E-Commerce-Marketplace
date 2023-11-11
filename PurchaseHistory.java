import java.util.*;
import java.io.*;
/**
 * Purchase History Class
 * <p>
 * Initializes an ArrayList of 
 * buying history for customer
 * and edits it.
 *
 * @author Ankita Majumdar; CS 180 Black
 * @version November 11, 2023
 */

public class PurchaseHistory {
    private ArrayList<String> history;

    public PurchaseHistory() {
        this.history = new ArrayList<>();
    }

    public void addPurchase(String purchase) {
        history.add(purchase);
    }

    public void exportHistory() {
        for (String purchase : history) {
            System.out.println(purchase);
        }
    }
}
