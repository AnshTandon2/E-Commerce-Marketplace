import java.sql.Timestamp;

/**
 * Order class
 * <p>
 * creates an order object
 *
 * @author Nirmal Senthilkumar, Ansh Tandon, CS 180 Black
 * @version updated the timestamp methodology used
 */
public class Order {
    private final User user;
    private final Product[] products;
    private final Timestamp timestamp;
    private double cost;

    public Order(User user, Product[] productList) {
        this.user = user;
        this.products = productList;
        timestamp = new Timestamp(java.lang.System.currentTimeMillis());
    }

    public User getUser() {
        return this.user;
    }

    public Product[] getItems() {
        return this.products;
    }

    public long getTimestampInMilli() {
        return timestamp.getTime();
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public double getCost() {
        return this.cost;
    }

    public void confirmOrder() {
        this.cost = this.calculateTotal();

    }

    public double calculateTotal() {
        double sum = 0.0;
        for (Product prod : this.products) {
            sum += prod.getPrice();
        }
        return sum;
    }
}
