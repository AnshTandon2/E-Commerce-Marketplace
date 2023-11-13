/**
 * Seller Class
 * <p>
 * Initializes a seller object and
 * manages their selling history and identification
 * information.
 *
 * @author Nirmal Senthilkumar; Ansh Tandon CS 180 Black
 * @version November 12, 2023
 */
public class Seller {
    private String name;
    private String email;
    private String password;

    public Seller(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void createProduct() {

    }

}
