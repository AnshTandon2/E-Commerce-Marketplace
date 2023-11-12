import java.util.*;

public class Seller extends User{

    private ArrayList<Store> stores;
    // fields from parent code
    // email
    // name
    // password
    
    public Seller (String info, ArrayList<Store> storeList) {
        // call to super constructor has to be the first line
        super(info.split(",")[0], info.split(",")[1], info.split(",")[2]);
        // calls the parent constructor and sends the seller's name, email, and password
        try {
            if (this.stores.isEmpty()) {
                this.stores = new ArrayList<>();
            } else {
                this.stores = storeList;
            }
        } catch (NullPointerException e) {
        }
    }

    // be able to view total sales by their store
    // should have their own sales invoice per store
    // sellers should be able to sort sales per product
    // should be able to get a file list for each store which specified the
        // products currently there along with the quantities sold

}
