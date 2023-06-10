public class Inventory {
    public String name;
    public String category;
    public String date;
    public String exp_date;
    public double orig_price;
    public int qty;
    public double total_price;
    public double retail_price;
    public int sales_qty;
    public double total_sales_amount;
    public double profit;

    // Declaration
    Inventory(){
        this.name = "N/A";
        this.category = "N/A";
        this.date = "N/A";
        this.exp_date = "N/A";
        this.orig_price = 0.0;
        this.qty = 0;
        this.total_price = 0.0;
        this.retail_price = 0.0;
        this.sales_qty = 0;
        this.total_sales_amount = 0.0;
        this.profit = 0.0;
    }

    Inventory(String category, String name, String date, String exp_date, double orig_price, int qty, double retail_price){
        this.category = category;
        this.name = name;
        this.date = date;
        this.exp_date = exp_date;
        this.orig_price = orig_price;
        this.qty = qty;
        this.total_price = orig_price * qty;
        this.retail_price = retail_price;
        this.total_sales_amount = retail_price * this.sales_qty;
        this.profit = this.total_sales_amount - this.total_price;
    }
}
    /* public Inventory(String product_name, String date_time, double orig_price, int qty, double total_price,
                     double retail_price, int sales_qty, double total_sales_amount, double profit){
        this.product_name = product_name;
        this.date_time = date_time;
        this.orig_price = orig_price;
        this.qty = qty;
        this.total_price = total_price;
        this.retail_price = retail_price;
        this.sales_qty = sales_qty;
        this.total_sales_amount = total_sales_amount;
        this.profit = profit;
    }

    // getters and setters for instance variables
    public String getProductName() {
        return product_name;
    }
    public void setProductName(String product_name) {
        this.product_name = product_name;
    }

    public String getDateTime(){
        return date_time;
    }
    public void setDateTime(String date_time){
        this.date_time = date_time;
    }

    // Product Amount
    public double getPrice(){
        return orig_price;
    }
    public void setPrice(double orig_price){
        this.orig_price = orig_price;
    }

    // Product Quantity
    public int getQty(){
        return qty;
    }
    public void setQty(int qty){
        this.qty = qty;
    }

    // Total Amount
    public double getTotalAmount(){
        return total_price;
    }
    public void setTotalAmount(double total_price){
        this.total_price = total_price;
    }

    // Retail Price
    public double getRetailPrice(){
        return retail_price;
    }
    public void setRetailPrice(double retail_price){
        this.retail_price = retail_price;
    }

    // Sales Quantity
    public int getSalesQty(){
        return sales_qty;
    }
    public void setSalesQty(int sales_qty){
        this.sales_qty = sales_qty;
    }

    // Total Sales Amount
    public double getTotalSalesAmount(){
        return total_sales_amount;
    }
    public void setTotalSalesAmount(double total_sales_amount){
        this.total_sales_amount = total_sales_amount;
    }
    
    // Profit
    public double getProfit(){
        return profit;
    }
    public void setProfit(double profit){
        this.profit = profit;
    }*/


