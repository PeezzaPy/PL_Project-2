import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame implements ActionListener{
    // set frame
    JFrame frame = new JFrame();
    JLabel header = new JLabel("Welcome, Admin");

    JButton addButton = new JButton("Add Product");
    JButton displayButton = new JButton("Display Products");
    JButton settingsButton = new JButton ("Settings");
    JButton logoutButton = new JButton("Logout");
    JButton addProductButton = new JButton("ADD PRODUCT");
    JPanel buttonPanel = new JPanel();
    JPanel cardsPanel = new JPanel();
    CardLayout cardLayout = new CardLayout();

    JPanel addProductPanel = new JPanel();
    JPanel displayProductPanel = new JPanel();
    JPanel settingsPanel = new JPanel();
    String[] choices = {"Choose Category","Canned Goods", "Dairy", "Drink", "Fruit", "Junk Food","Sweet","Vegetable"};
    JComboBox<String> categ;
    JTextField productName = new JTextField();
    JTextField origPrice = new JTextField();
    JFormattedTextField quantity = new JFormattedTextField();
    JTextField retailPrice = new JTextField();
    JButton settingsCashier = new JButton("CASHIER");
    JButton settingsAdmin = new JButton("ADMIN");
    JButton settingsKey = new JButton("ENCRYPTION KEY");
    JPanel settingsAdminPanel = new JPanel();
    JPanel settingsCashierPanel = new JPanel();
    JPanel settingsKeyPanel = new JPanel();
    JButton changeUsername = new JButton("CHANGE USERNAME");
    JButton changePassword = new JButton("CHANGE PASSWORD");
    JButton changeConfirm = new JButton("CONFIRM");
    JTextField newPassword = new JTextField();
    JTextField newUsername = new JTextField();
    JLabel enterNewUsername = new JLabel("Enter new username");
    JLabel enterNewPassword = new JLabel("Enter new password");
    JButton cancelChange = new JButton("BACK");
    JPanel changeUsernamePanel = new JPanel();
    JPanel changePasswordPanel = new JPanel();
    public void admin(){
        //TODO add panel for buttons and updates
        //error message
        //buttonPanel.add(addButton);
        //        buttonPanel.add(displayButton);
        //        buttonPanel.add(settingsButton);
        //        buttonPanel.add(logoutButton);
        //        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.Y_AXIS));
        //        buttonPanel.setPreferredSize(new Dimension(200,0));

        buttonPanel.setBorder(new EmptyBorder(5,25,5,25));
        buttonPanel.setLayout(new GridLayout(10,1,2,10));
        buttonPanel.setPreferredSize(new Dimension(200,0));

        buttonPanel.add(Box.createRigidArea(null));
        buttonPanel.add(Box.createRigidArea(null));
        buttonPanel.add(Box.createRigidArea(null));
        buttonPanel.add(addButton);
        buttonPanel.add(displayButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(logoutButton);

        //product name textfield
        JLabel productNameLabel = new JLabel("Product name");
        productNameLabel.setLayout(null);
        productNameLabel.setBounds(100,210,300,50);
        productNameLabel.setFont(new Font("Montserrat", Font.BOLD,18));
        productName.setBounds(250,210,200,40);
        productName.setLayout(null);

        //price textfield
        JLabel origPriceLabel = new JLabel("Price (each)");
        origPriceLabel.setLayout(null);
        origPriceLabel.setBounds(100,270,300,50);
        origPriceLabel.setFont(new Font("Montserrat", Font.BOLD,18));
        origPrice.setBounds(250,270,200,40);
        origPrice.setLayout(null);

        //Quantity textfield
        JLabel quantityLabel = new JLabel("Quantity");
        quantityLabel.setLayout(null);
        quantityLabel.setBounds(100,335,300,50);
        quantityLabel.setFont(new Font("Montserrat", Font.BOLD,18));
        quantity.setBounds(250,340,200,40);
        quantity.setLayout(null);

        //retail price textfield
        JLabel retailPriceLabel = new JLabel("Retail Price");
        retailPriceLabel.setLayout(null);
        retailPriceLabel.setBounds(100,405,300,50);
        retailPriceLabel.setFont(new Font("Montserrat", Font.BOLD,18));
        retailPrice.setBounds(250,410,200,40);
        retailPrice.setLayout(null);


        //addProductPanel
        addProductPanel.setBackground(Color.red);
        addProductPanel.setLayout(null);
        categ = new JComboBox<String>(choices);
        categ.setBounds(250,150,200,40);
        addProductPanel.add(categ);
        addProductPanel.add(addProductButton);
        addProductPanel.add(productName);
        addProductPanel.add(origPrice);
        addProductPanel.add(quantity);
        addProductPanel.add(retailPrice);
        addProductPanel.add(origPriceLabel);
        addProductPanel.add(productNameLabel);
        addProductPanel.add(quantityLabel);
        addProductPanel.add(retailPriceLabel);


        //add product button
        addProductButton.setLayout(null);
        addProductButton.setBackground(Color.lightGray);
        addProductButton.setBounds(275,480,150,50);







        //display panel
        displayProductPanel.setBackground(Color.green);
        displayProductPanel.setLayout(null);
        Object[][] data = new Object[100][11];
                // {{"Category","Product name", "Date/time",
                //"Expiration date","Original Price","Quantity",
                //"Total amount","Retail Price","Sales Quantity",
                //"Total sales amount", "Profit"}};


        String[] columnNames = {"Category","Product name", "Date/time",
                                "Expiration date","Original Price","Quantity",
                                "Total amount","Retail Price","Sales Quantity",
                                "Total sales amount", "Profit"};
        for(int i=0; i<=Main.marker;i++){
            //int temp =0;
            //data[row][column]
            //for(int j=0;j<10;i++){
                data[i][0]= Main.my_inv[i].category;
                data[i][1]= Main.my_inv[i].name;
                data[i][2]= Main.my_inv[i].date;
                data[i][3]= Main.my_inv[i].exp_date;
                data[i][4]= Main.my_inv[i].orig_price;
                data[i][5]= Main.my_inv[i].qty;
                data[i][6]= Main.my_inv[i].total_price;
                data[i][7]= Main.my_inv[i].retail_price;
                data[i][8]= Main.my_inv[i].sales_qty;
                data[i][9]= Main.my_inv[i].total_sales_amount;
                data[i][10]= Main.my_inv[i].profit;
            //temp++;

            //data[][i]={
            }




        JTable table = new JTable(data, columnNames);
        table.getTableHeader().setBounds(20,10,830,20);
        table.setBounds(20,30,830,200);
        table.setEnabled(false);

        displayProductPanel.add(table.getTableHeader());
        displayProductPanel.add(table);











        //settings panel
        settingsPanel.setBackground(Color.blue);
        settingsPanel.setLayout(null);
        settingsPanel.add(settingsCashier);
        settingsPanel.add(settingsAdmin);
        settingsPanel.add(settingsKey);

        //settings buttons
        settingsCashier.setLayout(null);
        settingsCashier.setBackground(Color.lightGray);
        settingsCashier.setBounds(150,480,150,50);

        settingsAdmin.setLayout(null);
        settingsAdmin.setBackground(Color.lightGray);
        settingsAdmin.setBounds(350,480,150,50);

        settingsKey.setLayout(null);
        settingsKey.setBackground(Color.lightGray);
        settingsKey.setBounds(550,480,150,50);

        //change password button
        changePassword.setLayout(null);
        changePassword.setBackground(Color.lightGray);
        changePassword.setBounds(500,480,150,50);


        //change username button
        changeUsername.setLayout(null);
        changeUsername.setBackground(Color.lightGray);
        changeUsername.setBounds(200,480,150,50);


        //confirm button
        changeConfirm.setLayout(null);
        changeConfirm.setBackground(Color.darkGray);
        changeConfirm.setBounds(350,480,150,50);


        //back button
        cancelChange.setLayout(null);
        cancelChange.setBackground(Color.darkGray);
        cancelChange.setBounds(350,530,150,50);


        //new password label
        enterNewPassword.setLayout(null);
        enterNewPassword.setBounds(120,325,300,50);
        enterNewPassword.setFont(new Font("Montserrat", Font.BOLD,18));


        //new password textfield
        newPassword.setLayout(null);
        newPassword.setBounds(350,335,200,30);


        //new username label
        enterNewUsername.setLayout(null);
        enterNewUsername.setBounds(120,325,300,50);
        enterNewUsername.setFont(new Font("Montserrat", Font.BOLD,18));


        //new username textfield
        newUsername.setLayout(null);
        newUsername.setBounds(350,335,200,30);


        //admin settings panel
        settingsAdminPanel.setLayout(null);
        settingsAdminPanel.setBackground(Color.darkGray);
        settingsAdminPanel.add(changeUsername);
        settingsAdminPanel.add(changePassword);
        //settingsAdminPanel.add(changeConfirm);
        //settingsAdminPanel.add(cancelChange);

        //cashier settings panel
        settingsCashierPanel.setLayout(null);
        settingsCashierPanel.setBackground(Color.lightGray);
        settingsCashierPanel.add(changeUsername);
        settingsCashierPanel.add(changePassword);

        //settingsCashierPanel.add(changeConfirm);
        //.add(cancelChange);



        //encryption key settings panel
        settingsKeyPanel.setLayout(null);
        settingsKeyPanel.setBackground(Color.PINK);

        //change password panel
        changePasswordPanel.setLayout(null);
        changePasswordPanel.setBackground(Color.yellow);
        changePasswordPanel.add(enterNewPassword);
        changePasswordPanel.add(newPassword);
        changePasswordPanel.add(changeConfirm);
        changePasswordPanel.add(cancelChange);


        //change username panel
        changeUsernamePanel.setLayout(null);
        changeUsernamePanel.setBackground(Color.white);
        changeUsernamePanel.add(enterNewUsername);
        changeUsernamePanel.add(newUsername);
        changeUsernamePanel.add(changeConfirm);
        changeUsernamePanel.add(cancelChange);




        cardsPanel.setLayout(cardLayout);

        cardsPanel.add(new JPanel(), "empty");
        cardsPanel.add(addProductPanel, "addProduct");
        cardsPanel.add(displayProductPanel,"displayProduct");
        cardsPanel.add(settingsPanel,"settings");
        cardsPanel.add(settingsAdminPanel, "adminSettings");
        cardsPanel.add(settingsCashierPanel, "cashierSettings");
        cardsPanel.add(settingsKeyPanel,"encryptionSettings");
        cardsPanel.add(changeUsernamePanel, "changeUsername");
        cardsPanel.add(changePasswordPanel, "changePassword");

        //button panel
        addButton.setBackground(Color.lightGray);
        displayButton.setBackground(Color.lightGray);
        settingsButton.setBackground(Color.lightGray);
        logoutButton.setBackground(Color.lightGray);


        //button listeners
        logoutButton.addActionListener(this);
        addButton.addActionListener(this);
        displayButton.addActionListener(this);
        settingsButton.addActionListener(this);
        addProductButton.addActionListener(this);
        settingsAdmin.addActionListener(this);
        settingsCashier.addActionListener(this);
        settingsKey.addActionListener(this);
        changeUsername.addActionListener(this);
        changePassword.addActionListener(this);
        changeConfirm.addActionListener(this);
        cancelChange.addActionListener(this);


        //frame.add(header);
        frame.add(buttonPanel,BorderLayout.WEST);
        frame.add(cardsPanel,BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Admin");
        frame.setSize(1080,720);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==logoutButton){

            //loginFrame.login();
            frame.dispose();
            //.main(null);
        }
        else if(e.getSource()==addButton){
            cardLayout.show(cardsPanel,"addProduct");
            //addProductPanel.revalidate();
            //addProductPanel.repaint();

        }
        else if(e.getSource()==displayButton){
            cardLayout.show(cardsPanel,"displayProduct");
            displayProductPanel.revalidate();
            displayProductPanel.repaint();
        }
        else if(e.getSource()==settingsButton){
            cardLayout.show(cardsPanel,"settings");
            settingsCashierPanel.validate();
            settingsCashierPanel.repaint();
            settingsAdminPanel.validate();
            settingsAdminPanel.repaint();

        }
        else if (e.getSource() == addProductButton) {
            int choose = categ.getSelectedIndex();
            System.out.println(choices[choose]);


            //clean the panel
            categ.setSelectedIndex(0);
            //addProductPanel.revalidate();
            //addProductPanel.repaint();
        }
        else if (e.getSource()==settingsAdmin){
            cardLayout.show(cardsPanel,"adminSettings");
            settingsCashierPanel.validate();
            settingsCashierPanel.repaint();
            settingsAdminPanel.validate();
            settingsAdminPanel.repaint();
        }
        else if(e.getSource()==settingsCashier){
            cardLayout.show(cardsPanel,"cashierSettings");
            settingsCashierPanel.validate();
            settingsCashierPanel.repaint();
            settingsAdminPanel.validate();
            settingsAdminPanel.repaint();
        }
        else if(e.getSource()==settingsKey){
            cardLayout.show(cardsPanel,"encryptionSettings");
        }
        else if(e.getSource()==changePassword){
            cardLayout.show(cardsPanel,"changePassword");

        }
        else if(e.getSource()==changeUsername){
            cardLayout.show(cardsPanel,"changeUsername");

        }
        else if(e.getSource()==changeConfirm){

        }
        else if(e.getSource()==cancelChange){
            /*
            settingsAdminPanel.validate();
            settingsAdminPanel.repaint();
            settingsCashierPanel.validate();
            settingsCashierPanel.repaint();
             */

            cardLayout.show(cardsPanel,"settings");
        }
    }
}
