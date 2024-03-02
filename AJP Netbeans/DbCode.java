// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
//  */
// package AJPmicroproject;

import java.awt.*;

import java.awt.event.*;
import java.sql.*;

/**
 *
 * @author junai
 */
class MyFrame extends Frame  implements ActionListener
{
Label l1=new Label("enter id");
Label l2=new Label("enter name");
Label l3=new Label("enter salary");
TextField t1=new TextField();
    TextField t2=new TextField();
    TextField t3=new TextField();
        Button b12=new Button("Save");

    public MyFrame() throws HeadlessException {
        setLayout(new GridLayout(4,2));
        add(l1);add(t1);
        add(l2);add(t2);
        add(l3);add(t3);
        add(b12);
        b12.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","JUNAID@shaikh1240");
        System.out.println("Dtabase connected");
        String SQL="insert into demo.emptable values(?,?,?)";
            PreparedStatement pst=con.prepareStatement(SQL);
            pst.setInt(1, Integer.parseInt(t1.getText()));
            pst.setString(2, t2.getText());
            pst.setInt(3, Integer.parseInt(t3.getText()));
            pst.executeUpdate();
            System.out.println("inserted successfully");
            t1.setText("");t2.setText("");
        t3.setText("");
        
        }catch(Exception e1){}
    }

}  
        

public class DbCode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    MyFrame f=new MyFrame();
    f.setVisible(true);
    f.pack();
    }}
