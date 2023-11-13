import java.io.*;
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

    /** Still being implemented*/
    public void addToCart(String productName, int quantity, String storeName) {

        File marketFile = new File("market.txt");
        String productStock = "";
        try {
            Scanner scan = new Scanner(marketFile);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");
                if (data[0].equals(productName) && data[2].equals(storeName)) {
                    productStock = data[3];
                    if (quantity > Integer.parseInt(productStock)) {
                        
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        String cart = "";
        // finding item from market to add to cart
        try (BufferedReader bfr = new BufferedReader(new FileReader("market.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                String[] product = line.split(",");
                if (product[7].equalsIgnoreCase(productName)) {
                    if (Integer.parseInt(product[4]) < quantity) {
                        System.out.println("Quantity is higher than stock.");
                    } else {
                        cart = String.format("%s,%s,%s,%s,%s,%s,%s", name, product[1], product[2], product[3],
                                quantity, product[6], productName);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // writing cart info to shoppingcart.txt
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("shoppingCart.txt", true))) {
            writer.write(cart);
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Still being implemented*/
    public void removeFromCart(String customerUsername, String productName, String productId) {
        StringBuilder fileContents = new StringBuilder();
        try (BufferedReader bfr = new BufferedReader(new FileReader("shoppingCart.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                String []product = line.split(";");
                if (product[0].equalsIgnoreCase(customerUsername)) {
                    // nothing happens then
                } else {
                    fileContents.append(line).append("\n");
                }
            }
            bfr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("shoppingCart.txt"))) {
            writer.write(fileContents.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**Still being implemented*/
    public void buyShoppingCartItems(String customerName) {
        try (BufferedReader bfr = new BufferedReader(new FileReader("shoppingCart.txt"))) {
            String line = bfr.readLine();
            while (line != null) {
                String[] product = line.split(",");
                if (product[0].equalsIgnoreCase(customerName)) {
                    buyItem(customerName, product[6], Integer.parseInt(product[4]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Still being implemented*/
<<<<<<< HEAD
    public static boolean buyItem(String customerName,String productId, int quantity) throws IOException, FileNotFoundException {
        FileReader fr = new FileReader("market.txt");
=======
    public static boolean buyItem(String customerName,String productId, int quantity) throws IOException {
        FileReader fr = new FileReader("/data/market.txt");
>>>>>>> 6341181bf9764a5013ba37b18b8329503485af37
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
                    for(int i = 0;i < market.length;i++){
                        if(i != 4){
                            newLineContent = market[i] + ",";
                        }else{
                            newLineContent = x + ",";
                        }

                    }
                    newLineContent = newLineContent.substring(0,newLineContent.length() - 1);
                    content.append(newLineContent).append("\n");
                    stringChanged = true;

                    String productsBought = market[1] + "," + market[2] + "," + market[3] + "," + quantity + "," + customerName + "," + market[6];
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
<<<<<<< HEAD
        PrintWriter writer = new PrintWriter(new FileWriter("market.txt"));
        writer.print(content.toString());
=======
        PrintWriter writer = new PrintWriter(new FileWriter("/data/market.txt"));
        writer.print(content);
>>>>>>> 6341181bf9764a5013ba37b18b8329503485af37
        writer.close();
        return stringChanged;
    }

    /** Customer Function*/
    public void viewPurchaseHistory() {

    }

    public void exportPurchaseHistory(String userName) {
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

    /** Customer Function*/
    public void viewStoreStatistics() {

    }


}
