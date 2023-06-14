import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class loginFrame {

    JFrame frame = new JFrame();

    static OvalButton loginButton = new OvalButton("Login");
    static OvalButton resetButton = new OvalButton("Reset");

    static JLabel userLabel = new JLabel("Username");
    static JLabel passwordLabel = new JLabel("Password");

    static JLabel header1 = new JLabel("Inventory System");
    static JLabel header2 = new  JLabel("Login");

    static JTextField userField = new JTextField();
    static JPasswordField passField = new JPasswordField();

    static String uname = null;
    static String pass = null;


    static ImageIcon logo = new ImageIcon("logo1.png");
    static JLabel logoLabel = new JLabel(logo);

    public static ImageIcon resizeImage(ImageIcon icon,int width, int height){
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width,height ,Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
    

    public static int login() {

         JFrame frame = new JFrame();


        frame.setTitle("Inventory System Login");

        header1.setText("Inventory System");
        header1.setBounds(120,10,400,50);
        header1.setFont(new Font("Montserrat Extra Bold",Font.BOLD,30));
        header1.setForeground(Color.white);


        header2.setText("Login");
        header2.setBounds(200,60,100,50);
        header2.setFont(new Font("Montserrat Extra Bold",Font.BOLD,30));
        header2.setForeground(Color.white);

        userLabel.setText("Username");
        userLabel.setBounds(75,275,100,25);
        userLabel.setFont(new Font("Montserrat Extra Bold",Font.BOLD,18));
        userLabel.setForeground(Color.white);

        passwordLabel.setText("Password");
        passwordLabel.setBounds(75,320,100,25);
        passwordLabel.setFont(new Font("Montserrat Extra Bold",Font.BOLD,18));
        passwordLabel.setForeground(Color.white);

        userField.setBounds(199,275,200,25);
        passField.setBounds(199,320,200,25);

        loginButton.setBounds(300,370,100,40);
        loginButton.setFocusable(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder());
        loginButton.setFont(new Font("Montserrat Extra Bold",Font.BOLD,18));
        //loginButton.addActionListener(this);
        
        resetButton.setBounds(75,370,100,40);
        resetButton.setFocusable(false);
        resetButton.setFont(new Font("Montserrat Extra Bold",Font.BOLD,18));
        //resetButton.addActionListener(this);

        //logoLabel.setBounds(50,80,100,50);
        logoLabel.setBounds(140,90,200,200);
        logoLabel.setIcon(resizeImage(logo, 150, 150));
        logoLabel.setOpaque(false);
        
        frame.add(header1);
        frame.add(header2);
        frame.add(userLabel);
        frame.add(passwordLabel);
        frame.add(passField);
        frame.add(userField);
        frame.add(loginButton);
        frame.add(resetButton);
        frame.add(logoLabel);
        frame.getContentPane().setBackground(Color.decode("#4267B2"));
        frame.setResizable(false);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        /* 
        JButton button = new JButton("LOGIN");
        //button.setBounds(200,200,100,100); //button layout
        JFrame frame = new JFrame();


            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new FlowLayout());
            frame.add(button);

            //username
            JTextField username = new JTextField();
            username.setPreferredSize(new Dimension(400, 80));

            //password
            JTextField password = new JTextField();
            password.setPreferredSize(new Dimension(400, 80));
            frame.add(username);
            frame.add(password);
            frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.setTitle("Inventory System");

        ImageIcon logo = new ImageIcon("logo.jpg");
        frame.setIconImage(logo.getImage());
        */
        while (uname == null) {
        //button
            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource() == loginButton) {
                        uname = userField.getText();
                        pass = String.valueOf(passField.getPassword());
                        frame.dispose();
                    }
                    
                }
            });

            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    userField.setText("");
                    passField.setText("");
                }
            });
        }

        if (uname.equals(Main.cashierAcc.getUsername())) {
            if (pass.equals(Main.cashierAcc.getPassword())) {
                return 1;        // if found
            }
        } else if (uname.equals(Main.adminAcc.getUsername())) {
            if (pass.equals(Main.adminAcc.getPassword())) {
                return 2;        // if found
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Login", "Error", JOptionPane.ERROR_MESSAGE);
                return -1;
            }
        }
        return -1;           // not found
    }
}

