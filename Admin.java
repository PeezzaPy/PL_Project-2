import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Admin {
    static Scanner console = new Scanner(System.in);
    static String name, username, password;

    public static void admin(){
        Inventory product = new Inventory();

        do {
        //save_profit_history();
            Main.validInput = false;
            do {
                Terminal.clearScreen();
                System.out.println("=-=-= ADMIN INVENTORY =-=-= \n");
                System.out.println("(1) Add Product");
                System.out.println("(2) Display");
                System.out.println("(3) Settings");
                System.out.println("(0) Log out \n");
                System.out.print("Select: ");
                Authen.inputValidation();
            } while (!Main.validInput || Main.choice < 0 || Main.choice > 3);

            Terminal.clearScreen();
            if(Main.choice == 1){
                System.out.println("=-=-= ADD PRODUCT =-=-= \n");
                System.out.print("Product Name: ");
                console.nextLine();     // catching buffer
                product.product_name = console.nextLine();
                System.out.print("Price (each): ");
                product.orig_price = console.nextDouble();
                System.out.print("Quantity: ");
                product.qty = console.nextInt();
                System.out.print("Retail price (each): ");
                product.retail_price = console.nextDouble();    

                // Get the current date/time
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd / HH:mm:ss");
                product.date_time = now.format(formatter);

                addProduct(product);
                Main.save();
            }
            else if(Main.choice == 2)
                display();
            else if(Main.choice == 3)
                settings();
        } while (Main.choice != 0);
    } 




    static void addProduct(Inventory my_product){
        if(Main.isFull() == 1){
            Terminal.clearScreen();
            System.out.println("ARRAY IS FULL\n");
        }
        else {
            Main.marker++;
            Main.my_inv[Main.marker] = new Inventory(my_product.product_name, my_product.date_time, my_product.orig_price, my_product.qty, my_product.retail_price);
        }
    }   


    static void display(){
        Terminal.clearScreen();
        console.nextLine();     // clear buffer
        if(Main.marker == -1){
            System.out.println("INVENTORY IS EMPTY \n");
            console.nextLine();
            return;
        }
        
        for(int i=0; i<=Main.marker; i++){
            System.out.println("Product Name: " + Main.my_inv[i].product_name);
            System.out.println("Date/Time: " + Main.my_inv[i].date_time);
            System.out.println("Original Price: " + Main.my_inv[i].orig_price);
            System.out.println("Quantity: " + Main.my_inv[i].qty);
            System.out.println("Total Amount: " + Main.my_inv[i].total_price);
            System.out.println("Retail Price: " + Main.my_inv[i].retail_price);
            System.out.println("Sales Quantity: " + Main.my_inv[i].sales_qty);
            System.out.println("Total Sales Amount: " + Main.my_inv[i].total_sales_amount);
            System.out.println("Profit: " + Main.my_inv[i].profit);
            System.out.println("\n");
        }
        System.out.println("\n\nPress Enter to continue...");
        console.nextLine();
    }


    static void settings(){
        do {
            Main.validInput = false;
            do {
                Terminal.clearScreen();
                System.out.println("=-=-= SETTINGS =-=-= \n");
                System.out.println("(1) Cashier");
                System.out.println("(2) Admin");
                System.out.println("(0) Exit \n");
                System.out.print("Select: ");
                Authen.inputValidation();
            } while (!Main.validInput || Main.choice < 0 || Main.choice > 2);

            if(Main.choice == 1)
                cashierSettings();
            else if(Main.choice == 2)
                adminSettings();
        } while (Main.choice != 0);
    }


    static void adminSettings() throws IllegalArgumentException {
        name = Main.adminAcc.getName();
        username = Main.adminAcc.getUsername();
        password = Main.adminAcc.getPassword();

        do {
            Main.validInput = false;
            do {
                Terminal.clearScreen();
                System.out.println("=-=-= ADMIN SETTINGS =-=-= \n");
                System.out.println("(1) Change Name");
                System.out.println("(2) Change Username");
                System.out.println("(3) Change Password");
                System.out.println("(4) Change Encryption Key");
                System.out.println("(0) Exit \n");
                System.out.print("Select: ");
                Authen.inputValidation();
            } while (!Main.validInput || Main.choice < 0 || Main.choice > 2);

            Terminal.clearScreen();
            if(Main.choice == 1){
                System.out.println("Current name: " + Main.adminAcc.getName());
                System.out.print("New name: ");
                name = console.nextLine();
                if(name.equals(Main.adminAcc.getName()))
                    throw new IllegalArgumentException("New name cannot be the same as current name");
                
            }
            else if(Main.choice == 2){
                System.out.println("Current Username: " + Main.adminAcc.getUsername());
                System.out.print("New username: ");
                username = console.nextLine();
                if(username.equals(Main.adminAcc.getUsername()))
                    throw new IllegalArgumentException("New name cannot be the same as current name");
            }
            else if(Main.choice == 3){
                System.out.println("Current Password: " + Main.adminAcc.getPassword());
                System.out.print("New password: ");
                password = console.nextLine();
                if(password.equals(Main.adminAcc.getPassword()))
                    throw new IllegalArgumentException("New name cannot be the same as current name");
            }
            else if(Main.choice == 4){

            }
            // Update admin account data
            System.out.println("\n\nCHANGED SUCCESSFULLY");
            Main.adminAcc = new Account(name, username, password);
            Authen.saveAccount();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        } while (Main.choice != 0);
    }


    static void cashierSettings(){
        
    }
}
