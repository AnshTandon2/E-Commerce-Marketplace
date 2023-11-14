import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Marketplace Class
 * <p>
 * Creates a marketplace class with the main functionality of getting store information.
 *
 * @author Nirmal Senthilkumar CS 180 Black
 * @version November 11, 2023
 */
public class Marketplace {
    public static final ArrayList<String> productIDs = new ArrayList<>();
    public static final ArrayList<String> productNames = new ArrayList<>();
    public static final ArrayList<Double> priceList = new ArrayList<>();
    public static final ArrayList<String> storeNames = new ArrayList<>();
    public static final ArrayList<Integer> quantityList = new ArrayList<>();
    public static final ArrayList<String> descriptionList = new ArrayList<>();

    /**
     * Reads the market.txt file and appends
     * Values to their corresponding ArrayLists;
     *
     * @author Lalitha Chandolu
     */
    public static void initializeMarketplace() {
        File file = new File("market.txt");
        int counter = 0;
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String[] data = s.nextLine().split(";");
                productNames.add(data[0]);
                priceList.add(Double.parseDouble(data[1]));
                storeNames.add(data[2]);
                quantityList.add(Integer.parseInt(data[3]));
                descriptionList.add(data[4]);
                productIDs.add(Integer.toString(counter));
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * updateMarketplace method to update the parallel arrayLists when the files are being changed
     *
     * @author Nirmal Senthilkumar
     */
    public static void updateMarketplace() {
        productNames.clear();
        priceList.clear();
        storeNames.clear();
        quantityList.clear();
        descriptionList.clear();
        productIDs.clear();
        try {
            int counter = 0;
            File file = new File("market.txt");
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                String[] data = line.split(";");
                productNames.add(data[0]);
                priceList.add(Double.parseDouble(data[1]));
                storeNames.add(data[2]);
                quantityList.add(Integer.parseInt(data[3]));
                descriptionList.add(data[4]);
                productIDs.add(Integer.toString(counter));
                counter++;
                line = bfr.readLine();
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * method to print the marketplace with all the products
     *
     * @author Lalitha Chandolu
     */
    public static void printMarketplace() {
        for (int index = 0; index < productIDs.size(); index++) {
            System.out.print(productDetail(index));
            System.out.println("----------------------------------");
        }
    }

    /**
     * find a product using a keyword, checking the name, store, and description to see if they contain the keyword
     *
     * @param keyword the keyword to search by
     * @return an arraylist of strings of productDetails that contain the keyword
     * @author Nirmal Senthilkumar
     */
    public static ArrayList<String> searchProduct(String keyword) {
        ArrayList<String> matchedProducts = new ArrayList<>();
        for (int i = 0; i < productIDs.size(); i++) {
            if (productNames.get(i).toLowerCase().contains(keyword.toLowerCase()) ||
                    storeNames.get(i).toLowerCase().contains(keyword.toLowerCase()) ||
                    descriptionList.get(i).toLowerCase().contains(keyword.toLowerCase())) {
                matchedProducts.add(productDetail(i));
            }
        }
        return matchedProducts;
    }

    /**
     * Get the product's price from specifying the name and seller
     *
     * @param productName   name of the product
     * @param productSeller seller of the product
     * @return
     */
    public static String getProductPrice(String productName, String productSeller) {
        String returnStr = null;
        File f = new File("market.txt");
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) {
                String initialData = scan.nextLine();
                String[] data = initialData.split(";");
                if (data[0].equals(productName) && data[2].equals(productSeller)) {
                    returnStr = data[1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnStr;
    }

    /**
     * Retrieve the details of the product as a string
     *
     * @param index the index of it in the file (the line number)
     * @return the string with all the product details including description
     */
    public static String productDetail(int index) {
        // index is decremented because when user enter index 1
        // they mean the first line - which is index 0 in Java
        if (quantityList.get(index) > 0) {
            // the quantity available of the product is greater than 0
            // return the detailed description of the product along with the other info
            return String.format("Product Name: %s" + "\nProduct Price: %.2f" + "\nStore: %s" + "\nQuantity: %d" +
                            "\nDescription: %s\n", productNames.get(index), priceList.get(index), storeNames.get(index),
                    quantityList.get(index), descriptionList.get(index));


        } else {
            // there is no stock available of the product
            // return the product name, price, store, and quantity (specify it's out of stock)
            return String.format("Product Name: %s" + "\nProduct Price: %.2f" + "\nStore: %s" + "\nQuantity: Out of" +
                            " Stock\n", productNames.get(index), priceList.get(index), storeNames.get(index),
                    quantityList.get(index));
        }
    }

    /**
     * sort the marketplace by price or quantity
     *
     * @param sortBy    is "price" or "quantity"
     * @param ASCENDING how to sort
     * @author Nirmal Senthilkumar, Justin
     */
    public static ArrayList<String> sortMarket(String sortBy, boolean ASCENDING) {
        ArrayList<String> sortedProductIds = new ArrayList<>(productIDs);

        if (sortBy.equalsIgnoreCase("price")) {
            sortedProductIds.sort(Comparator.comparing(o -> (priceList.get(productIDs.indexOf(o)))));
            if (!ASCENDING) {
                Collections.reverse(sortedProductIds);
            }
        } else if (sortBy.equalsIgnoreCase("quantity")) {
            sortedProductIds.sort(Comparator.comparing(o -> (quantityList.get(productIDs.indexOf(o)))));
            if (!ASCENDING) {
                Collections.reverse(sortedProductIds);
            }
        }
        return sortedProductIds;
    }
<<<<<<< Updated upstream
}
=======

    public String storesByQuantity(boolean ASCENDING) { //get first part of dashboard, list of stores by
        // quantity
        StringBuilder returnString = new StringBuilder("List of stores by number of products sold:\n");
        stores.sort(Comparator.comparing(o -> Double.valueOf(o.getQuantitySold())));
        if (ASCENDING) {
            stores.sort(Comparator.comparingInt(Store::getQuantitySold));

        }
        for (Store store : stores) {
            returnString.append(store.getName()).append(": ").append(store.getQuantitySold()).append("\n");
        }
        return returnString.toString();
    }

    public String storesByProductsPurchased(Customer c, boolean ASCENDING) { //get first part of dashboard, list of
        HashMap<Store, Integer> storeQuantity = new HashMap<>();
        StringBuilder returnString = new StringBuilder("List of stores by products purchased:\n");
        for (Store store : stores) {
            for (Map.Entry<String, HashMap<Integer, Integer>> entry : store.getCustomerHistories().entrySet()) {
                if (entry.getKey().equals(c.getEmail())) {
                    int quantity = 0;
                    for (Map.Entry<Integer, Integer> entry1 : entry.getValue().entrySet()) {
                        quantity += entry1.getValue();
                    }
                    storeQuantity.put(store, quantity);
                }
            }
        }
        List<Map.Entry<Store, Integer>> list = new LinkedList<>(storeQuantity.entrySet());
        list.sort((o1, o2) -> (Objects.requireNonNull(o1.getValue()).compareTo(o2.getValue())));
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        if (ASCENDING) {
            for (Map.Entry<Store, Integer> aa : list) {
                temp.put(aa.getKey().getName(), aa.getValue());
            }
            StringBuilder outputString = new StringBuilder("Stores by Quantity Sold Ascending:\n");
            for (Map.Entry<String, Integer> entry : temp.entrySet()) {
                outputString.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
            }
            return outputString.toString();
        } else {
            Collections.reverse(list);
            for (Map.Entry<Store, Integer> aa : list) {
                temp.put(aa.getKey().getName(), aa.getValue());
            }
            StringBuilder outputString = new StringBuilder("Stores by Quantity Sold Descending:\n");
            for (Map.Entry<String, Integer> entry : temp.entrySet()) {
                outputString.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
            }
            return outputString.toString();
        }
    }

    public ArrayList<Store> getStores() {
        return stores;
    }

    public void addStores(ArrayList<Store> stores) {
        this.stores.addAll(stores);
    }

    public void addStore(Store store) {
        this.stores.add(store);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to marketplace!");

        String userName = "";
        String password = "";
        String email = "";

        boolean loggedIn = false;
        boolean exitOnFirst = false;

        do {
            try {
                System.out.println("Select an option:\n1. Login\n2. Signup\n3. Exit");
                String firstPgOption = scan.nextLine();
                if (Integer.parseInt(firstPgOption) == 1) {
                    System.out.println("Enter email");
                    email = scan.nextLine();
                    System.out.println("Enter password");
                    password = scan.nextLine();
                    if (User.accountExists(email, password)) {
                        loggedIn = true;
                    } else {
                        System.out.println("email or password is incorrect");
                    }
                } else if (Integer.parseInt(firstPgOption) == 2) {
                    System.out.println("Enter new email (this will serve as your ID/Username)");
                    email = scan.nextLine();
                    System.out.println("Enter new password");
                    password = scan.nextLine();
                    System.out.println("Enter your name");
                    userName = scan.nextLine();
                    System.out.println("Will you be a buyer (enter 1) or seller? (enter 2)");
                    String isBuyer = scan.nextLine();
                    if (User.accountExists(email, password)) {
                        System.out.println("Account already exists");
                    } else {
                        if (Integer.parseInt(isBuyer) == 1) {
                            Customer custom = new Customer(userName, email, password);
                            User.addUser(custom);
                            custom.createCustomerFile();
                        } else if (Integer.parseInt(isBuyer) == 2) {
                            Seller sell = new Seller(userName, email, password);
                            User.addUser(sell);
                            sell.createSellerFile();
                        }
                        //loggedIn = true;
                    }


                } else if (Integer.parseInt(firstPgOption) == 3) {
                    exitOnFirst = true;
                    loggedIn = true;
                } else {
                    System.out.println("Please enter a valid choice");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid choice");
            }
        } while (!loggedIn);

        boolean start = true;

        if (exitOnFirst) {
            start = false;
        }

        User user = User.getUserObject(email, password);

        Product.populateProductList();
        User.generateUserList();

        Customer c = null;
        Seller s = null;

        if (user instanceof Seller) {
            s = new Seller(userName, email, password);
        } else if (user instanceof Customer) {
            c = new Customer(userName, email, password);
        }


        while (start) {
            if (user instanceof Seller) {
                System.out.println("Main Menu:\n1. Manage Stores\n2. Dashboard\n3. \n4. \n5. Exit");
                String MMChoice = scan.nextLine();
                if (MMChoice.equals("1")) {
                    ArrayList<Store> myStores = s.getStores();
                    System.out.println("Select Store to Manage");
                    for (int i = 0; i < myStores.size(); i++) {
                        System.out.println(i + ". " + myStores.get(i));
                    }
                    String storeChoice = scan.nextLine();
                    System.out.println("What are you trying to do?\n1. Create Product\n2. Edit Product\n3. Delete Product");
                    String doingChoice = scan.nextLine();
                    
                }

            } else {

            }
        }

        System.out.println("Thank you for using our service");

        scan.close();
    }

}
>>>>>>> Stashed changes
