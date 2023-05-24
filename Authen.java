import java.util.Scanner;


public class Authen {
    static Scanner console = new Scanner(System.in);
    static String name, username, password;

    static int login(int my_choice){     
        Terminal.clearScreen();
        System.out.println("=-=-= LOG IN =-=-=-= \n");
        System.out.print("Enter username: ");
        username = console.nextLine();
        System.out.print("Enter password: ");
        password = console.nextLine();

        if(my_choice == 1){          // cashier
            if(username.equals(Main.cashierAcc.getUsername())){
                if(password.equals(Main.cashierAcc.getPassword())){    
                    return 1;        // if found    
                }   
            }  
        }
        else if(my_choice == 2){     // admin
            if(username.equals(Main.adminAcc.getUsername())){
                if(password.equals(Main.adminAcc.getPassword())){    
                    return 2;        // if found     
                }   
            }
        }
        Terminal.clearScreen();
        System.out.println("INVALID USERNAME/PASSWORD \n");
        console.nextLine();
        return -1;               // status if not found   
    }
    

    
    static void signUp(int my_choice){
        String repassword;

        if(my_choice == 1 && Main.cashierAcc.getName() != "N/A" || my_choice == 2 && Main.adminAcc.getName() != "N/A"){
            Terminal.clearScreen();
            System.out.println("Account already registered \n");
            console.nextLine();
            return;
        }

        while(true){         
            Terminal.clearScreen();
            System.out.println("=-=-= SIGN UP =-=-=-= \n");
            System.out.print("Enter Name: ");
            name = console.nextLine();
            System.out.print("Create username: ");
            username = console.nextLine();
            System.out.print("Enter password: ");
            password = console.nextLine();
            System.out.print("Re-enter password: ");
            repassword = console.nextLine();         

            if(password.equals(repassword)){
                if(my_choice == 1){
                    Main.cashierAcc = new Account(name, username, password);     
                    break;
                }  
                else if(my_choice == 2){
                    Main.adminAcc = new Account(name, username, password);
                    break;
                } 
            }
            else {
                Terminal.clearScreen();
                System.out.println("Password does not match");
                console.nextLine();
            }
        }   
    }
}
