package com.codebind;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private JList peopleList;
    private JButton deleteButton;
    private ArrayList<dbUser> userList;
    private DefaultListModel listPeople;


    register(){
        super("Employee Registration App");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        arrayRefresh();
        editButton.setEnabled(false);
        deleteButton.setEnabled(true);


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    user User1 = new user(
                            nameText.getText(),
                            idText.getText(),
                            addressText.getText(),
                            Long.parseLong(telephoneText.getText()),
                            Double.parseDouble(salaryInt.getText()),
                            new SimpleDateFormat("yyyy-mm-dd").parse(dateText.getText())
                    );
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                addToDatabase(nameText.getText(),
                        idText.getText(),
                        addressText.getText(),
                        telephoneText.getText(),
                        salaryInt.getText(),
                        dateText.getText()
                );

            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    user User = new user(
                            nameText.getText(),
                            idText.getText(),
                            addressText.getText(),
                            Long.parseLong(telephoneText.getText()),
                            Double.parseDouble(salaryInt.getText()),
                            new SimpleDateFormat("yyyy-mm-dd").parse(dateText.getText())
                    );
                    editDataBase(nameText.getText(),
                            idText.getText(),
                            addressText.getText(),
                            telephoneText.getText(),
                            salaryInt.getText(),
                            dateText.getText());

                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDataBase(idText.getText());
                clearFields();

            }
        });
        peopleList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                SimpleDateFormat date=new SimpleDateFormat("yyyy-mm-dd");
                int personIndex = peopleList.getSelectedIndex();
                if(personIndex>=0)
                {
                    dbUser u = userList.get(personIndex);
                    nameText.setText(u.getName());
                    idText.setText(u.getId());
                    addressText.setText(u.getAddress());
                    telephoneText.setText(Long.toString(u.getTelephone()));
                    salaryInt.setText(Double.toString(u.getSalary()));
                    dateText.setText(date.format(u.getDate()));
                    editButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                }
                else {
                    editButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                }
            }
        });
    }
    public static void main(String[] args) {
        register screen= new register();
        screen.setVisible(true);
    }
    void arrayRefresh(){
        ArrayList<dbUser> userList=new ArrayList<>();
        listPeople =new DefaultListModel();
        String url="jdbc:mysql://localhost:3306/LNT_Solutions";
        String user="root";
        String password="tharushi";
        try {
            Connection myConnection = DriverManager.getConnection(url,user,password);
            Statement myStatement = myConnection.createStatement();
            String sqlQuery = "Select * from employee";
            ResultSet resultSet= myStatement.executeQuery(sqlQuery);
            while (resultSet.next())
            {
                dbUser User = new dbUser(resultSet.getString("Name"),resultSet.getString("Id"),resultSet.getString("city"),Long.parseLong(resultSet.getString("telephone")),
                       resultSet.getDouble("salary"),resultSet.getDate("joined_date"));
                userList.add(User);
            }
            for (dbUser u:userList)
            {
                listPeople.addElement(u.getName());
            }
            peopleList.setModel(listPeople);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*void refreshUsers()
    {
        listPeople.removeAllElements();
        for(user u:userList)
        {
            listPeople.addElement(u.getName());
        }
    }*/
    void clearFields(){
        nameText.setText(null);
        idText.setText(null);
        addressText.setText(null);
        telephoneText.setText(null);
        salaryInt.setText(null);
        dateText.setText(null);
    }
    void addToDatabase (String name, String id, String address, String telephone, String salary, String date){
        sqlQuery( "Insert into employee"+"(Name,ID,City,Telephone,Salary,Joined_date)"+
                "values ("+'\''+name+'\''+','+'\''+id+'\''+','+'\''+address+'\''+','+'\''+telephone+'\''+','+salary+','+'\''+date+'\''+")");
        arrayRefresh();
    }
    void editDataBase(String name, String id, String address, String telephone, String salary, String date){
        sqlQuery("update employee set "+
                "name="+'\''+name+'\''+','+"ID="+'\''+id+'\''+','+"city="+'\''+address+'\''+','+"telephone="+'\''+telephone+'\''+','
                +"salary="+salary+','+"joined_date="+'\''+date+'\''+"where ID="+'\''+id+'\'');
        arrayRefresh();
    }
    void deleteDataBase(String id){
        sqlQuery( "delete from employee where id="+'\''+id+'\'');
        arrayRefresh();
    }
    void sqlQuery(String sqlQuery){
        String url="jdbc:mysql://localhost:3306/LNT_Solutions";
        String user="root";
        String password="tharushi";
        try {
            Connection myConnection = DriverManager.getConnection(url,user,password);
            Statement myStatement = myConnection.createStatement();
            myStatement.executeUpdate(sqlQuery);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}