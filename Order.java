import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Program Name
 * <p>
 * Brief description of program
 *
 * @author Ansh Tandon, CS 180 Black
 * @version Date of Completion
 */
public class Order {
    User user;
    Item[] items;

    LocalDateTime startTime;
    LocalDateTime endTime;

    double cost;

    public Order(User user, Item[] items, LocalDateTime startTime, LocalDateTime endTime) {
        this.user = user;
        this.items = items;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public User getUser() { return this.user; }
    public Item[] getItems() { return this.items; }
    public LocalDateTime getStartTime() { return this.startTime; }
    public LocalDateTime getEndTime() { return this.endTime; }
    public double getCost() { return this.cost; }

    public void confirmOrder() {
        this.cost = this.calculateTotal();

    }
    public double calculateTotal() {
        double sum=0.0;
        for(Item item : this.items){
            sum+=item.price;
        }
        return sum;
    }
}