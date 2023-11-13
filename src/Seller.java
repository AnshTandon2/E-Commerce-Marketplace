import java.io.*;
import java.nio.Buffer;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Seller Class
 * <p>
 * Initializes a seller object and
 * manages their selling history and identification
 * information.
 *
 * @author Nirmal Senthilkumar; Ansh Tandon CS 180 Black
 * @version November 12, 2023
 */
public class Seller extends User {
    // fields inherited from parent code: name, email, password
    private final File StoreHistory;
    private ArrayList<Store> stores;

    public Seller(String name, String email, String password) {
        super(name, email, password);
        // the purchase history file of every user will follow this notation
        String fileNotation = ("/data/" + email + ".txt");
        this.stores = new ArrayList<Store>();
        StoreHistory = new File(fileNotation);

        try {
            Scanner scan = new Scanner(StoreHistory);
            scan.nextLine();
            scan.nextLine();
            scan.nextLine();
            if (scan.hasNextLine()) {
                String[] data = scan.nextLine().split(";");
                for (int i = 0; i < data.length; i++) {
                    File f2 = new File("/data/" + data[i] + ".txt");
                    Scanner scan2 = new Scanner(f2);
                    HashMap<Integer, Integer> temp = new HashMap<>();
                    String storeID = scan2.nextLine();
                    String storeName = scan2.nextLine();
                    String[] merchandise = scan2.nextLine().split(";");
                    String[] merchStock = scan2.nextLine().split(";");
                    String[] soldCustomers = scan2.nextLine().split(";");
                    String[] soldQuantity = scan2.nextLine().split(";");
                    String[] soldProduct = scan2.nextLine().split(";");

                    HashMap<Integer, Integer> temp2 = new HashMap<>();
                    HashMap<String, HashMap<Integer, Integer>> temp3 = new HashMap<>();

                    for (int j = 0; j < merchandise.length; j++) {
                        temp.put(Integer.parseInt(merchandise[j]), Integer.parseInt(merchStock[j]));
                        temp2.put(Integer.parseInt(soldProduct[j]), Integer.parseInt(soldQuantity[j]));
                        temp3.put(soldCustomers[j], temp2);

                    }
                    this.stores.add(new Store(storeName, temp, 0.0, Integer.parseInt(storeID), temp3));
                }
            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        /*if (!StoreHistory.exists()) { //if file doesn't exist (Seller is new)
            try {
                FileWriter fw = new FileWriter(StoreHistory, false);
                BufferedWriter bfw = new BufferedWriter(fw);
                //First Line: [example@.email.com;John Doe]
                bfw.write(String.format(this.getEmail() + ";" + this.getName() + "\n"));
            } catch (IOException ignored) {
            }
        } else { //file exists, reading from it
            try {
                FileReader fr = new FileReader(StoreHistory);
                BufferedReader bfr = new BufferedReader(fr);
                String line = bfr.readLine();
                ArrayList<String> list = new ArrayList<>();
                while (line != null) {
                    list.add(line);
                    line = bfr.readLine();
                }
                list.remove(0);
                String storeName = "";
                HashMap<Product, Integer> productMap = new HashMap<>();
                for (String s : list) {
                    if (s.startsWith("Store: ")) { //if the line contains details on Store
                        storeName = s.substring(7); //get storeName while omitting the "Store: " in the beginning of
                        // the line
                    } else if (s.startsWith("Product<")) {//if the line contains details on the products
                        String[] split = line.split("Product<"); //split the list of products using the word Product
                        for (String product : split) {
                            //ex. Product<productName;Price;Description>;Quantity
                            //split the quantity at the end with the product details
                            String[] productAndQuantity = product.split(">");
                            //get product details and split them up into the Array: [productName, Price, Description]
                            String[] productDetail = productAndQuantity[1].split(";");
                            Product newProduct = new Product(productDetail[0], Double.parseDouble(productDetail[1]),
                                    productDetail[2]);
                            //add quantity to the map using the product as the key
                            productMap.put(newProduct, Integer.parseInt(productAndQuantity[2]));
                        }
                    } else if (s.startsWith("TotalSales: ")) {//if the line contains data on TotalSales
                        stores.add(new Store(storeName, productMap, Double.parseDouble(s.substring(12))));
                    }
                }
            } catch (IOException ignored) {
            }
        }*/
    }

    public void createSellerFile() {
        File f = new File("/data/" + super.getEmail() + ".txt");
        try {
            if (!f.exists()) {
                f.createNewFile();
                FileWriter fw = new FileWriter(f);
                fw.write(super.getName());
                fw.write("\n");
                fw.write(super.getEmail());
                fw.write("\n");
                fw.write(super.getPassword());
                fw.write("\n");
                fw.flush();
                fw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePurchaseHistoryFile() {
        try {
            FileWriter fw = new FileWriter(this.StoreHistory, false);
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write(String.format(this.getEmail() + ";" + this.getName()) + "\n");
            for (Store store : stores) {
                bfw.write(store.toString());
            }
            bfw.flush();
            bfw.close();
        } catch (IOException ignored) {
        }
    }

    public void modifyPurchaseHistoryFile(ArrayList<Store> list) {
        // takes the existing store history file for the user
        // and appends a new list of stores to it
        try {
            FileWriter fw = new FileWriter(this.StoreHistory, true);
            BufferedWriter bfw = new BufferedWriter(fw);
            for (Store store : list) {
                if (!stores.contains(store))
                    bfw.write(store.toString());
            }
        } catch (IOException ignored) {
        }
    }

    public void exportStoreInformation() {
        int counter = 0;
        File f = new File("/data/" + this.getEmail() + ".txt");
        File exportFile = new File("export.csv");
        try {
            exportFile.createNewFile();
            FileWriter fw = new FileWriter(exportFile);
            Scanner scan = new Scanner(f);
            scan.nextLine();
            scan.nextLine();
            String[] data = scan.nextLine().split(";");
            for (String s : data) {
                File f2 = new File("/data/" + s + ".txt");
                Scanner scan2 = new Scanner(f2);
                String storeID = scan2.nextLine();
                String storeName = scan2.nextLine();
                String[] merchandise = scan2.nextLine().split(";");
                String[] merchPrise = scan2.nextLine().split(";");
                fw.write(storeID + ":" + storeName);
                fw.write("\n");
                fw.write("\n");
                for (int i = 0; i < merchandise.length; i++) {
                    fw.write("Product: " + merchandise[i] + ", Quantity: " + merchPrise[i]);
                }
                fw.write("\n");
                fw.write("---------------------------");
                fw.write("\n");
                scan2.close();
            }
            scan.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void createProduct(String name, String store, String description, double price, int quantity) {
        ArrayList<Product> products = getProducts();
        products.add(new Product(name, price, description));
        setProducts(products);
        //add implementation here that updates quantity accordingly
//        addStoreToSellerFile(store);
        updateStoreFile();
        System.out.println(name + " was successfully added to marketplace!");
        //go back to main application (or just call createProduct there)

    }

    public void setProducts(ArrayList<Product> products) { //set the products.txt
        try (PrintWriter pw = new PrintWriter(new FileWriter("products.txt"))) {
            for (Product a : products) {
                pw.write(a.getName() + "," + a.getDescription() + "," + a.getPrice());
                pw.println();
            }
            pw.println();
            pw.close();
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void productSoldInfo() {
        for (Store s: stores) {
            HashMap<Integer, Integer> temp = s.getProductsList(); //hasmap of product Id and quantity,
            for (Integer productId: temp.keySet()) {
                Product a = Product.getProduct(productId);
                System.out.println(a.getName() + ", " + temp.get(productId));
            }
            //for each product it should go through all the stores and how much of quantityt sold for each product ID
        }
    }

    public void viewSales() {

        ArrayList<String> sales = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.getName() + ".txt"))) {
            int count = 0;
            String line = "";
            boolean salesAspect = false;
            while ((line = br.readLine()) != null) {
               if (line.contains("Sales")) {
                   salesAspect = true;
                   continue;
               }
               if (salesAspect) {
                   sales.add(line);
               }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String a : sales) {
            String[] arr = a.split(",");
            System.out.println("Product name: " + arr[0]);
            System.out.println("Quantity purchased: " + arr[1]);
            System.out.println("Customer name: " + arr[2]);
            System.out.println("");//get revenu
        }

    }

    public ArrayList<Product> getProducts() { //read the product file
        File file = new File("products.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList<Product> allProducts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                allProducts.add(new Product(tokens[0], Double.parseDouble(tokens[1]), tokens[2]));
            } // end of while loop
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    public boolean verifyProductIsNew() {

    }

    public void updateStoreFile() {


    }


    public void editProduct() { // to be completed

    }

    public void deleteProduct() {

    }

    public File getFile() {

    }

    public void importProducts() {  //to be done

    }


    public void exportProducts() { //don't need to implement

    }

    public void viewDashboard() {
        System.out.println("Enter your option: " +
                "1. View customers sorted by number of items purchased" +
                "2. View products sorted by sales per year" +
                "3. Back");

    }

    public void viewDashbaord(String order) {

    }

    public void viewProductsInCart() {
        System.out.println("Carts that have your products: ");

    }

    public ArrayList<Product> getCart() {
        ArrayList<Product> arrayList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(this.getEmail() + ".txt"));
            //read through

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public void importStoreFile(String pathName) {
        File f = new File(pathName);
        try {
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()) { 
                //format: [StoreName],[NewProductName],[NewProductQuantity],[NewProductPrice],[NewProductDescription];
                String[] data = scan.nextLine().split(",");
                for (Store s : stores) {
                    if (data[0].equals(s.getName())) {
                        s.addProduct(new Product(data[1], Double.parseDouble(data[3]), data[4]));
                    }
                }
            }

        scan.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void addNewStore(Store s) {
        stores.add(s);
    }

    // be able to view total sales by their store
    // should have their own sales invoice per store
    // sellers should be able to sort sales per product
    // should be able to get a file list for each store which specified the
    // products currently there along with the quantities sold

}
