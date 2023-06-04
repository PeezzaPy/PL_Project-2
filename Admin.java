import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Admin {
    static Scanner console = new Scanner(System.in);
    static String name, username, password;

    public static void admin(){
        Inventory product = new Inventory();
        int pos;

        do {
        //save_profit_history();
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
                String product_name = console.nextLine();
                product.product_name = product_name.toUpperCase();
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

                // locate if already exist or not
                pos = Main.locateProduct(product);
                if(pos == -1)
                    addProduct(product);
                else       // if exist update the product 
                    updateProduct(product, pos);
                
                Main.save();
            }
            else if(Main.choice == 2)
                display();
            else if(Main.choice == 3)
                settingsMenu();     
            
        } while (Main.choice != 0);
        
        if(Main.choice == 0);
            Main.backToLogin = true;
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


    static void updateProduct(Inventory my_product, int indexPos) {
        if((Main.my_inv[indexPos].qty + my_product.qty) > 50){
            System.out.println("QUANTITY LIMIT EXCEEDED");
            console.nextLine();
        }
        else
            Main.my_inv[indexPos] = new Inventory(my_product.product_name, my_product.date_time, my_product.orig_price, (Main.my_inv[indexPos].qty + my_product.qty), my_product.retail_price);
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


    static void settingsMenu(){
        do {
            do {
                Terminal.clearScreen();
                System.out.println("=-=-= SETTINGS =-=-= \n");
                System.out.println("(1) Cashier");
                System.out.println("(2) Admin");
                System.out.println("(3) Change Encryption Key");
                System.out.println("(0) Exit \n");
                System.out.print("Select: ");
                Authen.inputValidation();
            } while (!Main.validInput || Main.choice < 0 || Main.choice > 3);
            
            if(Main.choice != 0)
                settings(Main.choice);          // default to avoid duplicate exits  
        } while (Main.choice != 0);

        if(Main.choice == 0)
            Main.choice = -1;
    }


    static void settings(int my_choice){
        name = Main.adminAcc.getName();
        username = Main.adminAcc.getUsername();
        password = Main.adminAcc.getPassword();

        do {
            if(my_choice == 1 || my_choice == 2){
                do {
                    Terminal.clearScreen();
                    if(my_choice == 1)
                        System.out.println("=-=-= CASHIER SETTINGS =-=-= \n");
                    else if(my_choice == 2)
                        System.out.println("=-=-= ADMIN SETTINGS =-=-= \n");
                    // update info
                    System.out.println("(1) Change Name");
                    System.out.println("(2) Change Username");
                    System.out.println("(3) Change Password");
                    System.out.println("(0) Exit \n");
                    System.out.print("Select: ");
                    Authen.inputValidation();
                } while (!Main.validInput || Main.choice < 0 || Main.choice > 3);
            }

            if(Main.choice == 0){
                Main.choice = -1;
                return;
            }
            else {
                Terminal.clearScreen();
                if(my_choice == 1){                // cashier settings
                    if(Main.choice == 1){
                        System.out.println("Current name: " + Main.cashierAcc.getName());
                        System.out.print("New name: ");
                        name = console.nextLine();
                        if(name.equals(Main.cashierAcc.getName()))
                            throw new IllegalArgumentException("NEW NAME MUST NOT BE THE SAME AS CURRENT NAME");
                        else
                            Main.cashierAcc = new Account(name, username, password);
                    } 
                    else if(Main.choice == 2){
                        System.out.println("Current Username: " + Main.cashierAcc.getUsername());
                        System.out.print("New username: ");
                        username = console.nextLine();
                        if(username.equals(Main.cashierAcc.getUsername()))
                            throw new IllegalArgumentException("NEW USERNAME MUST NOT BE THE SAME AS CURRENT USERNAME");
                        else
                            Main.cashierAcc = new Account(name, username, password);
                    }
                    else if(Main.choice == 3){
                        System.out.println("Current Password: " + Main.cashierAcc.getPassword());
                        System.out.print("New password: ");
                        password = console.nextLine();
                        if(password.equals(Main.cashierAcc.getPassword()))
                            throw new IllegalArgumentException("NEW PASSWORD MUST NOT BE THE SAME AS CURRENT PASSWORD");
                        else
                            Main.cashierAcc = new Account(name, username, password);
                    }
                }
                else if(my_choice == 2){            // admin settings
                    if(Main.choice == 1){
                        System.out.println("Current name: " + Main.adminAcc.getName());
                        System.out.print("New name: ");
                        name = console.nextLine();
                        if(name.equals(Main.adminAcc.getName()))
                            throw new IllegalArgumentException("NEW NAME MUST NOT BE THE SAME AS CURRENT NAME");
                        else
                            Main.adminAcc = new Account(name, username, password);
                    } 
                    else if(Main.choice == 2){
                        System.out.println("Current Username: " + Main.adminAcc.getUsername());
                        System.out.print("New username: ");
                        username = console.nextLine();
                        if(username.equals(Main.adminAcc.getUsername()))
                            throw new IllegalArgumentException("NEW USERNAME MUST NOT BE THE SAME AS CURRENT USERNAME");
                        else
                            Main.adminAcc = new Account(name, username, password);
                    }
                    else if(Main.choice == 3){
                        String temp_password;
                        System.out.println("Current Password: " + Main.adminAcc.getPassword());
                        System.out.print("New password: ");
                        password = console.nextLine();
                        System.out.print("Re-enter password: ");
                        temp_password = console.nextLine();
                        if(password.equals(Main.adminAcc.getPassword()))
                            throw new IllegalArgumentException("NEW PASSWORD MUST NOT BE THE SAME AS CURRENT PASSWORD");
                        else
                            if(password.equals(temp_password))
                                Main.adminAcc = new Account(name, username, password);
                            else
                                throw new IllegalArgumentException("NEW PASSWORD DOES NOT MATCHED");
                    }
                }
                else if(my_choice == 3){
                    System.out.println("Current encryption key: " + Security.getSecretKey());
                    System.out.print("New encryption key: ");
                    int new_key = Integer.parseInt(console.nextLine());
                    if(new_key == Security.getSecretKey()){
                        System.out.println("New key must not be the same as current key");
                        console.nextLine();
                    }
                    else {
                        // store previous
                        String old_admin_fp = Security.encrypt(Security.getAdminFileName(), Security.getSecretKey());
                        String old_cashier_fp = Security.encrypt(Security.getCashierFileName(), Security.getSecretKey());
                        Security.changeSecretKey(new_key);
                        // store new ones
                        String new_admin_fp = Security.encrypt(Security.getAdminFileName(), Security.getSecretKey());
                        String new_cashier_fp = Security.encrypt(Security.getCashierFileName(), Security.getSecretKey());
                        
                        Security.renameFile("C:\\Users\\ASUS\\Desktop\\PL_Project-2\\account\\" + old_admin_fp + ".txt", "C:\\Users\\ASUS\\Desktop\\PL_Project-2\\account\\" + new_admin_fp + ".txt");
                        Security.renameFile("C:\\Users\\ASUS\\Desktop\\PL_Project-2\\account\\" + old_cashier_fp + ".txt", "C:\\Users\\ASUS\\Desktop\\PL_Project-2\\account\\" + new_cashier_fp + ".txt");
                        
                        Authen.saveAccount();
                    }
                }

                if(Main.choice != 0){
                    System.out.println("\n\nCHANGED SUCCESSFULLY");
                    Main.console.nextLine();
                    Authen.saveAccount();
                }

                if(my_choice == 3);
                    Main.choice = 0;
            }
        } while (Main.choice != 0);
    }
}
