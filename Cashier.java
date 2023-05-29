import java.util.Scanner;


public class Cashier {
    static Scanner console = new Scanner(System.in);

    static void cashier() {
        Main.validInput = false;

        do {
            do {
                Terminal.clearScreen();
                System.out.println("=-=-=-= CASHIER =-=-=-=");
                System.out.println("(1) New Customer");
                System.out.println("(0) Log out");
                System.out.print("Select: ");
                
                if(console.hasNextInt()){
                    Main.choice = console.nextInt();
                    Main.validInput = true;
                }
                else {
                    console.next();     // consume the invalid input
                    Main.validInput = false;
                }
            } while (!Main.validInput);
        
            if (Main.choice == 1)
                Cashier.punch();
            else if (Main.choice == 0)
                Main.main(null);
            
        } while (Main.choice != 0);
    }


    static void punch(){
        
    }
}
