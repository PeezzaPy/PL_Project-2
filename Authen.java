import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;


public class Authen {
    static Scanner console = new Scanner(System.in);
    static String name, username, password, ename, eusern, epass; 
    static int key = 8;
    static String admin_fp, cashier_fp;


    static void inputValidation(){
        Main.validInput = false; 

        if(console.hasNextInt()){
            Main.choice = console.nextInt();
            Main.validInput = true;
            console.nextLine();
        }
        else {
            console.next();     // consume the invalid input
            Main.validInput = false;
        }
    }

    
    static int login(){    
        do {
            Terminal.clearScreen();
            System.out.println("=-=-= INVENTORY LOGIN  =-=-= \n");
            System.out.println("(1) Cashier");
            System.out.println("(2) Admin");
            System.out.println("(0) Exit \n");
            System.out.print("Select: ");
            Authen.inputValidation();  
        } while (!Main.validInput || Main.choice < 0 || Main.choice > 2);
        
        if(Main.choice == 0){
            Main.backToLogin = false;
            return 0;
        }

        Terminal.clearScreen();
        System.out.println("=-=-= LOG IN =-=-=-= \n");
        System.out.print("Enter username: ");
        username = console.nextLine();
        System.out.print("Enter password: ");
        password = console.nextLine();

        if(Main.choice == 1){          // cashier
            if(username.equals(Main.cashierAcc.getUsername())){
                if(password.equals(Main.cashierAcc.getPassword())){    
                    return 1;        // if found    
                }   
            }  
        }
        else if(Main.choice == 2){     // admin
            if(username.equals(Main.adminAcc.getUsername())){
                if(password.equals(Main.adminAcc.getPassword())){    
                    return 2;        // if found     
                }   
            }
        }
        return -1;               // status if not found 
    }

    
    

    public static void saveAccount(){      
        admin_fp = Security.encrypt(Security.getAdminFileName(), key);
        cashier_fp = Security.encrypt(Security.getCashierFileName(), key);

        // ADMIN ACCOUNT
        try (FileWriter writer = new FileWriter("C:/Users/ASUS/Desktop/PL_Project-2/account/" + admin_fp + ".txt")){
            if(Main.adminAcc.getName()  != "N/A"){
                ename = Security.encrypt(Main.adminAcc.getName(), key);
                eusern = Security.encrypt(Main.adminAcc.getUsername(), key); 
                epass = Security.encrypt(Main.adminAcc.getPassword(), key); 
                writer.write(ename + '\n');
                writer.write(eusern + '\n');
                writer.write(epass + '\n');
            }
            writer.close();       
        } catch (IOException e){
            e.printStackTrace();
        }
        
        // CASHIER ACCOUNT
        try (FileWriter writer = new FileWriter("C:/Users/ASUS/Desktop/PL_Project-2/account/" + cashier_fp + ".txt")){
            if(Main.cashierAcc.getName() != "N/A"){
                ename = Security.encrypt(Main.cashierAcc.getName(), key);
                eusern = Security.encrypt(Main.cashierAcc.getUsername(), key); 
                epass = Security.encrypt(Main.cashierAcc.getPassword(), key); 
                writer.write(ename + '\n');
                writer.write(eusern + '\n');
                writer.write(epass + '\n');
            } 
            writer.close();           
        } catch (IOException e){
            e.printStackTrace();
        } 
    }


    public static void retrieveAccount(){
        String data_line;
        admin_fp = Security.encrypt(Security.getAdminFileName(), key);
        cashier_fp = Security.encrypt(Security.getCashierFileName(), key);
        // ADMIN ACCOUNT
        try(BufferedReader reader = new BufferedReader(new FileReader("C:/Users/ASUS/Desktop/PL_Project-2/account/" + admin_fp + ".txt"))){
            while((data_line = reader.readLine()) != null){
                if(data_line != "N/A"){
                    ename = data_line;   
                    eusern = reader.readLine();
                    epass = reader.readLine();
                    name = Security.decrypt(ename, key);
                    username = Security.decrypt(eusern, key);
                    password = Security.decrypt(epass, key);
                    Main.adminAcc = new Account(name, username, password);    
                }
                else
                    break;
            } 
            reader.close();
        } catch (IOException e){
        }

        // CASHIER ACCOUNT
        try(BufferedReader reader = new BufferedReader(new FileReader("C:/Users/ASUS/Desktop/PL_Project-2/account/" + cashier_fp + ".txt"))){
            while((data_line = reader.readLine()) != null){
                if(data_line != "N/A"){
                    ename = data_line;   
                    eusern = reader.readLine();
                    epass = reader.readLine();
                    name = Security.decrypt(ename, key);
                    username = Security.decrypt(eusern, key);
                    password = Security.decrypt(epass, key);
                    Main.cashierAcc = new Account(name, username, password);
                }
                else 
                    break;
            }
            reader.close();
        } catch (IOException e){
        }
    }
}


