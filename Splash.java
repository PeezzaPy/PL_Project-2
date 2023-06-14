import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash {
    static JFrame frame = new JFrame();
    static JProgressBar bar = new JProgressBar();
    static JLabel header = new JLabel("Inventory System");
    static JLabel footer = new JLabel("Collecting Stock Packages...");
    static JLabel devs = new JLabel("Developed by: AMN");

    static ImageIcon logo = new ImageIcon("logo1.png");
    static JLabel logoLabel = new JLabel(logo);

     public static ImageIcon resizeImage(ImageIcon icon,int width, int height){
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width,height ,Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public static void ProgressBar(){
        frame.setTitle("Inventory System by: AMN");

        bar.setValue(0);
        bar.setBounds(0, 390, 484, 15);
        bar.setStringPainted(true);
        bar.setFont(new Font("Montserrat", Font.BOLD,14));
        bar.setForeground(Color.red);

        header.setBounds(80,40,400,50);
        header.setFont(new Font("Montserrat Extra-Bold",Font.BOLD,40));
        header.setForeground(Color.white);

        devs.setBounds(170,290,400,50);
        devs.setFont(new Font("Montserrat Extra-Bold",Font.BOLD,15));
        devs.setForeground(Color.white);

        footer.setBounds(160,410,400,50);
        footer.setFont(new Font("Montserrat Extra-Bold",Font.BOLD,12));
        footer.setForeground(Color.white);


        frame.add(devs);
        frame.add(bar);
        frame.add(logoLabel);
        frame.add(header);
        frame.add(footer);
        
        logoLabel.setBounds(140,90,200,200);
        logoLabel.setIcon(resizeImage(logo, 150, 150));
        logoLabel.setOpaque(false);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.decode("#4267B2"));

        fill();
        frame.dispose();
    }

    public static void fill(){
        int counter = 0;

        while(counter <=100){
            bar.setValue(counter);
            try{
                Thread.sleep(50);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            counter +=1;
        }
        bar.setString("Done!");
        try{
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
    }
}
}
