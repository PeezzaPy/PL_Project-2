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
    JButton changeUsernameCashier = new JButton("CHANGE USERNAME");
    JButton changePasswordCashier = new JButton("CHANGE PASSWORD");
    JButton changeUsernameAdmin = new JButton("CHANGE USERNAME");
    JButton changePasswordAdmin = new JButton("CHANGE PASSWORD");
    JButton changeConfirmCashierPasswordPanel = new JButton("CONFIRM");
    JButton changeConfirmCashierUsernamePanel = new JButton("CONFIRM");
    JButton changeConfirmAdminPasswordPanel = new JButton("CONFIRM");
    JButton changeConfirmAdminUsernamePanel = new JButton("CONFIRM");
    JButton changeConfirmEncryptionPanel = new JButton("CONFIRM");
    JButton cancelChangeEncryptionPanel = new JButton("BACK");
    JTextField newPasswordCashier = new JTextField();
    JTextField newUsernameCashier = new JTextField();
    JLabel enterNewUsernameCashier = new JLabel("Enter new username");
    JLabel enterNewPasswordCashier = new JLabel("Enter new password");
    JLabel enterNewKey = new JLabel("Enter new key");
    JTextField newKey = new JTextField();
    JTextField newPasswordAdmin = new JTextField();
    JTextField newUsernameAdmin = new JTextField();
    JLabel enterNewUsernameAdmin = new JLabel("Enter new username");
    JLabel enterNewPasswordAdmin = new JLabel("Enter new password");
    JButton cancelChangeCashierPasswordPanel = new JButton("BACK");
    JButton cancelChangeCashierUsernamePanel = new JButton("BACK");
    JButton cancelChangeAdminPasswordPanel = new JButton("BACK");
    JButton cancelChangeAdminUsernamePanel = new JButton("BACK");
    JPanel changeUsernameCashierPanel = new JPanel();
    JPanel changePasswordCashierPanel = new JPanel();
    JPanel changeUsernameAdminPanel = new JPanel();
    JPanel changePasswordAdminPanel = new JPanel();
    Inventory product = new Inventory();
    Object[][] data = new Object[100][11];
    int pos;
    String usernameAdmin  ;
    String passwordAdmin ;
    String nameAdmin ;
    String usernameCashier;
    //String passwordCashier = "eyyy";
    String passwordCashier ;
    String nameCashier;
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

        String[] columnNames = {"Category","Product name", "Date/time",
                                "Expiration date","Original Price","Quantity",
                                "Total amount","Retail Price","Sales Quantity",
                                "Total sales amount", "Profit"};
        //input data for display
        refreshDisplayData();

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
        changePasswordCashier.setLayout(null);
        changePasswordCashier.setBackground(Color.lightGray);
        changePasswordCashier.setBounds(500,480,150,50);


        //change username button
        changeUsernameCashier.setLayout(null);
        changeUsernameCashier.setBackground(Color.lightGray);
        changeUsernameCashier.setBounds(200,480,150,50);

        //change password button
        changePasswordAdmin.setLayout(null);
        changePasswordAdmin.setBackground(Color.yellow);
        changePasswordAdmin.setBounds(500,480,150,50);


        //change username button
        changeUsernameAdmin.setLayout(null);
        changeUsernameAdmin.setBackground(Color.yellow);
        changeUsernameAdmin.setBounds(200,480,150,50);

        //confirm button in username cashier panel
        changeConfirmAdminUsernamePanel.setLayout(null);
        changeConfirmAdminUsernamePanel.setBackground(Color.darkGray);
        changeConfirmAdminUsernamePanel.setBounds(350,480,150,50);

        //confirm button in password cashier panel
        changeConfirmAdminPasswordPanel.setLayout(null);
        changeConfirmAdminPasswordPanel.setBackground(Color.darkGray);
        changeConfirmAdminPasswordPanel.setBounds(350,480,150,50);



        //confirm button in username cashier panel
        changeConfirmCashierUsernamePanel.setLayout(null);
        changeConfirmCashierUsernamePanel.setBackground(Color.darkGray);
        changeConfirmCashierUsernamePanel.setBounds(350,480,150,50);

        //confirm button in password cashier panel
        changeConfirmCashierPasswordPanel.setLayout(null);
        changeConfirmCashierPasswordPanel.setBackground(Color.darkGray);
        changeConfirmCashierPasswordPanel.setBounds(350,480,150,50);


        //back button
        cancelChangeCashierPasswordPanel.setLayout(null);
        cancelChangeCashierPasswordPanel.setBackground(Color.darkGray);
        cancelChangeCashierPasswordPanel.setBounds(350,530,150,50);

        cancelChangeCashierUsernamePanel.setLayout(null);
        cancelChangeCashierUsernamePanel.setBackground(Color.darkGray);
        cancelChangeCashierUsernamePanel.setBounds(350,530,150,50);

        //back button
        cancelChangeAdminPasswordPanel.setLayout(null);
        cancelChangeAdminPasswordPanel.setBackground(Color.darkGray);
        cancelChangeAdminPasswordPanel.setBounds(350,530,150,50);

        cancelChangeAdminUsernamePanel.setLayout(null);
        cancelChangeAdminUsernamePanel.setBackground(Color.darkGray);
        cancelChangeAdminUsernamePanel.setBounds(350,530,150,50);


        //new password label
        enterNewPasswordCashier.setLayout(null);
        enterNewPasswordCashier.setBounds(120,325,300,50);
        enterNewPasswordCashier.setFont(new Font("Montserrat", Font.BOLD,18));


        //new password textfield cashier
        newPasswordCashier.setLayout(null);
        newPasswordCashier.setBounds(350,335,200,30);


        //new username label cashier
        enterNewUsernameCashier.setLayout(null);
        enterNewUsernameCashier.setBounds(120,325,300,50);
        enterNewUsernameCashier.setFont(new Font("Montserrat", Font.BOLD,18));


        //new username textfield cashier
        newUsernameCashier.setLayout(null);
        newUsernameCashier.setBounds(350,335,200,30);

        //new password label admin
        enterNewPasswordAdmin.setLayout(null);
        enterNewPasswordAdmin.setBounds(120,325,300,50);
        enterNewPasswordAdmin.setFont(new Font("Montserrat", Font.BOLD,18));


        //new password textfield admin
        newPasswordAdmin.setLayout(null);
        newPasswordAdmin.setBounds(350,335,200,30);


        //new username label admin
        enterNewUsernameAdmin.setLayout(null);
        enterNewUsernameAdmin.setBounds(120,325,300,50);
        enterNewUsernameAdmin.setFont(new Font("Montserrat", Font.BOLD,18));


        //new username textfield admin
        newUsernameAdmin.setLayout(null);
        newUsernameAdmin.setBounds(350,335,200,30);


        //admin settings panel
        settingsAdminPanel.setLayout(null);
        settingsAdminPanel.setBackground(Color.darkGray);
        settingsAdminPanel.add(changeUsernameAdmin);
        settingsAdminPanel.add(changePasswordAdmin);
        //settingsAdminPanel.add(changeConfirm);
        //settingsAdminPanel.add(cancelChange);

        //cashier settings panel
        settingsCashierPanel.setLayout(null);
        settingsCashierPanel.setBackground(Color.lightGray);
        settingsCashierPanel.add(changeUsernameCashier);
        settingsCashierPanel.add(changePasswordCashier);

        //settingsCashierPanel.add(changeConfirm);
        //.add(cancelChange);



        //encryption key settings panel
        settingsKeyPanel.setLayout(null);
        settingsKeyPanel.setBackground(Color.PINK);
        settingsKeyPanel.add(cancelChangeEncryptionPanel);
        settingsKeyPanel.add(changeConfirmEncryptionPanel);
        settingsKeyPanel.add(enterNewKey);
        settingsKeyPanel.add(newKey);

        //label enter new key
        enterNewKey.setLayout(null);
        enterNewKey.setBounds(120,325,300,50);
        enterNewKey.setFont(new Font("Montserrat", Font.BOLD,18));

        //textfield
        newKey.setLayout(null);
        newKey.setBounds(350,335,200,30);

        //encryption key panel button config
        cancelChangeEncryptionPanel.setLayout(null);
        cancelChangeEncryptionPanel.setBackground(Color.darkGray);
        cancelChangeEncryptionPanel.setBounds(350,530,150,50);

        changeConfirmEncryptionPanel.setLayout(null);
        changeConfirmEncryptionPanel.setBackground(Color.darkGray);
        changeConfirmEncryptionPanel.setBounds(350,480,150,50);




        //change password panel cashier
        changePasswordCashierPanel.setLayout(null);
        changePasswordCashierPanel.setBackground(Color.yellow);
        changePasswordCashierPanel.add(enterNewPasswordCashier);
        changePasswordCashierPanel.add(newPasswordCashier);
        changePasswordCashierPanel.add(changeConfirmCashierPasswordPanel);
        changePasswordCashierPanel.add(cancelChangeCashierPasswordPanel);


        //change username panel cashier
        changeUsernameCashierPanel.setLayout(null);
        changeUsernameCashierPanel.setBackground(Color.white);
        changeUsernameCashierPanel.add(enterNewUsernameCashier);
        changeUsernameCashierPanel.add(newUsernameCashier);
        changeUsernameCashierPanel.add(changeConfirmCashierUsernamePanel);
        changeUsernameCashierPanel.add(cancelChangeCashierUsernamePanel);

        //change password panel admin
        changePasswordAdminPanel.setLayout(null);
        changePasswordAdminPanel.setBackground(Color.magenta);
        changePasswordAdminPanel.add(enterNewPasswordAdmin);
        changePasswordAdminPanel.add(newPasswordAdmin);
        changePasswordAdminPanel.add(changeConfirmAdminPasswordPanel);
        changePasswordAdminPanel.add(cancelChangeAdminPasswordPanel);


        //change username panel admin
        changeUsernameAdminPanel.setLayout(null);
        changeUsernameAdminPanel.setBackground(Color.pink);
        changeUsernameAdminPanel.add(enterNewUsernameAdmin);
        changeUsernameAdminPanel.add(newUsernameAdmin);
        changeUsernameAdminPanel.add(changeConfirmAdminUsernamePanel);
        changeUsernameAdminPanel.add(cancelChangeAdminUsernamePanel);




        cardsPanel.setLayout(cardLayout);

        cardsPanel.add(new JPanel(), "empty");
        cardsPanel.add(addProductPanel, "addProduct");
        cardsPanel.add(displayProductPanel,"displayProduct");
        cardsPanel.add(settingsPanel,"settings");
        cardsPanel.add(settingsAdminPanel, "adminSettings");
        cardsPanel.add(settingsCashierPanel, "cashierSettings");
        cardsPanel.add(settingsKeyPanel,"encryptionSettings");
        cardsPanel.add(changeUsernameCashierPanel, "changeCashierUsername");
        cardsPanel.add(changePasswordCashierPanel, "changeCashierPassword");
        cardsPanel.add(changeUsernameAdminPanel, "changeAdminUsername");
        cardsPanel.add(changePasswordAdminPanel, "changeAdminPassword");

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
        changeUsernameCashier.addActionListener(this);
        changePasswordCashier.addActionListener(this);
        changeUsernameAdmin.addActionListener(this);
        changePasswordAdmin.addActionListener(this);
        changeConfirmCashierPasswordPanel.addActionListener(this);
        changeConfirmCashierUsernamePanel.addActionListener(this);
        cancelChangeCashierPasswordPanel.addActionListener(this);
        cancelChangeCashierUsernamePanel.addActionListener(this); // no listenerr
        cancelChangeAdminPasswordPanel.addActionListener(this);
        cancelChangeAdminUsernamePanel.addActionListener(this);
        changeConfirmEncryptionPanel.addActionListener(this);
        cancelChangeEncryptionPanel.addActionListener(this);


        //frame.add(header);
        frame.add(buttonPanel,BorderLayout.WEST);
        frame.add(cardsPanel,BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Admin");
        frame.setSize(1080,720);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }

    //for display
     void refreshDisplayData() {
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
        displayProductPanel.revalidate();
        displayProductPanel.repaint();
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
        //the new cashier class AHAHAHAHAHAH
        else if (e.getSource() == addProductButton) {
            int productCategory = categ.getSelectedIndex();
            System.out.println(choices[productCategory]); //trial
            String category = choices[productCategory];
            System.out.println(category); //trial
            String name = productName.getText();
            double pprice = Double.parseDouble(origPrice.getText());
            int qquantity= Integer.parseInt(quantity.getText());
            double retail = Double.parseDouble(retailPrice.getText());

            //save to file
            product.category = category;
            product.name = name.toUpperCase();
            product.orig_price=pprice;
            product.qty=qquantity;
            product.retail_price=retail;

            product.total_price = product.qty * product.orig_price;
            product.date = DateManager.setDate();
            product.exp_date = DateManager.setGetExpirationDate(product.category);

            pos = Main.locateProduct(product);
            if(pos == -1){
                product.sales_qty = 0;
                product.total_sales_amount = 0.0;
                product.profit = product.total_price * -1;

                Admin.addProduct(product);
            }
            else{// if exist update the product
                Admin.updateProduct(product, pos);
            }


            // recording data to history
            DataManager.recordProduct(product);

            DataManager.save();

            refreshDisplayData();

            //clean the panel
            productName.setText("");
            origPrice.setText("");
            quantity.setText("");
            retailPrice.setText("");
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
        else if(e.getSource()==changePasswordCashier){
            cardLayout.show(cardsPanel,"changeCashierPassword");

        }
        else if(e.getSource()==changeUsernameCashier){
            cardLayout.show(cardsPanel,"changeCashierUsername");
            System.out.println("change cashierr username");

        }
        else if(e.getSource()==changePasswordAdmin){
            cardLayout.show(cardsPanel,"changeAdminPassword");
            System.out.println("admin password change");

        }
        else if(e.getSource()==changeUsernameAdmin){
            cardLayout.show(cardsPanel,"changeAdminUsername");
            System.out.println("Change admin username");
        }


        else if(e.getSource()==changeConfirmCashierPasswordPanel){
            String usernameCashier= Main.cashierAcc.getUsername();
            String passwordCashier = Main.cashierAcc.getPassword();
            String nameCashier= Main.cashierAcc.getName();

            passwordCashier = newPasswordCashier.getText();

            Main.cashierAcc = new Account(nameCashier, usernameCashier, passwordCashier);
            System.out.println("new: "+Main.cashierAcc.getPassword());

            Authen.saveAccount();
            newPasswordCashier.setText("");

            cardLayout.show(cardsPanel,"settings");
        }

        else if(e.getSource()==changeConfirmCashierUsernamePanel){
            String usernameCashier= Main.cashierAcc.getUsername();
            String passwordCashier = Main.cashierAcc.getPassword();
            String nameCashier= Main.cashierAcc.getName();

            usernameCashier = newUsernameCashier.getText();

            Main.cashierAcc = new Account(nameCashier, usernameCashier, passwordCashier);
            Authen.saveAccount();
            newUsernameCashier.setText("");

            cardLayout.show(cardsPanel,"settings");
        }

        else if(e.getSource()==changeConfirmAdminPasswordPanel){
            String usernameAdmin = Main.adminAcc.getUsername();
            String passwordAdmin = Main.adminAcc.getPassword();
            String nameAdmin = Main.adminAcc.getName();

            passwordAdmin = newPasswordAdmin.getText();

            Main.adminAcc = new Account(nameAdmin, usernameAdmin, passwordAdmin);

            newPasswordAdmin.setText("");
            Authen.saveAccount();
            cardLayout.show(cardsPanel,"settings");
        }

        else if(e.getSource()==changeConfirmAdminUsernamePanel) {
            String usernameAdmin = Main.adminAcc.getUsername();
            String passwordAdmin = Main.adminAcc.getPassword();
            String nameAdmin = Main.adminAcc.getName();
            usernameAdmin = newUsernameAdmin.getText();

            Main.adminAcc = new Account(nameAdmin, usernameAdmin, passwordAdmin);

            //put a confirmation pop up
            newUsernameAdmin.setText("");
            Authen.saveAccount();
            cardLayout.show(cardsPanel,"settings");
        }
        else if(e.getSource()==changeConfirmEncryptionPanel){
            int newEnryptionKey = Integer.parseInt(newKey.getText());

            String old_admin_fp = Security.encrypt(Security.getAdminFileName(), Security.getSecretKey());
            String old_cashier_fp = Security.encrypt(Security.getCashierFileName(), Security.getSecretKey());
            Security.changeSecretKey(newEnryptionKey);
            // store new ones
            String new_admin_fp = Security.encrypt(Security.getAdminFileName(), Security.getSecretKey());
            String new_cashier_fp = Security.encrypt(Security.getCashierFileName(), Security.getSecretKey());

            Security.renameFile(Authen.account_dir + old_admin_fp + ".txt", Authen.account_dir + new_admin_fp + ".txt");
            Security.renameFile(Authen.account_dir + old_cashier_fp + ".txt", Authen.account_dir + new_cashier_fp + ".txt");

            //System.out.println("bagong key: "+Security.getSecretKey());

            newUsernameAdmin.setText("");
            Authen.saveAccount();
            cardLayout.show(cardsPanel,"settings");

        }
        else if(e.getSource()==cancelChangeCashierPasswordPanel){
            cardLayout.show(cardsPanel,"settings");
        }
        else if(e.getSource()==cancelChangeCashierUsernamePanel){
            cardLayout.show(cardsPanel,"settings");
        }
        else if(e.getSource()==cancelChangeAdminPasswordPanel){
            cardLayout.show(cardsPanel,"settings");
        }
        else if(e.getSource()==cancelChangeAdminUsernamePanel){
            cardLayout.show(cardsPanel,"settings");
        }
        else if(e.getSource()==cancelChangeEncryptionPanel){
            cardLayout.show(cardsPanel,"settings");
        }
    }
}
