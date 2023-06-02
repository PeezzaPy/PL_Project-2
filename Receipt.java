public class Receipt {
    public String productName;
    public int quantity;
    public double price;
    public double totalPrice;


    Receipt(){
        this.productName="N/a";
        this.price = 0;
        this.quantity = 0;
        this.totalPrice = 0;
    }
    Receipt(String productName, int quantity, double price, double totalPrice){
        this.productName=productName;
        this.quantity=quantity;
        this.price=price;
        this.totalPrice=totalPrice;
    }
}
