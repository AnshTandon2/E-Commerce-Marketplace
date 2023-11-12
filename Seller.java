public class Seller extends User{

    private ArrayList<Store> stores;
    // fields from parent code
    // email
    // name
    // password

<<<<<<< HEAD
//    private PurchaseHistory purchaseHistory;
    private ShoppingCart shoppingCart;
    //store shopping cart and purchase history in separate clases and hten reference usernames in that file
    //buyer info and seller info (one csv file) 
    //
    public Seller(String info, boolean hasStores) {
=======
    public Seller(String info, ArrayList<Stores> storeList) {
>>>>>>> 5deea63ed7457c2117f360e2f3b2451f0f2a4e3a
        String[] arr = info.split(","); //Ansh,tandon39@purdue.du,hello
        // calls the parent constructor and sends the seller's name, email, and password
        super(arr[0], arr[1], arr[2]);
        if (this.stores.isEmpty()) {
            this.stores = new ArrayList<>();
        } else {
            this.stores = storeList;
        }
    }

}