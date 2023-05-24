import java.util.Scanner;

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
                    
                    default: isRepeat = true; 
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
}