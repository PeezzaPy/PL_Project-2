import java.util.Scanner;

import javax.swing.ToolTipManager;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Main {
    final static int MAX_INV = 100;
    static int pos, marker, choice;
    static boolean validInput;
    static Inventory[] my_inv = new Inventory[MAX_INV]; 
    static Scanner console = new Scanner(System.in);
    static Account adminAcc = new Account();
    static Account cashierAcc = new Account();


    public static void main(String[] args){   
        boolean isRepeat;

        init(); 
        retrieve();
        while(true){
            startMenu();
            do {
                switch(Authen.login(choice)){
                    case 1: cashier();
                            isRepeat = false; 
                            break;
                    case 2: Admin.admin();
                            isRepeat = false; 
                            break;
                    
                    default: isRepeat = false; 
                }
            } while(isRepeat == true);
        }   
    }


    public static void startMenu(){
        boolean status;

        do {
            do {
                status = validInput = false;

                Terminal.clearScreen();
                System.out.println("=-=-= WELCOME BACK =-=-= \n");
                System.out.println("(1) Login");
                System.out.println("(2) Sign up");
                System.out.println("(0) Exit \n");
                System.out.print("Select: ");
                
                if(console.hasNextInt()){
                    choice = console.nextInt();
                    validInput = true;
                }
                else {
                    console.next();     // consume the invalid input
                    validInput = false;
                }
            } while (!validInput || choice < 0 || choice > 2);
            Terminal.clearScreen();

            if(choice == 1 || choice == 2){  
                int authen_choice = choice;
                validInput = false;     
                do {
                    Terminal.clearScreen();
                    if(choice == 1)
                        System.out.println("=-=-= INVENTORY LOGIN  =-=-= \n");
                    else if(choice == 2)
                        System.out.println("=-=-= INVENTORY SIGN UP  =-=-= \n");
                    System.out.println("(1) Cashier");
                    System.out.println("(2) Admin");
                    System.out.println("(0) Exit \n");
                    System.out.print("Select: ");
                    
                    if(console.hasNextInt()){
                        choice = console.nextInt();
                        validInput = true;
                    }
                    else {
                        console.next();     // consume the invalid input
                        validInput = false;
                    }   
                } while (!validInput || choice < 0 || choice > 2);
                
                if(choice == 1 || choice == 2){
                    if(authen_choice == 1)
                        status = true;
                    else if(authen_choice == 2)
                        Authen.signUp(choice);
                } 
            }
            else
                System.exit(0);
        } while(status == false || choice == 0);
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


    private static void cashier(){
        validInput = false;

        do {
            do {
                Terminal.clearScreen();
                System.out.println("=-=-=-= CASHIER =-=-=-=");
                System.out.println("(1) New Customer");
                System.out.println("(0) Log out");
                System.out.print("Select: ");
                
                if(console.hasNextInt()){
                    choice = console.nextInt();
                    validInput = true;
                }
                else {
                    console.next();     // consume the invalid input
                    validInput = false;
                }
            } while (!validInput);
        
            if (choice == 1)
                punch();
            else if (choice == 0)
                main(null);
            
        } while (choice != 0);
    }
        

    private static void punch(){
        Terminal.clearScreen();
        System.out.print("PUNCH FUNCTION");
    }


    public static void save(){
        String inventory_fp = "C:\\Users\\ASUS\\Desktop\\PL_Project-2\\item\\inventory.txt";
        try (FileWriter writer = new FileWriter(inventory_fp)){
            for(Inventory product : my_inv){
                if(product != null){
                    writer.write(product.date_time + '\n');

                    writer.write(product.product_name + '\n');

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void retrieve(){
        Inventory my_product = new Inventory();
        String inventory_fp = "C:\\Users\\ASUS\\Desktop\\PL_Project-2\\item\\inventory.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(inventory_fp))){
            String data_line;
            
            while((data_line = reader.readLine()) != null){
                my_product.date_time = data_line;
                my_product.product_name = reader.readLine();

                String line = reader.readLine();
                if(line != null){
                    String[] product_data = line.split(" ");
                    my_product.orig_price = Double.parseDouble(product_data[0]);
                    my_product.qty = Integer.parseInt(product_data[1]);
                    my_product.total_price = Double.parseDouble(product_data[2]);
                    my_product.retail_price = Double.parseDouble(product_data[3]);
                    my_product.sales_qty = Integer.parseInt(product_data[4]);
                    my_product.total_sales_amount = Double.parseDouble(product_data[5]);
                    my_product.profit = Double.parseDouble(product_data[6]);

                    Admin.addProduct(my_product);
                } 
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }


    }
}