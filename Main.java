import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class Main {
    final static int MAX_INV = 100;
    public static int i, marker, choice, loginChoice;
    static boolean validInput, backToLogin;
    static Inventory[] my_inv = new Inventory[MAX_INV];
    static Receipt[] customerReceipt = new Receipt[MAX_INV];
    static Scanner console = new Scanner(System.in);
    static Account adminAcc = new Account();
    static Account cashierAcc = new Account();


    public static void main(String[] args){   
        init(); 
        Authen.retrieveAccount();
        retrieve();

        while(true){
            startMenu();
            do {
                switch(Authen.login()){
                    case 0: break;
                    case 1: Cashier.cashier();
                            break;
                    case 2: Admin.admin();
                            break;
                    
                    default: backToLogin = true;
                             System.out.println("\n\nINVALID USERNAME/PASSWORD \n");
                             console.nextLine();
                }
            } while(backToLogin == true);
        }   
    }

    public static void startMenu(){
        do {
            Terminal.clearScreen();
            System.out.println("=-=-= WELCOME BACK =-=-= \n");
            System.out.println("(1) Login");
            System.out.println("(0) Exit \n");
            System.out.print("Select: ");
            Authen.inputValidation();  
        } while (!validInput || choice < 0 || choice > 1);

        if(choice == 0){
            Terminal.clearScreen();
            System.exit(0);
        }
    }


    public static void init(){
        marker = -1;
    }

    public static int isFull(){
        if(marker == MAX_INV-1)
            return 1;
        else
            return 0;
    }

    public static int locateProduct(Inventory my_product){
        for(i=0; i<=marker; i++){
            if(my_inv[i].product_name.equalsIgnoreCase(my_product.product_name))
                return i;
        }
        return -1;
    }

    public static int locateProductforReceipt(Receipt my_product){
        for(i=0; i<=marker; i++){
            if(my_inv[i].product_name.equalsIgnoreCase(my_product.productName))
                return i;
        }
        return -1;
    }

    public static void save(){
        File prod_fp = new File("C:\\Users\\ASUS\\Desktop\\PL_Project-2\\product\\inventory.txt");
        try {
            if(!prod_fp.exists())  
                prod_fp.createNewFile();
        } catch (IOException e){
        }

        String inventory_fp = "C:\\Users\\ASUS\\Desktop\\PL_Project-2\\product\\inventory.txt";
        try (FileWriter writer = new FileWriter(inventory_fp)){
            for(Inventory product : my_inv){
                if(product != null){
                    writer.write(product.category + '\n');

                    writer.write(product.date_time + '\n');

                    writer.write(product.product_name + '\n');

                    writer.write(product.exp_date_time + '\n');

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


    private static void retrieve(){
        Inventory my_product = new Inventory();
        String inventory_fp = "C:\\Users\\ASUS\\Desktop\\PL_Project-2\\product\\inventory.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(inventory_fp))){
            String data_line;
            
            while((data_line = reader.readLine()) != null){
                my_product.category = data_line;
                my_product.date_time = reader.readLine();
                my_product.product_name = reader.readLine();
                my_product.exp_date_time = reader.readLine();

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
}