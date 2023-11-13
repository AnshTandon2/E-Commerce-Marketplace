import java.util.*;

/**
 * Marketplace Class
 * <p>
 * Creates a marketplace class with the main functionality of getting store information.
 *
 * @author Nirmal Senthilkumar CS 180 Black
 * @version November 11, 2023
 */
public class Marketplace {
    private final ArrayList<Store> stores;

    public Marketplace(ArrayList<Store> stores) {
        this.stores = stores;
    }

    public static HashMap<Product, Integer> sortHashMap(HashMap<Product, Integer> hashMap, boolean ASCENDING) {
        List<Map.Entry<Product, Integer>> list = new LinkedList<>(hashMap.entrySet());
        list.sort(Map.Entry.comparingByValue());
        if (!ASCENDING)
            Collections.reverse(list);
        HashMap<Product, Integer> outputHashmap = new LinkedHashMap<>();
        for (Map.Entry<Product, Integer> aa : list) {
            outputHashmap.put(aa.getKey(), aa.getValue());
        }
        return outputHashmap;
    }

    public static ArrayList<Store> sortStores(ArrayList<Store> stores, boolean ASCENDING) {
        List<Store> list = new LinkedList<>(stores);
        list.sort(Comparator.comparingInt(Store::getQuantitySold));
        if (!ASCENDING)
            Collections.reverse(list);
        return new ArrayList<>(list);
    }

    public ArrayList<Integer> findProductsByName(String name) {
        ArrayList<Integer> productList = new ArrayList<>();
        for (Store store : stores) {
            for (Map.Entry<Integer, Integer> entry : store.getProducts().entrySet()) {
                if (Objects.requireNonNull(Product.getProduct(entry.getKey())).getName().contains(name)) {
                    productList.add(entry.getKey());
                }
            }
        }
        return productList;
    }

    public HashMap<Integer, Integer> findProductsByStore(String storeName) {
        for (Store store : stores) {
            if (store.getName().equals(storeName)) {
                return store.getProducts();
            }
        }
        return new HashMap<>();
    }

    public ArrayList<Integer> findProductsByDescription(String descriptionKey) {
        ArrayList<Integer> productList = new ArrayList<>();
        for (Store store : stores) {
            for (Map.Entry<Integer, Integer> entry : store.getProducts().entrySet()) {
                if (Objects.requireNonNull(Product.getProduct(entry.getKey())).getDescription().contains(descriptionKey)) {
                    productList.add(entry.getKey());
                }
            }
        }
        return productList;
    }

    public HashMap<Integer, Integer> findTotalProductList() {
        HashMap<Integer, Integer> returnMap = new HashMap<>();
        for (Store store : stores) {
            returnMap.putAll(store.getProducts());
        }
        return returnMap;
    }

    public String productsToString() {
        StringBuilder outputString = new StringBuilder("List of Products and Prices:\n");
        for (Store store : stores) {
            for (int productID : store.getProducts().keySet()) {
                outputString.append(Objects.requireNonNull(Product.getProduct(productID)).getName()).append(" : ")
                        .append(Objects.requireNonNull(Product.getProduct(productID)).getPrice()).append("\n");
            }
        }
        return outputString.toString();
    }

    public String listProductsByPrice(boolean ASCENDING) {
        HashMap<Integer, Integer> products = new HashMap<>();
        for (Store store : stores) {
            products.putAll(store.getProducts());
        }
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(products.entrySet());
        list.sort((o1, o2) -> (Objects.requireNonNull(Product.getProduct(o1.getKey())).getPrice()).
                compareTo(Objects.requireNonNull(Product.getProduct(o2.getKey())).getPrice()));
        HashMap<Integer, Integer> temp = new LinkedHashMap<>();
        if (ASCENDING) {
            for (Map.Entry<Integer, Integer> aa : list) {
                temp.put(aa.getKey(), aa.getValue());
            }
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Price Ascending:\n");
            for (int product : temp.keySet()) {
                outputString.append(Objects.requireNonNull(Product.getProduct(product)).getName()).append(" : ").
                        append(Objects.requireNonNull(Product.getProduct(product)).getPrice()).append("\n");
            }
            return outputString.toString();
        } else {
            Collections.reverse(list);
            for (Map.Entry<Integer, Integer> aa : list) {
                temp.put(aa.getKey(), aa.getValue());
            }
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Price Descending:\n");
            for (int product : temp.keySet()) {
                outputString.append(Objects.requireNonNull(Product.getProduct(product)).getName()).append(" : ").
                        append(Objects.requireNonNull(Product.getProduct(product)).getPrice()).append("\n");
            }
            return outputString.toString();
        }
    }

    public String listProductsByQuantity(boolean ASCENDING) {
        HashMap<Integer, Integer> products = new HashMap<>();
        for (Store store : stores) {
            products.putAll(store.getProducts());
        }
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(products.entrySet());
        list.sort((o1, o2) -> (Objects.requireNonNull(o1.getValue()).compareTo(o2.getValue())));
        HashMap<Integer, Integer> temp = new LinkedHashMap<>();
        if (ASCENDING) {
            for (Map.Entry<Integer, Integer> aa : list) {
                temp.put(aa.getKey(), aa.getValue());
            }
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Quantity Ascending:\n");
            for (int product : temp.keySet()) {
                outputString.append(Objects.requireNonNull(Product.getProduct(product)).getName()).append(" : ").
                        append(products.get(product)).append("\n");
            }
            return outputString.toString();
        } else {
            Collections.reverse(list);
            for (Map.Entry<Integer, Integer> aa : list) {
                temp.put(aa.getKey(), aa.getValue());
            }
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Quantity Descending:\n");
            for (int product : temp.keySet()) {
                outputString.append(Objects.requireNonNull(Product.getProduct(product)).getName()).append(" : ").
                        append(products.get(product)).append("\n");
            }
            return outputString.toString();
        }
    }

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

            } else {

            }
        }

        System.out.println("Thank you for using our service");

        scan.close();
    }

}
