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
    private final Item[] items;
    private final Timestamp timestamp;
    private double cost;

    public Order(User user, Item[] items) {
        this.user = user;
        this.items = items;
        timestamp = new Timestamp(java.lang.System.currentTimeMillis());
    }

    public User getUser() {
        return this.user;
    }

    public Item[] getItems() {
        return this.items;
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
        for (Item item : this.items) {
            sum += item.price;
        }
        return sum;
    }
}
