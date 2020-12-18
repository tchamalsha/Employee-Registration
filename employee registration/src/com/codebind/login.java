package com.codebind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login extends JFrame {
    private JLabel title;
    private JTextField textField1;
    private JLabel usernameField;
    private JPasswordField textField2;
    private JLabel passwordField;
    private JPanel mainPanel1;
    public JButton loginButton;
    public static String userName;
    public static String password;


    login(){
        super("Vehicle app");
        this.setContentPane(mainPanel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName=textField1.getText();
                password=String.valueOf(textField2.getPassword());

                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                register entryScreen = new register();
                entryScreen.setSize(screenSize.width*7/8, screenSize.height*4/5);
                entryScreen.setVisible(true);

            }
        });
    }

    public static void main (String[] args) {
        login LoginForm=new login();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        LoginForm.setSize(screenSize.width*1/3, screenSize.height*1/3);
        LoginForm.setVisible(true);


    }


}
