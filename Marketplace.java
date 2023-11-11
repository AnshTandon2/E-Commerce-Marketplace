import java.util.*;

public class Marketplace {
    private final ArrayList<Store> stores;

    public Marketplace(ArrayList<Store> stores) {
        this.stores = stores;
    }

    public ArrayList<Product> findProductsByName(String name) {
        ArrayList<Product> productList = totalProductList();
        ArrayList<Product> returnList = new ArrayList<>();
        for (Product p : productList) {
            if (p.getProductName().contains(name)) {
                returnList.add(p);
            }
        }
        return returnList;
    }

    public ArrayList<Product> findProductsByStore(String storeName) {
        for (Store store : stores) {
            if (store.getName().equals(storeName)) {
                return store.getProducts();
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
            productList.addAll(store.getProducts());
        }
        return productList;
    }

    public String listProducts() {
        StringBuilder outputString = new StringBuilder("List of Products and Prices:\n");
        for (Store store : stores) {
            for (Product product : store.getProducts()) {
                outputString.append(product.getProductName()).append(" : ").append(product.getPrice()).append("\n");
            }
        }
        return outputString.toString();
    }

    public String listProductsByPrice(boolean ASCENDING) {
        ArrayList<Product> products = new ArrayList<>();
        for (Store store : stores) {
            products.addAll(store.getProducts());
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
                outputString.append(product.getProductName()).append(" : ").append(product.getPrice()).append("\n");
            }
            return outputString.toString();
        } else {
            Collections.reverse(products);
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Price Ascending:\n");
            for (Product product : products) {
                outputString.append(product.getProductName()).append(" : ").append(product.getPrice()).append("\n");
            }
            return outputString.toString();
        }
    }

    public String listProductsByQuantity(boolean ASCENDING) {
        ArrayList<Product> products = new ArrayList<>();
        for (Store store : stores) {
            products.addAll(store.getProducts());
        }
        products.sort(new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p1.getQuantityAvail(), p2.getQuantityAvail());
            }
        });
        if (ASCENDING) {
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Price Ascending:\n");
            for (Product product : products) {
                outputString.append(product.getProductName()).append(" : ").append(product.getQuantityAvail()).append("\n");
            }
            return outputString.toString();
        } else {
            Collections.reverse(products);
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Price Ascending:\n");
            for (Product product : products) {
                outputString.append(product.getProductName()).append(" : ").append(product.getPrice()).append("\n");
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
