import java.io.IOException;
import java.util.*;

public class Test {

    /**
     * Test Cases File
     * Uses extensive test cases to test the functionality of all of our methods in the project
     * And to verify that the user workflow is as expected for a Seller vs for a Buyer
     *
     * @author Justin
     * @version November 14, 2023
     */

    public static void main(String[] args) {

        Marketplace.initializeMarketplace();

        // call each test case method from here
        /* Seller Class Testing */
        //String output = Seller.removeProduct("Purdue laptop bag", "dylanStore"); //Should return that the product was removed sucessfully
        //String output = Seller.removeProduct("Purdue XD", "dylanStore"); //Should return that the product doesn't exist
        //boolean output = Seller.productExists("Purdue nonexistent thing", "dylanStore"); //Should return false
        //boolean output = Seller.productExists("Purdue hoodie", "notAStore");//Should return false
        //boolean output = Seller.productExists("Purdue Half Zip", "dylanStore"); //should return true
        //String output = Seller.addProduct("newproduct!", 50.00, "newStore!", 7, "A new Test product", "newTestSeller"); //should add properly
        //String output = Seller.editProduct("newproduct!", "newStore!", "quantity", "999"); //should update
        //TODO Editproducts still not working
        //Seller.editProduct("nonoexistentProduct", "nonexistentstore", "description", "Hello World"); //should return prouduct not found
        //boolean output = Seller.exportStoreInformation("dylank", "dylanStore"); //should export file containing dylanStore products
        //format:
        //Store,item,price

        //boolean output = Seller.exportStoreInformation("noneexistentSeller", "dylanStore"); //should not export anything
        //boolean output = Seller.importStoreInformation("dylank","testImportFile.txt");
        //String output = Seller.viewStoreStatistics("davidkj", 1);
        //String output = Seller.viewStoreStatistics("davidkj", 2);
        //String output = Seller.viewStoreStatistics("davidkj", 2);
        /* End of Seller Class Testing */

        /* Customer Class Testing */

        //Customer.exportPurchaseHistory("tandon39");
        //should create a csv file in root dir containing the purchases of this person 
        //format:
        //productName;price;storeName;quantity;customer-userame;seller-username

        //String output = Customer.viewShoppingHistory("tandon39", "price", false);
        //String output = Customer.viewShoppingHistory("tandon39", "price", true);
        //String output = Customer.viewShoppingHistory("tandon39", "quantity", false);
        //String output = Customer.viewShoppingHistory("tandon39", "quantity", true);
        //boolean output = Customer.addToCart("Purdue hoodie", "sandyStore", 4, "tandon39"); //should be sucessful
        //boolean output = Customer.addToCart("Purdue hat", "sandyStore", 4, "tandon39"); //should say out of stock
        //String output = Customer.buyShoppingCartItems("tandon39"); // should buy things and update purchases.txt to reflect the things brought 
        //as well as if brought properly, should decrease the stock by number of things brought 

        //boolean output = Customer.removeFromCart("tandon39", "Book"); //should remove from shoppingcart
        //boolean output = Customer.removeFromCart("tandon39", "Noneexistentthing"); //should display error

        //should delete the entire line from shoppingCart.txt


        /* End customer class testing */

        /* MarketPlace Class Testing */

        //Marketplace.initializeMarketplace();
        //Marketplace.updateMarketplace();
        //Marketplace.printMarketplace();
        //ArrayList<String> output = Marketplace.searchProduct("badge");
        /*for (String s : output) {
            System.out.println(s);
        } */
        //ArrayList<String> output = Marketplace.searchProduct("nonexistentThings");
        String output = Marketplace.getProductPrice("Purdue hat", "sandyruk");
        System.out.println(output);
        //Marketplace.productDetail(4);
        //Marketplace.sortMarket("price", false);
        //Marketplace.sortMarket("price", true);
        //Marketplace.sortMarket("quantity", false);
        //Marketplace.sortMarket("quantity", true);
        //Marketplace.removeFromCart(null, null); // this shouldn't even be here


        /* End MarketPlace Class testing */

        /* StartingApplication testing */
        //StartingApplication.main(args);
        /* End StartingApplication */
    }
}
