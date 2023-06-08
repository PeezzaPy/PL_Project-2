import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


public class DataManager {

    public static void save(){
        File inventory_fp = new File("C:\\Users\\ASUS\\Desktop\\PL_Project-2\\product\\inventory.txt");
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
        String inventory_fp = "C:\\Users\\ASUS\\Desktop\\PL_Project-2\\product\\inventory.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(inventory_fp))){
            String data_line;
            
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
        File record_fp = new File ("C:\\Users\\ASUS\\Desktop\\PL_Project-2\\product\\product_history\\" + product.date + ".txt");    
        try {
            if(!record_fp.exists())
                record_fp.createNewFile();
        } catch (Exception e){
        }

        try(FileWriter fileWriter = new FileWriter(record_fp, true)){
            fileWriter.write(product.name + '\n');
            fileWriter.write(product.exp_date + '\n');
            fileWriter.write(String.valueOf(product.qty));
            fileWriter.write(' ');
            fileWriter.write(Double.toString(product.qty * product.orig_price) + "\n\n");
            fileWriter.close();
        } catch (IOException e) {
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
    }

    
    public static void delExpiredProduct(){
        // ...
    }
}
