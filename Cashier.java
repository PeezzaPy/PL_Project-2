import java.util.Scanner;


public class Cashier {
    static Scanner console = new Scanner(System.in);
    public static int  mark = 0 ;

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
        Receipt resibo = new Receipt();
        int position, quantity;


        System.out.println("=-=-= PUNCH PRODUCT =-=-= \n");
        console.nextLine();
        System.out.print("Product name: ");
        String productName = console.nextLine();
        resibo.productName = productName.toUpperCase();
        System.out.print("Quantity: ");
        resibo.quantity = console.nextInt();

        //locate the product
        position = Main.locateProductforReceipt(resibo);

        //if it exists, minus the quantity, else invalid punch
        if(position == -1){
            System.out.println("Product does not exist/out of stock");
        }
        else{
            if(mark != 0 && resibo.productName.equals(Main.customerReceipt[position].productName)){
                Main.customerReceipt[position].quantity++;
                Main.customerReceipt[position].totalPrice=(Main.customerReceipt[position].quantity* Main.my_inv[position].retail_price);
                Main.my_inv[position].qty -= resibo.quantity;
            }
            else{
                //calculate the total price then save to resibo.totalPrice
                resibo.totalPrice=(resibo.quantity* Main.my_inv[position].retail_price);
                resibo.price=Main.my_inv[position].retail_price;
                Main.my_inv[position].qty -= resibo.quantity;
                //add to customer receipt
                addToReceipt(resibo);
            }

        }

        //question loop
        System.out.println("Add product?");
        System.out.println("[Y] Yes   [N] No");
        char choice = console.next().charAt(0);

        if (choice == 'y' || choice == 'Y'){
            Cashier.punch();
        }
        else{
            displayReceipt();
            Main.save();
            System.out.println("\n\nPress Enter to continue...");
            mark = 0; //reset the receipt
            console.nextLine();
            Cashier.cashier();
        }
    }

    static void addToReceipt(Receipt resibo){
        Main.customerReceipt[mark]= new Receipt(resibo.productName, resibo.quantity, resibo.price, resibo.totalPrice);
        mark++;

    }

    static void displayReceipt(){
        Terminal.clearScreen();
        for(int i=0; i<mark; i++){
            System.out.println("Product: " + Main.customerReceipt[i].productName);
            System.out.println("Quantity: " + Main.customerReceipt[i].quantity);
            System.out.println("Price: " + Main.customerReceipt[i].price);
            System.out.println("Amount: " + Main.customerReceipt[i].totalPrice);

        }
    }
}
