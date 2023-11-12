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

    public static HashMap<Product, Integer> sortHashMap(HashMap<Product, Integer> hashMap,
                                                        boolean ASCENDING) {
        List<Map.Entry<Product, Integer>> list = new LinkedList<>(hashMap.entrySet());
        list.sort((o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));
        if (!ASCENDING)
            Collections.reverse(list);
        HashMap<Product, Integer> outputHashmap = new LinkedHashMap<>();
        for (Map.Entry<Product, Integer> aa : list) {
            outputHashmap.put(aa.getKey(), aa.getValue());
        }
        return outputHashmap;
    }

    public static ArrayList<Store> sortStores(ArrayList<Store> stores,
                                              boolean ASCENDING) {
        List<Store> list = new LinkedList<>(stores);
        list.sort(Comparator.comparingInt(Store::getQuantitySold));
        if (!ASCENDING)
            Collections.reverse(list);
        return new ArrayList<>(list);
    }

    public ArrayList<Product> findProductsByName(String name) {
        ArrayList<Product> productList = totalProductList();
        ArrayList<Product> returnList = new ArrayList<>();
        for (Product p : productList) {
            if (p.getName().contains(name)) {
                returnList.add(p);
            }
        }
        return returnList;
    }

    public ArrayList<Product> findProductsByStore(String storeName) {
        for (Store store : stores) {
            if (store.getName().equals(storeName)) {
                return new ArrayList<Product>(store.getProducts().keySet());
            }
        }
        return new ArrayList<Product>();
    }

    public ArrayList<Product> findProductsByDescription(String descriptionKey) {
        ArrayList<Product> productList = totalProductList();
        ArrayList<Product> returnList = new ArrayList<>();
        for (Product p : productList) {
            if (p.getProductDescription().contains(descriptionKey)) {
                returnList.add(p);
            }
        }
        return returnList;
    }

    public ArrayList<Product> totalProductList() {
        ArrayList<Product> productList = new ArrayList<Product>();
        for (Store store : stores) {
            productList.addAll(store.getProducts().keySet());
        }
        return productList;
    }

    public String listProducts() {
        StringBuilder outputString = new StringBuilder("List of Products and Prices:\n");
        for (Store store : stores) {
            for (Product product : store.getProducts().keySet()) {
                outputString.append(product.getName()).append(" : ").append(product.getPrice()).append("\n");
            }
        }
        return outputString.toString();
    }

    public String listProductsByPrice(boolean ASCENDING) {
        ArrayList<Product> products = new ArrayList<>();
        for (Store store : stores) {
            products.addAll(store.getProducts().keySet());
        }
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        if (ASCENDING) {
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Price Ascending:\n");
            for (Product product : products) {
                outputString.append(product.getName()).append(" : ").append(product.getPrice()).append("\n");
            }
            return outputString.toString();
        } else {
            Collections.reverse(products);
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Price Descending:\n");
            for (Product product : products) {
                outputString.append(product.getName()).append(" : ").append(product.getPrice()).append("\n");
            }
            return outputString.toString();
        }
    }

    public String CustomerDashboard(Customer c) {
        StringBuilder returnString = new StringBuilder("List of stores by number of products sold:\n");
        for (Store store : stores) {
            returnString.append(store.getName()).append(": ").append(store.getProducts().size()).append("\n");
        }
        returnString.append("List of stores by products purchased:\n");
        HashMap<Product, Integer> customerHistory = c.getProductHistoryMap();
        for (Product product : customerHistory.keySet()) {
            for (Store store : stores) {
                if (store.getProducts().containsKey(product)) {
                    returnString.append(store.getName()).append(": ").append(product.toString());
                }
            }
        }
        return returnString.toString();
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

    public String CustomerDashboard(Customer c, boolean ASCENDING) {
        StringBuilder returnString = new StringBuilder("List of stores by number of products sold:\n");
        for (Store store : sortStores(stores, ASCENDING)) {
            returnString.append(store.getName()).append(": ").append(store.getProducts().size()).append("\n");
        }
        returnString.append("List of stores by products purchased:\n");
        HashMap<Product, Integer> customerHistory = c.getProductHistoryMap();
        customerHistory = sortHashMap(c.getProductHistoryMap(), ASCENDING);
        for (Product product : customerHistory.keySet()) {
            for (Store store : stores) {
                if (store.getProducts().containsKey(product)) {
                    returnString.append(store.getName()).append(": ").append(product.toString());
                }
            }
        }

        return returnString.toString();
    }

    public String listProductsByQuantity(boolean ASCENDING) {
        String returnString = "";
        HashMap<Product, Integer> products = new HashMap<>();
        for (Store store : stores) {
            products.putAll(store.getProducts());
        }

        List<Map.Entry<Product, Integer>> list = new LinkedList<Map.Entry<Product, Integer>>(products.entrySet());
        list.sort(new Comparator<Map.Entry<Product, Integer>>() {
            public int compare(Map.Entry<Product, Integer> o1, Map.Entry<Product, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        HashMap<Product, Integer> returnHashMap = new LinkedHashMap<Product, Integer>();
        if (!ASCENDING) {
            Collections.reverse(list);
        }
        for (Map.Entry<Product, Integer> entry : list) {
            returnString += entry.getValue();
        }
        return returnString;
    }
}
