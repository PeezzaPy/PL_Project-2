import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;


public class DataManager {
    static String inventory_dir = "C:\\Users\\ASUS\\Desktop\\PL_Project-2\\product\\";
    static String productHistory_dir = "C:\\Users\\ASUS\\Desktop\\PL_Project-2\\product\\product_history\\";
    static String expProductHistory_dir = "C:\\Users\\ASUS\\Desktop\\PL_Project-2\\product\\expired_history\\";
    static String expDateProduct_dir = "C:\\Users\\ASUS\\Desktop\\PL_Project-2\\product\\expiration_date_product\\";
    static String data_line, time = "";

    public static void save(){
        File inventory_fp = new File(inventory_dir + "inventory.txt");
        try {
            if(!inventory_fp.exists())  
                inventory_fp.createNewFile();
        } catch (Exception e){
        }

        try (FileWriter writer = new FileWriter(inventory_fp)){
            for(Inventory product : Main.my_inv){
                if(product != null){
                    writer.write(product.category + '\n');

                    writer.write(product.date + '\n');

                    writer.write(product.name + '\n');

                    writer.write(product.exp_date + '\n');

                    writer.write(String.valueOf(product.orig_price));
                    writer.write(' ');

                    writer.write(String.valueOf(product.qty));
                    writer.write(' ');

                    writer.write(String.valueOf(product.total_price));
                    writer.write(' ');

                    writer.write(String.valueOf(product.retail_price));
                    writer.write(' ');

                    writer.write(String.valueOf(product.sales_qty));
                    writer.write(' ');

                    writer.write(String.valueOf(product.total_sales_amount));
                    writer.write(' ');

                    writer.write(String.valueOf(product.profit));
                    writer.write("\n\n");
                }
            }
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static void retrieve(){
        Inventory my_product = new Inventory();
        String inventory_fp = inventory_dir + "inventory.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(inventory_fp))){        
            while((data_line = reader.readLine()) != null){
                my_product.category = data_line;
                my_product.date = reader.readLine();
                my_product.name = reader.readLine();
                my_product.exp_date = reader.readLine();

                String line = reader.readLine();
                if(line != null){
                    try {
                        String[] product_data = line.split(" ");
                        my_product.orig_price = Double.parseDouble(product_data[0]);
                        my_product.qty = Integer.parseInt(product_data[1]);
                        my_product.total_price = Double.parseDouble(product_data[2]);
                        my_product.retail_price = Double.parseDouble(product_data[3]);
                        my_product.sales_qty = Integer.parseInt(product_data[4]);
                        my_product.total_sales_amount = Double.parseDouble(product_data[5]);
                        my_product.profit = Double.parseDouble(product_data[6]);

                        Admin.addProduct(my_product);
                    } catch (NumberFormatException e) {
                        System.err.println("\n\nInvalid numeric value in the file: " + e.getMessage());
                    }
                } 
                reader.readLine();
            }
            reader.close();
        } catch(IOException e){
        }
    }


    public static void recordProduct(Inventory product){
        File record_fp = new File (productHistory_dir + product.date + ".txt");    
        try {
            if(!record_fp.exists())
                record_fp.createNewFile();
        } catch (Exception e){
        }

        try(FileWriter fileWriter = new FileWriter(record_fp, true)){
            fileWriter.write("Product Name: " + product.name + '\n');
            fileWriter.write("Expiration Date: " + product.exp_date + '\n');
            fileWriter.write("Quantity: " + String.valueOf(product.qty) + "\n\n");
            fileWriter.close();

            recordExpirationDateProduct(product.exp_date, product.name, product.qty);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void recordExpirationDateProduct(String prod_exp_date, String prod_name, int prod_qty){
        File recordExpDate_fp = new File (expDateProduct_dir + prod_exp_date + ".txt");

        try {
            if(!recordExpDate_fp.exists())
                recordExpDate_fp.createNewFile();
        } catch (Exception e) {
        }

        try (FileWriter fileWriter = new FileWriter(recordExpDate_fp, true)){
            fileWriter.write(prod_name + '\n');
            fileWriter.write(String.valueOf(prod_qty) + "\n\n");

            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void delProduct(int indexPosition){
        System.out.println(Main.marker);

        if(indexPosition == Main.marker)
            Main.my_inv[indexPosition] = null;
        else {
            for(int i=indexPosition; i<Main.marker; i++){
                Main.my_inv[indexPosition] = new Inventory();
                Main.my_inv[indexPosition] = Main.my_inv[indexPosition+1];
            }
            Main.my_inv[Main.marker] = null;
        }
        Main.marker--;
        System.out.println(Main.marker);
    }

    
    public static void delExpiredProduct(){
        String prod_name = "";
        int prod_qty = 0;
        double prod_profit_loss = 0.0;
        LocalDate currentDate = LocalDate.now();
        String date = currentDate.toString();
        String expDateProd_history = expDateProduct_dir + date + ".txt";
        String exp_history = expProductHistory_dir + date + ".txt";

        if(new File(expDateProd_history).exists()){

            try(BufferedReader reader = new BufferedReader(new FileReader(expDateProd_history))){
                while((data_line = reader.readLine()) != null){
                    prod_name = data_line;
                    prod_qty = Integer.parseInt(reader.readLine());
                    reader.readLine();

                    for(int i=0; i<=Main.marker; i++){
                        if(prod_name.equalsIgnoreCase(Main.my_inv[i].name)){
                            Main.my_inv[i].qty -= prod_qty;
                            Main.my_inv[i].total_price = Main.my_inv[i].qty * Main.my_inv[i].orig_price;
                            Main.my_inv[i].profit = Main.my_inv[i].total_sales_amount - Main.my_inv[i].total_price;
                            prod_profit_loss = prod_qty * Main.my_inv[i].orig_price;
                            Main.my_inv[i].profit -= prod_profit_loss;

                            if(Main.my_inv[i].qty == 0){
                                delProduct(i);
                                break;
                            }
                            else {
                                // update the data
                                Main.my_inv[i] = new Inventory(Main.my_inv[i].category, Main.my_inv[i].name, Main.my_inv[i].date, Main.my_inv[i].exp_date, Main.my_inv[i].orig_price, Main.my_inv[i].qty, Main.my_inv[i].retail_price);
                                break;
                            }
                        }
                    }
                    save();
            
                    // create file if not exist
                    File expRecord_fp = new File(exp_history);
                    try {
                        if(!expRecord_fp.exists())
                            expRecord_fp.createNewFile();
                    } catch (Exception e) {
                    }

                    // record the expired and deleted product
                    try(FileWriter fileWriter = new FileWriter(expRecord_fp, true)) {
                        time = DateManager.setTime();
                        fileWriter.write("Time recorded: " + String.valueOf(time) + '\n');
                        fileWriter.write("Product Name: " + prod_name + '\n');
                        fileWriter.write("Quantity: " + String.valueOf(prod_qty) + '\n');
                        fileWriter.write("Profit Loss: " + Double.valueOf(prod_profit_loss) + "\n\n");
                        fileWriter.close();
                    } catch (IOException e) {
                    }  
                }
                reader.close();

                new File(expDateProd_history).delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
