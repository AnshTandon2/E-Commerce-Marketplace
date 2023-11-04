import java.util.*;
import java.time.*;

/**
 * Program Name
 * <p>
 * Brief description of program
 *
 * @author Ansh Tandon, CS 180 Black
 * @version Date of Completion
 */


public class User {
    String name;
    String email;
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
}