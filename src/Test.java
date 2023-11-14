import java.io.IOException;

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
        String output = Seller.removeProduct("Purdue laptop bag", "dylanStore"); //Should return that the product was removed sucessfully
        System.out.println(output);
        Marketplace.updateMarketplace();
        //Seller.removeProduct("Purdue XD", "dylanStore"); //Should return that the product doesn't exist
        //Seller.productExists("Purdue nonexistent thing", "dylanStore"); //Should return false
        //Seller.productExists("Purdue hoodie", "notAStore");//Should return false
        //Seller.productExists("Purdue Half Zip", "dylanStore"); //should return true
        //Seller.addProduct("newproduct!", 50.00, "newStore!", 7, "A new Test product", "newTestSeller"); //should add properly
        //Seller.editProduct("newProduct!", "newStore!", "quantity", "9"); //should update
        //Seller.editProduct("nonoexistentProduct", "nonexistentstore", "description", "Hello World"); //should return prouduct not found
        //Seller.exportStoreInformation("dylanK", "dylanStore"); //should export file containing dylanStore products
        //format:
        //Store,item,price

        //Seller.exportStoreInformation("noneexistentSeller", "dylanStore"); //should not export anything
        //Seller.importStoreInformation("dylank","testImportFile.txt");
        //Seller.viewStoreStatistics("davidkj", 1);
        //Seller.viewStoreStatistics("davidkj", 2);
        //Seller.viewStoreStatistics("nonExistentUser", 1);
        /* End of Seller Class Testing */

        /* Customer Class Testing */

        //Customer.exportPurchaseHistory("tandon39");
        //should create a csv file in root dir containing the purchases of this person 
        //format:
        //productName;price;storeName;quantity;customer-userame;seller-username
        //Customer.viewShoppingHistory("tandon39", "price", false);
        //Customer.viewShoppingHistory("tandon39", "price", true);
        //Customer.viewShoppingHistory("tandon39", "quantity", false);
        //Customer.viewShoppingHistory("tandon39", "quantity", true);
        //Customer.addToCart("Purdue Hoodie", "sandyStore", 4, "tandon39"); //should be sucessful
        //Customer.addToCart("Purdue Hat", "sandyStore", 4, "tandon39"); //should say out of stock
        //Customer.buyShoppingCartItems("tandon39"); // should buy things and update purchases.txt to reflect the things brought 
        //as well as if brought properly, should decrease the stock by number of things brought 
        //Customer.removeFromCart("tandon39", "Purdue Overalls"); //should remove from shoppingcart
        //Customer.removeFromCart("tandon39", "Noneexistentthing"); //should display error
        //should delete the entire line from shoppingCart.txt


        /* End customer class testing */

        /* MarketPlace Class Testing */

        //Marketplace.initializeMarketplace();
        //Marketplace.updateMarketplace();
        //Marketplace.printMarketplace();
        //Marketplace.getProductInfo(7);
        //Marketplace.searchProduct("badge");
        //Marketplace.searchProduct("nonexistentThings");
        //Marketplace.getProductPrice("Purdue Hat", "sandyruk");
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
