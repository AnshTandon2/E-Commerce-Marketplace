import java.io.*;
import java.util.*;

/**
 * Customer Class
 * <p>
 * Initializes a customer object and
 * manages their buying history and identification information
 * Allows customers to add, remove, and purchase items in a cart
 *
 * @author Lalitha Chandolu, Nirmal Senthilkumar; CS 180 Black
 * @version November 11, 2023
 */
public class Customer {
    private String name;
    private String email;
    private String password;

    public Customer(String name, String email, String password) {
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

    public void viewCustomerMenu() {
        Scanner scan = new Scanner(System.in);

    }

    public static void addToCart(String productName, int quantity, String storeName, String client, String password) {

        File marketFile = new File("market.txt");
        String productStock = "";
        try {
            Scanner scan = new Scanner(marketFile);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");
                if (data[0].equals(productName) && data[2].equals(storeName)) {
                    productStock = data[3];
                    if (quantity > Integer.parseInt(productStock)) {
                        System.out.println("Cannot add to cart as quantity exceeds stock");
                    } else {
                        System.out.println("Added to Cart!");
                        File cartFile = new File("shoppingCart.txt");
                        Scanner cartFileScan = new Scanner(cartFile);
                        boolean exists = false;
                        while (cartFileScan.hasNextLine()) {
                            String initialCartData = cartFileScan.nextLine();
                            String[] cartData = initialCartData.split(";");
                            if (cartData[2].equals(productName) && cartData[4].equals(storeName)) {
                                String input = null;
                                Scanner sc = new Scanner(new File("shoppingCart.txt"));
                                StringBuffer sb = new StringBuffer();
                                while (sc.hasNextLine()) {
                                    input = sc.nextLine() + "\n";
                                    sb.append(input);
                                }
                                String finalStr = sb.toString();
                                String toReplace =
                                        cartData[0] + ";" + cartData[1] + ";" + cartData[2] + ";" + cartData[3] + ";" + cartData[4] + (Integer.parseInt(cartData[5]) + quantity);
                                finalStr = finalStr.replaceAll(initialCartData, toReplace);
                                PrintWriter writer = new PrintWriter(cartFile);
                                writer.append(finalStr);
                                writer.flush();
                                exists = true;
                            }
                        }
                        if (!exists) {
                            FileWriter writer = new FileWriter("shoppingCart.txt");
//                            writer.write(client + ";" + password + ";" + productName + ";" + price + ";" + storeName + ";" + quantity);
                            writer.write(client + ";" + password + ";" + productName + ";" + storeName + ";" + quantity);
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void removeFromCart(String customerUsername, String productName) {

        File f = new File("shoppingCart.txt");
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String initialData = scan.nextLine();
                String[] data = initialData.split(";");
                if (data[0].equals(customerUsername) && data[2].equals(productName)) {
                    String input = null;
                    Scanner sc = new Scanner(f);
                    StringBuffer sb = new StringBuffer();
                    while (sc.hasNextLine()) {
                        input = sc.nextLine() + "\n";
                        sb.append(input);
                    }
                    String finalStr = sb.toString();
                    finalStr = finalStr.replaceAll(initialData, "");
                    PrintWriter writer = new PrintWriter(f);
                    writer.append(finalStr);
                    writer.flush();
                    System.out.println("Removed!");
                } else {
                    System.out.println("Object doesn't exist in cart");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    /**Still being implemented*/
    public static void buyShoppingCartItems(String customerName) {
    /**
     * Still being implemented
     */
    public void buyShoppingCartItems(String customerName) {

        File f = new File("shoppingCart.txt");
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String initialData = scan.nextLine();
                String[] data = initialData.split(";");
                if (data[0].equals(customerName)) {
                    boolean flag = true;
                    File market = new File("market.txt");
                    Scanner marketScan = new Scanner(market);
                    String seller = "";
                    while (marketScan.hasNextLine()) {
                        String[] marketData = scan.nextLine().split(";");
                        if (Integer.parseInt(marketData[3]) < Integer.parseInt(data[5])) {
                            flag = false;
                            System.out.println("Not enough stock to buy");
                        } else {
                            seller = marketData[5];
                        }
                    }
                    if (flag) {
                        File purchases = new File("purchases.txt");
                        FileWriter fw = new FileWriter(purchases, true);
                        fw.write(data[2] + ";" + data[3] + ";" + data[4] + ";" + data[5] + ";" + data[0] + ";" + seller + "\n");
                        fw.flush();
                        fw.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
