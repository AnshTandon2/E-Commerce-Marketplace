import java.io.*;
import java.util.ArrayList;
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

    public void addToCart(String productName, int quantity, String storeName, String client, String password, String price) {

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
                                String toReplace = cartData[0] + ";" + cartData[1] + ";" + cartData[2] + ";" + cartData[3] + ";" + cartData[4] + (Integer.parseInt(cartData[5]) + quantity);
                                finalStr = finalStr.replaceAll(initialCartData,toReplace);
                                PrintWriter writer = new PrintWriter(cartFile);
                                writer.append(finalStr);
                                writer.flush();
                                exists = true;
                            }
                        }
                        if (exists == false) {
                            FileWriter writer = new FileWriter("shoppingCart.txt");
                            writer.write(client + ";" + password + ";" + productName + ";" + price + ";" + storeName + ";" + quantity);
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
    /** Still being implemented*/
    public static boolean buyItem(String customerName,String productId, int quantity) throws IOException {
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
        PrintWriter writer = new PrintWriter(new FileWriter("market.txt"));
        writer.print(content.toString());
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

    public static void viewHistory(String customerUsername) {
        //viewing the purchase history file
        try  {
            BufferedReader br = new BufferedReader(new FileReader("purchases.txt"));
            String line;
            ArrayList<String> histories = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                histories.add(line);
            }
            System.out.println("Product Purchase History");
            for (String temp: histories) {
                String[] arr = temp.split(";");
                if (arr[4].equals(customerUsername)) {
                    System.out.println("Name: " + arr[0] +  ", Price: " + arr[1] +  ", Store: " + arr[2] + ", Quantity: "
                            +  arr[3]);
                }
            }
        } catch (Exception e) {
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
