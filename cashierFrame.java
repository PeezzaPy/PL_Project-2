import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.CardLayout;

public class  cashierFrame implements ActionListener, KeyListener {
    JFrame frame = new JFrame();
    JLabel header = new JLabel("Welcome, Cashier");
    JButton punchButton = new JButton("PUNCH");
    JButton logoutButton = new JButton("Logout");
    JPanel buttonPanel = new JPanel();
    JPanel punchPanel = new JPanel();
    JPanel cardsPanel = new JPanel();
    JTextField productName = new JTextField();
    CardLayout cardLayout = new CardLayout();
    JButton addProduct = new JButton("ADD");
    JLabel addProductLabel = new JLabel("Product Name");
    JLabel receiptHeader = new JLabel("Customer Receipt", SwingConstants.CENTER);
    JPanel receipt = new JPanel();
    JTextField productQuantity = new JTextField();
    JLabel getProductQuantity = new JLabel("Quantity");
    JLabel productPrice = new JLabel("PRICE");
    //JLabel p1 = new JLabel(""), p2 = new JLabel(""), p3 = new JLabel(""),p4 = new JLabel(""),p5 = new JLabel(""),p6 = new JLabel("");
    JLabel price = new JLabel("");
    JLabel[] p1 = new JLabel[10];
    JLabel totalPrice = new JLabel("TOTAL: ");
    JButton newCustomerButton = new JButton("New Customer");
    Receipt resibo = new Receipt();
    int position;
    public static int receiptMarker = -1;
    Inventory product = new Inventory(); //bago
    Receipt customerReceipt = new Receipt();
    int inventoryPos, receiptPos;
    static int totalMax = -1;
    static Receipt[] totalReceipt = new Receipt[Main.MAX_INV];


    public void cashier() {

        for (int i =0; i<10; i++){
            p1[i] = new JLabel("");
        }



        buttonPanel.setBorder(new EmptyBorder(5,25,5,25));
        buttonPanel.setLayout(new GridLayout(10,1,2,10));
        buttonPanel.setPreferredSize(new Dimension(150,0));
        buttonPanel.setBackground(Color.decode("#4267B2"));

        buttonPanel.add(Box.createRigidArea(null));
        buttonPanel.add(Box.createRigidArea(null));
        buttonPanel.add(Box.createRigidArea(null));
        buttonPanel.add(punchButton);
        buttonPanel.add(logoutButton);



        receipt.setBackground(Color.lightGray);
        receipt.setLayout(null);
        receipt.setBounds(600,30,300,620);




        receiptHeader.setBounds(650,70,200,30);
        receiptHeader.setLayout(null);
        receiptHeader.setVisible(true);

        //input fields
        productName.setBounds(180,150,300,50);
        addProductLabel.setBounds(20,150,300,50);
        addProductLabel.setFont(new Font("Montserrat", Font.BOLD,18));

        productQuantity.setBounds(180,220,300,50);
        getProductQuantity.setBounds(20,220,300,50);
        getProductQuantity.setFont(new Font("Montserrat", Font.BOLD,18));

        productPrice.setBounds(100,300,300,50);
        productPrice.setFont(new Font("Montserrat", Font.BOLD,26));

        totalPrice.setBounds(620,570,300,50);
        totalPrice.setFont(new Font("Montserrat", Font.BOLD,26));
        totalPrice.setLayout(null);

        addProduct.setBounds(265,400,70,50);

        productName.addKeyListener(this);
        productQuantity.addKeyListener(this);


        newCustomerButton.setLayout(null);
        newCustomerButton.setBackground(Color.lightGray);
        newCustomerButton.setBounds(230,530,150,50);





        punchPanel.setBackground(Color.decode("#4267B2"));
        punchPanel.setBounds(50,50,500,500); //try to remove
        punchPanel.setLayout(null);
        punchPanel.setVisible(true);
        punchPanel.setOpaque(true);
        punchPanel.add(header);
        punchPanel.add(productName);
        punchPanel.add(addProductLabel);
        punchPanel.add(receiptHeader);
        for(int i = 0; i<10; i++)
            punchPanel.add(p1[i]);
        punchPanel.add(totalPrice);
        punchPanel.add(receipt);
        punchPanel.add(getProductQuantity);
        punchPanel.add(productQuantity);
        punchPanel.add(productPrice);

        punchPanel.add(addProduct);
        punchPanel.add(newCustomerButton);


        cardsPanel.setLayout(cardLayout);

        cardsPanel.add(new JPanel(), "empty");
        cardsPanel.add(punchPanel, "punch");


        logoutButton.addActionListener(this);
        punchButton.addActionListener(this);
        addProduct.addActionListener(this);
        newCustomerButton.addActionListener(this);

        logoutButton.setBackground(Color.decode("#4267B2"));
        punchButton.setBackground(Color.decode("#4267B2"));
        logoutButton.setFocusable(false);
        punchButton.setFocusable(false);

        frame.add(header);
        frame.add(buttonPanel, BorderLayout.WEST);
        frame.add(cardsPanel,BorderLayout.CENTER);
        //frame.add(punchPunch, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Cashier");
        frame.setSize(1080,720);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == punchButton) {
            cardLayout.show(cardsPanel, "punch");
            System.out.println("Punch");  //trial
        }
        else if (e.getSource() == addProduct) {
            DataManager.save();
            double pprice = 0;
            //add to customer receipt
            System.out.println("adding..");
            addToReceipt(resibo);
            //String nn = Main.customerReceipt[0].productName; //trial
            //System.out.println(nn);

            //punchPanel.removeAll();
            //buttonPanel.removeAll();
            punchPanel.revalidate();
            punchPanel.repaint();
            buttonPanel.revalidate();
            buttonPanel.repaint();
            productName.setText("");
            productQuantity.setText("");
            price.setText("");

            for (int i = 0; i < receiptMarker; i++) {

                p1[i].setText(Main.customerReceipt[i].productName + "     " + Main.customerReceipt[i].quantity + "      " + Main.customerReceipt[i].price + "  " + Main.customerReceipt[i].totalPrice);

                int y = (i + 1) * 20;
                p1[i].setBounds(650, 70 + y, 200, 30);
                p1[i].setLayout(null);

                pprice = pprice + Main.customerReceipt[i].totalPrice;

                totalPrice.setText("TOTAL: " + pprice);
                punchPanel.revalidate();
                punchPanel.repaint();
                receipt.setBackground(Color.lightGray);
            }
        }
        else if (e.getSource() == logoutButton) {

            frame.dispose();
            Main.main(null);
        }
        else if (e.getSource() == newCustomerButton) {

            if (totalMax > -1) {
                for (int i = 0; i <= totalMax; i++) {
                    DataManager.recordSales(totalReceipt[i]);
                }
                DataManager.save();
            }
            for(int i=0; i<=receiptMarker; i++){

                Main.customerReceipt[i] = null;

                p1[i].setText(" " + "     " + "" + "      " + "" + "  " + "");

                int y = (i + 1) * 20;
                p1[i].setBounds(650, 70 + y, 200, 30);
                p1[i].setLayout(null);



                totalPrice.setText("TOTAL: ");
            }
            receiptMarker=-1;

            punchPanel.revalidate();
            punchPanel.repaint();
            cardLayout.show(cardsPanel,"empty");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER){

                String name = productName.getText();
                System.out.println(name);
                int qty = Integer.parseInt(productQuantity.getText());
                System.out.println(qty);

                customerReceipt.productName=name.toUpperCase();
                customerReceipt.quantity = qty;

                product.name = customerReceipt.productName;
                inventoryPos = Main.locateProduct(product);
                if(inventoryPos == -1){
                    System.out.println("\n\nPRODUCT DOES NOT EXIST\n\n");
                    Main.console.nextLine();
                    //ADD JOPTION PANE
                }
                else {
                    customerReceipt.price = Main.my_inv[inventoryPos].retail_price;
                    customerReceipt.totalPrice = customerReceipt.quantity * customerReceipt.price;

                    if(Main.customerReceipt[0] == null){
                        addToReceipt(customerReceipt);
                    }
                    else {
                        receiptPos = Main.locateProduct(customerReceipt);
                        if(receiptPos == -1)
                            addToReceipt(customerReceipt);
                        else {
                            Main.customerReceipt[receiptPos].quantity += customerReceipt.quantity;
                            Main.customerReceipt[receiptPos].totalPrice += customerReceipt.totalPrice;
                        }
                    }
                    Main.my_inv[inventoryPos].qty -= customerReceipt.quantity;
                    Main.my_inv[inventoryPos].sales_qty += customerReceipt.quantity;
                    Main.my_inv[inventoryPos].total_price = Main.my_inv[inventoryPos].qty * Main.my_inv[inventoryPos].orig_price;
                    Main.my_inv[inventoryPos].total_sales_amount += Main.my_inv[inventoryPos].retail_price * customerReceipt.quantity;
                    Main.my_inv[inventoryPos].profit += Main.my_inv[inventoryPos].retail_price * customerReceipt.quantity;

                    if(!customerReceipt.productName.equalsIgnoreCase("N/a")){
                        totalMax++;
                        totalReceipt[totalMax] = customerReceipt;
                    }

                        price.setText(String.valueOf(customerReceipt.totalPrice));
                        price.setBounds(250,300,300,50);
                        price.setFont(new Font("Montserrat", Font.BOLD,26));
                        punchPanel.add(price);


                        punchPanel.revalidate();
                        punchPanel.repaint();
                        //System.out.println(pp);

                }
            }

        }



    @Override
    public void keyReleased(KeyEvent e) {

    }

    static void addToReceipt(Receipt customerReceipt) {
        receiptMarker++;
        Main.customerReceipt[receiptMarker] = new Receipt(customerReceipt.productName, customerReceipt.quantity, customerReceipt.price, customerReceipt.totalPrice);


    }
}

