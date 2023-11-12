public class Seller {

    private ArrayList<Store> stores; //stores they own

//    private PurchaseHistory purchaseHistory;
    private ShoppingCart shoppingCart;
    //store shopping cart and purchase history in separate clases and hten reference usernames in that file
    //buyer info and seller info (one csv file) 
    //
    public Seller(String info, boolean hasStores) {
        String[] arr = info.split(","); //Ansh,tandon39@purdue.du,hello
        super(arr[0], arr[1], arr[2]);
        if (hasStores) {
            stores = new ArrayList<>();
        } else {
            stores = new ArrayList<>();
        }
        this.loginInfo = new User(email, password);
        this.purchaseHistory = new PurchaseHistory();
        this.shoppingCart = new ShoppingCart();
    }



}