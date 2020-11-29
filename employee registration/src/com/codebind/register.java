package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class register extends JFrame {
    private JTextField nameText;
    private JTextField idText;
    private JTextField addressText;
    private JTextField telephoneText;
    private JTextField salaryInt;
    private JTextField dateText;
    private JButton clearButton;
    private JButton saveButton;
    private JButton editButton;
    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel title1;
    private JPanel title2;
    private JPanel listArea;
    private JLabel Name;
    private JLabel ID;
    private JLabel address;
    private JLabel telephone;
    private JLabel salary;
    private JCheckBox EPFETFCheckBox;
    private JList list1;

    register(){
        super("Employee Registration App");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    user User = new user(
                            nameText.getText(),
                            idText.getText(),
                            addressText.getText(),
                            Long.parseLong(telephoneText.getText()),
                            Double.parseDouble(salaryInt.getText()),
                            new SimpleDateFormat("dd/mm/yyyy").parse(dateText.getText())
                    );
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                refreshFile(nameText.getText(),
                        idText.getText(),
                        addressText.getText(),
                        telephoneText.getText(),
                        salaryInt.getText(),
                        dateText.getText()
                );
            }
        });

    }
    void refreshFile(String name, String id, String address, String telephone, String salary, String date){

    }
    void generateUserID(){

    }


    public static void main(String[] args) {
        register screen= new register();
        screen.setVisible(true);
    }
}
