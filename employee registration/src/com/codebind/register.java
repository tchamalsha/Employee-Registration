package com.codebind;
import java.awt.*;
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
    private static final ArrayList<user> userList=new ArrayList<>();
    private DefaultListModel listPeople;


    register(){
        super("Employee Registration App");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        listPeople = new DefaultListModel();
        arrayRefresh();
        refreshList();
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
                            new SimpleDateFormat("yyyy-mm-dd").parse(dateText.getText()),
                            EPFETFCheckBox.isSelected()
                    );
                    arrayRefresh();

                } catch (ParseException parseException) {
                    parseException.printStackTrace();

                }
                addToDatabase(nameText.getText(),
                        idText.getText(),
                        addressText.getText(),
                        telephoneText.getText(),
                        salaryInt.getText(),
                        dateText.getText(),
                        Boolean.toString(EPFETFCheckBox.isSelected())
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
                            new SimpleDateFormat("yyyy-mm-dd").parse(dateText.getText()),
                            EPFETFCheckBox.isSelected()
                    );
                    editDataBase(nameText.getText(),
                            idText.getText(),
                            addressText.getText(),
                            telephoneText.getText(),
                            salaryInt.getText(),
                            dateText.getText(),
                            Boolean.toString(EPFETFCheckBox.isSelected())
                    );

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
                    user u = userList.get(personIndex);
                    nameText.setText(u.getName());
                    idText.setText(u.getId());
                    addressText.setText(u.getAddress());
                    telephoneText.setText(Long.toString(u.getTelephone()));
                    salaryInt.setText(Double.toString(u.getSalary()));
                    dateText.setText(date.format(u.getDate()));
                    EPFETFCheckBox.setSelected(u.getEpf_etf());
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screen.setSize(screenSize.width*3/4, screenSize.height*3/4);
        screen.setVisible(true);

    }
    void arrayRefresh(){
        userList.removeAll(userList);
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
                user User = new user(resultSet.getString("Name"),resultSet.getString("Id"),resultSet.getString("city"),Long.parseLong(resultSet.getString("telephone")),
                       resultSet.getDouble("salary"),resultSet.getDate("joined_date"),Boolean.parseBoolean(resultSet.getString("EPF_ETF")));
                userList.add(User);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
   void refreshList()
    {
        listPeople.removeAllElements();
        for(user u:userList)
        {
            listPeople.addElement(u.getName());
        }
        peopleList.setModel(listPeople);

    }
    void clearFields(){
        nameText.setText(null);
        idText.setText(null);
        addressText.setText(null);
        telephoneText.setText(null);
        salaryInt.setText(null);
        dateText.setText(null);
        EPFETFCheckBox.setSelected(false);
        refreshList();
    }
    void addToDatabase (String name, String id, String address, String telephone, String salary, String date,String epf_etf){
        sqlQuery( "Insert into employee"+"(Name,ID,City,Telephone,Salary,Joined_date,EPF_ETF)"+
                "values ("+'\''+name+'\''+','+'\''+id+'\''+','+'\''+address+'\''+','+'\''+telephone+'\''+','+salary+','+'\''+date+'\''+','+'\''+epf_etf+'\''+")");
        arrayRefresh();
        refreshList();
    }
    void editDataBase(String name, String id, String address, String telephone, String salary, String date,String epf_etf){
        sqlQuery("update employee set "+
                "name="+'\''+name+'\''+','+"ID="+'\''+id+'\''+','+"city="+'\''+address+'\''+','+"telephone="+'\''+telephone+'\''+','
                +"salary="+salary+','+"joined_date="+'\''+date+'\''+','+"EPF_ETF="+'\''+epf_etf+'\''+"where ID="+'\''+id+'\'');
        arrayRefresh();
        refreshList();
    }
    void deleteDataBase(String id){
        sqlQuery( "delete from employee where id="+'\''+id+'\'');
        arrayRefresh();
        refreshList();
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
