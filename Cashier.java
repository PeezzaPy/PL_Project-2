import java.util.Scanner;


public class Cashier {
    static Receipt[] totalReceipt = new Receipt[Main.MAX_INV];
    static Scanner console = new Scanner(System.in);
    static int totalMax, receiptMarker;                                         // NADAGDAG UNG TOTAL MAX

    static void cashier() {
        Main.validInput = false;
    totalMax = -1;                                                              // NADAGDAG UNG TOTAL MAX
        receiptMarker = -1;

        do {
            do {
                Terminal.clearScreen();
                System.out.println("=-=-=-= CASHIER =-=-=-= \n");
                System.out.println("(1) New Customer");
                System.out.println("(0) Log out \n");
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


    static void punch(){                                                         // NAUPDATE UNG BUONG PUNCH FUNCTION                         
        Inventory product = new Inventory();
        Receipt receipt = new Receipt();
        int inventoryPos, receiptPos;

        Terminal.clearScreen();
        System.out.println("=-=-= PUNCH PRODUCT =-=-= \n");
        console.nextLine();
        System.out.print("Product name: ");
        String productName = console.nextLine();
        receipt.productName = productName.toUpperCase();
        System.out.print("Quantity: ");
        receipt.quantity = console.nextInt();
        
        // pass receipt name to inventory name variable;
        product.name = receipt.productName;
        inventoryPos = Main.locateProduct(product);
        if(inventoryPos == -1){
            System.out.println("\n\nPRODUCT DOES NOT EXIST\n\n");
            receipt = new Receipt();            // initialize again
            Main.console.nextLine();
        }
        else {
            if(Main.my_inv[inventoryPos].qty == 0 || Main.my_inv[inventoryPos].qty - receipt.quantity < 0){
                System.out.println("\n\nINSUFFICIENT AMOUNT\n");
                receipt = new Receipt();        // initialize again
                console.nextLine();
            }
            else {
                receipt.price = Main.my_inv[inventoryPos].retail_price;
                receipt.totalPrice = receipt.quantity * receipt.price;

                if(Main.customerReceipt[0] == null){
                    addToReceipt(receipt);
                }
                else {
                    receiptPos = Main.locateProduct(receipt);
                    if(receiptPos == -1)
                        addToReceipt(receipt);
                    else {
                        Main.customerReceipt[receiptPos].quantity += receipt.quantity;
                        Main.customerReceipt[receiptPos].totalPrice += receipt.totalPrice;
                    }
                }
                
                Main.my_inv[inventoryPos].qty -= receipt.quantity;
                Main.my_inv[inventoryPos].sales_qty += receipt.quantity;
                Main.my_inv[inventoryPos].total_price = Main.my_inv[inventoryPos].qty * Main.my_inv[inventoryPos].orig_price;
                Main.my_inv[inventoryPos].total_sales_amount += Main.my_inv[inventoryPos].retail_price * receipt.quantity;
                Main.my_inv[inventoryPos].profit += Main.my_inv[inventoryPos].retail_price * receipt.quantity;
            }
        }
        //question loop
        System.out.println("\n =-=-=-=-=-=-=-=-= \n");                 
        System.out.println("Add product?");
        System.out.println("[Y] Yes   [N] No");
        char choice = console.next().charAt(0);

        if(!receipt.productName.equalsIgnoreCase("N/a")){   
            totalMax++;                                                   
            totalReceipt[totalMax] = receipt;                             
        }

        if (choice == 'y' || choice == 'Y')
            Cashier.punch();
        else{
            if(totalMax > -1){                                            
                for(int i=0; i<=totalMax; i++)                            
                    DataManager.recordSales(totalReceipt[i]);             
                DataManager.save();                                       
                displayReceipt();                                         
            }
            initCustomerReceipt();                                             
            Cashier.cashier();                                          
        }
    }


    static void addToReceipt(Receipt resibo){
        receiptMarker++;
        Main.customerReceipt[receiptMarker] = new Receipt(resibo.productName, resibo.quantity, resibo.price, resibo.totalPrice);
    }


    static void displayReceipt(){                                              // NAUPDATE UNG BUONG DISPLAYRECEIPT FUNCTION
        Terminal.clearScreen();
        System.out.println("\n\n");
        for(int i=0; i<=receiptMarker; i++){
            System.out.println("Product: " + Main.customerReceipt[i].productName);
            System.out.println("Quantity: " + Main.customerReceipt[i].quantity);
            System.out.println("Price: " + Main.customerReceipt[i].price);
            System.out.println("Amount: " + Main.customerReceipt[i].totalPrice + '\n');
        }
        System.out.println("\n\nPress Enter to continue...");
        console.nextLine();
    }

    static void initCustomerReceipt(){                                         // NADAGDAG NA BAGONG FUNCTION
        for(int i=0; i<=receiptMarker; i++){
            Main.customerReceipt[i] = null;   
        }
    }
}
