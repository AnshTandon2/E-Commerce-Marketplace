import java.util.*;

public class Marketplace {
    private ArrayList<Store> stores;

    public Marketplace(ArrayList<Store> stores) {
        this.stores = stores;
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
                return p1.getPrice().compareTo(p2.getPrice());
            }
        });
        if (ASCENDING) {
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Price Ascending:\n");
            for (Product product: products) {
                outputString.append(product.getProductName()).append(" : ").append(product.getPrice()).append("\n");
            }
            return outputString.toString();
        }
        else {
            Collections.reverse(products);
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Price Ascending:\n");
            for (Product product: products) {
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
                return Integer(p1.getQuantity()).compareTo(Integer(p2.getQuantity()));
            }
        });
        if (ASCENDING) {
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Price Ascending:\n");
            for (Product product: products) {
                outputString.append(product.getProductName()).append(" : ").append(product.getQuantity()).append("\n");
            }
            return outputString.toString();
        }
        else {
            Collections.reverse(products);
            StringBuilder outputString = new StringBuilder("List of Products and Prices by Price Ascending:\n");
            for (Product product: products) {
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
