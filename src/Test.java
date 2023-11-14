import java.io.IOException;

public class Test {

    /**
     * Test Cases File
     * Uses extensive test cases to test the functionality of all of our methods in the project
     * And to verify that the user workflow is as expected for a Seller vs for a Buyer
     *
     * @author
     * @version November 14, 2023
     */

    public static void main(String[] args) {
        // call each test case method from here
        /* Seller Class Testing */
        Seller.removeProduct(null, null);
        Seller.productExists(null, null);
        Seller.addProduct(null, 0, null, 0, null, null);
        Seller.editProduct(null, null, null, null);
        Seller.listInformationByStore(null);
        Seller.exportStoreInformation(null, null);
        Seller.importStoreInformation(null);
        Seller.viewStoreStatistics(null, 0);
        /* End of Seller Class Testing */

        /* Customer Class Testing */
        try {
            Customer.buyItem(null, null, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Customer.exportPurchaseHistory(null);
        Customer.viewShoppingHistory(null, null, false);
        Customer.addToCart(null, 0, null, null, null, null);
        Customer.buyShoppingCartItems(null);
        Customer.removeFromCart(null, null);


        /* End customer class testing */

        /* MarketPlace Class Testing */

        Marketplace.initializeMarketplace();
        Marketplace.updateMarketplace();
        Marketplace.printMarketplace();
        Marketplace.getProductInfo(0);
        Marketplace.searchProduct(null);
        Marketplace.getProductPrice(null, null);
        Marketplace.productDetail(0);
        Marketplace.sortMarket(null, false);
        Marketplace.removeFromCart(null, null);
        Marketplace.displayCart(null);
        

        /* End MarketPlace Class testing */
    }
}
