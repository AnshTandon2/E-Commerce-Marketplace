import java.util.*;
public class Store {

    private static int storeIdCounter = 1;

    private int storeId;

    private double totalSales;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void removeProduct(Product product) {
        productsList.remove(product);
    }

}
