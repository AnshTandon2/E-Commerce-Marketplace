import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

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

    /**
     * Still being implemented
     */
    public static boolean buyItem(String customerName, String productId, int quantity) throws IOException {
        FileReader fr = new FileReader("market.txt");
        BufferedReader bfr = new BufferedReader(fr);
        String line = bfr.readLine();
        StringBuilder content = new StringBuilder();
        boolean stringChanged = false;
        while (line != null) {
            String[] market = line.split(",");
            if (market[6].equals(productId)) {
                if (Integer.parseInt(market[4]) < quantity) {
                    System.out.println("Quantity is higher than stock.");
                } else {
                    int remainder = Integer.parseInt(market[4]) - quantity;
                    String x = Integer.toString(remainder);
                    String newLineContent = "";
                    for (int i = 0; i < market.length; i++) {
                        if (i != 4) {
                            newLineContent = market[i] + ",";
                        } else {
                            newLineContent = x + ",";
                        }

                    }
                    newLineContent = newLineContent.substring(0, newLineContent.length() - 1);
                    content.append(newLineContent).append("\n");
                    stringChanged = true;

                    String productsBought =
                            market[1] + "," + market[2] + "," + market[3] + "," + quantity + "," + customerName + "," + market[6];
                    PrintWriter writer2 = new PrintWriter(new FileWriter("purchases.txt"));
                    writer2.println(productsBought);
                    writer2.close();
                }
            } else {
                content.append(line).append("\n");
            }
            line = bfr.readLine();
        }
        bfr.close();
        PrintWriter writer = new PrintWriter(new FileWriter("market.txt"));
        writer.print(content);
        writer.close();
        return stringChanged;
    }

    public static void exportPurchaseHistory(String userName) {
        File exportFile = new File("purchaseHistoryExport.csv");
        File readingFile = new File("purchases.txt");
        try {
            exportFile.createNewFile();
            Scanner scan = new Scanner(readingFile);
            FileWriter fw = new FileWriter(exportFile);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");
                if (data[4].equals(userName)) {
                    fw.write("Purchased:" + data[0] + ",Price:" + data[1] + ",FromSeller:" + data[5] + "Count:" + data[3]);
                    fw.write("\n");
                }

            }

            scan.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param userName  the username of the customer
     * @param sortBy    the value to sort by, valid values are "price" (the amount of total money) or "quantity"
     * @param ASCENDING choose to sort the values by sortBy in ascending or descending order
     * @return a formmated String that has the list of products sorted by the specified sortBy and ASCENDING
     * parameters, returns "Your purchase history is empty" if there is no purchase history
     * @author Nirmal, Justin
     * views shopping history and sorts by either the total amount of each purchase, or the quantity of each product
     * purchased in that sale
     */
    public static String viewShoppingHistory(String userName, String sortBy, boolean ASCENDING) {
        StringBuilder returnString = new StringBuilder();
        File purchases = new File("purchases.txt");
        ArrayList<String> storesBoughtFrom = new ArrayList<>();
        try {
            Scanner scan = new Scanner(purchases);
            while (scan.hasNextLine()) {
                String initialData = scan.nextLine();
                String[] data = initialData.split(";");
                if (data[4].equals(userName)) {
                    storesBoughtFrom.add(initialData);
                }
            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (storesBoughtFrom.isEmpty()) {
            returnString.append("Your purchase history is empty.");
            return returnString.toString();
        }
        if (sortBy.equalsIgnoreCase("price")) { //the amount spent bt the customer
            storesBoughtFrom.sort(Comparator.comparing(o -> ((Double.parseDouble(o.split(";")[1]) * Double.parseDouble(o.split(";")[3])))));
        } else if (sortBy.equalsIgnoreCase("quantity")) { //the amount of products the customer bought
            storesBoughtFrom.sort(Comparator.comparing(o -> ((Double.parseDouble(o.split(";")[3])))));
        }
        if (!ASCENDING) {
            Collections.reverse(storesBoughtFrom);
        }
        for (String product : storesBoughtFrom) {
            String[] data = product.split(";");
            returnString.append("You purchased ").append(data[3]).append(" ").append(data[0]).append(" from ").append(data[2]).append(" for ").append(data[1]).append(String.format(" for a total of %.2f .\n", Double.parseDouble(data[3]) * Double.parseDouble(data[1])));
        }
        return returnString.toString();
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void viewCustomerMenu() {
        Scanner scan = new Scanner(System.in);

    }

    public static boolean addToCart(String productName, String storeName, int quantity, String client) {
        File marketFile = new File("market.txt");
        String productStock = "";
        try {
            Scanner scan = new Scanner(marketFile);
            String[] productToAdd = null;
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");
                if (data[0].equals(productName) && data[2].equals(storeName)) {
                    productToAdd = data;
                }
            }
            if (productToAdd == null)
                return false;
            if (quantity > Integer.parseInt(productToAdd[3]))
                return false;
            scan.close();
            File cartFile = new File("shoppingCart.txt");
            scan = new Scanner(cartFile);
            boolean exists = false;
            ArrayList<String[]> shoppingCartOld = new ArrayList<>();
            while (scan.hasNextLine()) {
                String initialCartData = scan.nextLine();
                String[] cartData = initialCartData.split(";");
                shoppingCartOld.add(cartData);
            }
            ArrayList<String[]> shoppingCartNew = new ArrayList<>();

            for (String[] product : shoppingCartOld) {
                if (product[2].equals(productName) && product[4].equals(storeName)) {
                    product[5] += quantity;
                    exists = true;
                }
                shoppingCartNew.add(product);
            }
            if (!exists) {
                String[] newProduct = new String[8];
                newProduct[0] = client;
                newProduct[1] = Customer.getPassword(client);
                newProduct[2] = productToAdd[0];
                newProduct[3] = productToAdd[1];
                newProduct[4] = productToAdd[2];
                newProduct[5] = productToAdd[3];
                newProduct[6] = productToAdd[4];
                newProduct[7] = productToAdd[5];
                shoppingCartNew.add(newProduct);
            }
            FileWriter fr = new FileWriter(cartFile, true);
            BufferedWriter bfr = new BufferedWriter(fr);
            for (String[] product : shoppingCartNew) {
                bfr.write(String.join(";", product) + "\n");
            }
            bfr.flush();
            bfr.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean removeFromCart(String customerUsername, String productName) {

        File f = new File("shoppingCart.txt");
        try {
            File cartFile = new File("shoppingCart.txt");
            Scanner scan = new Scanner(cartFile);
            boolean exists = false;
            ArrayList<String[]> shoppingCart = new ArrayList<>();
            ArrayList<String[]> newShoppingCart = new ArrayList<>();
            while (scan.hasNextLine()) {
                String initialCartData = scan.nextLine();
                String[] cartData = initialCartData.split(";");
                shoppingCart.add(cartData);
            }
            for (String[] product : shoppingCart) {
                if (product[0].equals(customerUsername) && product[2].equals(productName)) {
                    exists = true;
                } else {
                    newShoppingCart.add(product);
                }
            }
            if (!exists) {
                return false;
            }
            FileWriter fr = new FileWriter(f, false);
            BufferedWriter bfr = new BufferedWriter(fr);
            for (String[] product : newShoppingCart) {
                bfr.write(String.join(";", product) + "\n");
            }
            bfr.flush();
            bfr.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Still being implemented
     */
    public static void buyShoppingCartItems(String customerName) {

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

    private static String getPassword(String username) {
        // parses the file of all of the existing user in the marketplace
        File f = new File("users.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                // splits the line by the ";" token
                String[] temp = line.split(";");
                // email and password given match
                if (temp[0].equals(username)) {
                    // returns the userRole (s for Seller, c for Customer)
                    return temp[1];
                }
                line = bfr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // the info given doesn't match with an existing user
        return null;
    }

    /**
     * displayCart for customer to see all products in their cart
     *
     * @param userName the username of the customer
     * @return the arrayList of products in the cart
     * @author Nirmal Senthilkumar
     */
    public static ArrayList<String> displayCart(String userName) {
        File f = new File("shoppingCart.txt");
        ArrayList<String[]> shoppingCart = new ArrayList<>();
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String temp = scan.nextLine();
                String[] data = temp.split(";");
                if (data[0].equals(userName)) {
                    shoppingCart.add(data);
                }
            }
            ArrayList<String> returnList = new ArrayList<>();
            for (int i = 0; i < shoppingCart.size(); i++) {

            }
            return returnList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
