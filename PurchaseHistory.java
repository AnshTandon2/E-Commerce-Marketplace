import java.util.*;
import java.io.*;
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