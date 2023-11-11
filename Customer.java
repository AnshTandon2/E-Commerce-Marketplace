public class Customer extends User{
    private String name;
    private User loginInfo;
    private String email;
    private PurchaseHistory purchaseHistory;
    private ShoppingCart shoppingCart;
    
    @Override
    public Customer(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.loginInfo = new User(email, password);
        this.purchaseHistory = new PurchaseHistory();
        this.shoppingCart = new ShoppingCart();
    }

    public boolean login(String email, String password) {
        return loginInfo.accountExists(email, password);
    }

    public void exportPurchaseHistory() {
        purchaseHistory.exportHistory();
    }

    public void addToShoppingCart(String product) {
        shoppingCart.addProduct(product);
    }

    public void removeFromShoppingCart(String product) {
        shoppingCart.removeProduct(product);
    }

    public void viewShoppingCart() {
        shoppingCart.viewShoppingCart();
    }

    public static void main(String[] args) {
        Customer justin = new Customer("Justin", "justin@example.com", "password123");

        // Example usage
        if (justin.login("justin@example.com", "password123")) {
            justin.addToShoppingCart("Product 1");
            justin.addToShoppingCart("Product 2");

            justin.viewShoppingCart();

            justin.removeFromShoppingCart("Product 1");

            justin.viewShoppingCart();

            justin.exportPurchaseHistory();
        } else {
            System.out.println("Invalid login information.");
        }
    }
}
