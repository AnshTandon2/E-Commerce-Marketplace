/**
 * Customer Class
 * <p>
 * Initializes a customer object and
 * manages their buying history and identification information
 * Customer info is saved in the following format:
 * First Line: email Name
 * Second Line to productLength+1 Line: Product.toString() + quantity;
 * productLength+2 Line: Shopping Cart:
 * ProductLength+3 - productLength+3+ShoppingCartLength: Product.toString() + quantity;
 *
 * @author Nirmal Senthilkumar, Ankita Majumdar, Lalitha Chandolu; CS 180 Black
 * @version November 11, 2023
 */
public class Customer {
    private String name;
    private String email;
    private String password;
}
