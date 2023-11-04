/**
 * Program Name
 * <p>
 * Brief description of program
 *
 * @author Ansh Tandon, CS 180 Black
 * @version Date of Completion
 */
public class Item {
    int id;
    double price;

    public Item(int id, double price) {
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public double getPrice() {
        return this.price;
    }
}
