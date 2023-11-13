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

    /** Still being implemented*/
    public void addToCart(String productName, int quantity, String storeName) {

        File marketFile = new File("data/market.txt");
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
        try (BufferedReader bfr = new BufferedReader(new FileReader("data/market.txt"))) {
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
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("data/shoppingCart.txt", true))) {
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
        try (BufferedReader bfr = new BufferedReader(new FileReader("data/shoppingCart.txt"))) {
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/shoppingCart.txt"))) {
            writer.write(fileContents.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**Still being implemented*/
    public void buyShoppingCartItems(String customerName) {
        try (BufferedReader bfr = new BufferedReader(new FileReader("data/shoppingCart.txt"))) {
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
    public static boolean buyItem(String customerName,String productId, int quantity) throws IOException {
        FileReader fr = new FileReader("data/market.txt");
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
                    PrintWriter writer2 = new PrintWriter(new FileWriter("data/purchases.txt"));
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
        writer.print(content.toString());
        writer.close();
        return stringChanged;
    }

    /** Customer Function*/
    public void viewPurchaseHistory() {

    }

    public void exportPurchaseHistory(String userName) {
        File exportFile = new File("purchaseHistoryExport.csv");
        File readingFile = new File("data/purchases.txt");
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
    public static void viewStoreStatistics(String userName) {
        File purchases = new File("purchases.txt");
        ArrayList<String> storesBroughtFrom = new ArrayList<>();
        try {
            Scanner scan = new Scanner(purchases);
            while (scan.hasNextLine()) {
                String initialData = scan.nextLine();
                String[] data = initialData.split(";");
                if (data[4].equals(userName)) {
                    storesBroughtFrom.add(initialData);
                }
            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String s : storesBroughtFrom) {
            String[] recordedData = s.split(";");
            System.out.println("You brought " + recordedData[3] + " " + recordedData[0] + " from " + recordedData[2] + " for " + recordedData[1]);
        }

        Scanner userScan = new Scanner(System.in);       

        System.out.println("1. Sort by amount spent\n2. Sort by items brought\n(Anything else. ) exit");
        String sortChoice = userScan.nextLine();
        if (sortChoice.equals("1")) {
            int counter = 0;
            int index = 0;
            for (int i = 0; i < storesBroughtFrom.size(); i++) {
                for (int j = 0; j < storesBroughtFrom.size(); j++) {
                    String[] data = storesBroughtFrom.get(j).split(";");
                    if (Double.parseDouble(data[1]) * Double.parseDouble(data[3]) > counter) {
                        index = j;
                    }
                }
                String[] recordedData = storesBroughtFrom.get(index).split(";");
                System.out.println("You brought " + recordedData[3] + " " + recordedData[0] + " from " + recordedData[2] + " for " + recordedData[1]);
                storesBroughtFrom.remove(index);
            }
        } else if (sortChoice.equals("2")) {
            int counter = 0;
            int index = 0;
            for (int i = 0; i < storesBroughtFrom.size(); i++) {
                for (int j = 0; j < storesBroughtFrom.size(); j++) {
                    String[] data = storesBroughtFrom.get(j).split(";");
                    if (Double.parseDouble(data[3]) > counter) {
                        index = j;
                    }
                }
                String[] recordedData = storesBroughtFrom.get(index).split(";");
                System.out.println("You brought " + recordedData[3] + " " + recordedData[0] + " from " + recordedData[2] + " for " + recordedData[1]);
                storesBroughtFrom.remove(index);
            }
        }
    }


}
