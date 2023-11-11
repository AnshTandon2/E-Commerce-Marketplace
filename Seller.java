public class Seller {

    private String name;
    private User loginInfo;
    private String email;
    private PurchaseHistory purchaseHistory;
    private ShoppingCart shoppingCart;


    public Seller(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.loginInfo = new User(email, password);
        this.purchaseHistory = new PurchaseHistory();
        this.shoppingCart = new ShoppingCart();
    }



}