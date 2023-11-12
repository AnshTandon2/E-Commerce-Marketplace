public class Seller extends User{

    private ArrayList<Store> stores;
    // fields from parent code
    // email
    // name
    // password
    
    public Seller(String info, ArrayList<Stores> storeList) {
        String[] arr = info.split(","); //Ansh,tandon39@purdue.edu,hello
        // calls the parent constructor and sends the seller's name, email, and password
        super(arr[0], arr[1], arr[2]);
        if (this.stores.isEmpty()) {
            this.stores = new ArrayList<>();
        } else {
            this.stores = storeList;
        }
    }

}
