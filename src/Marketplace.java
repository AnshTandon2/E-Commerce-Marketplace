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
}
