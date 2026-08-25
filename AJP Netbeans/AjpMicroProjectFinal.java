
// package javaapplication1;

import java.sql.*;
import java.util.*;

public class AjpMicroProjectFinal {
   
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
            
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","password123");
        System.out.println("Dtabase connected");
        Statement st = con.createStatement();
        Scanner sc = new Scanner(System.in);
        int ch =0;
               
        while(ch != 4)
        {
               
            System.out.println("Select Your Choice : ");
            System.out.println("1) :Create Table Emp");
            System.out.println("2) :Insert into Emp ");
            System.out.println("3) :View Records ");
            System.out.println("4) :Exit ");
            ch = sc.nextInt();
                switch(ch)
            {
                case 1: createTable(st);
                        break;
                    
                case 2:insertData(st);
                       break;

                case 3:showData(con);
                       break;

                case 4: System.out.println("\n\n Exiting.........");
                        break;
                        
                default :System.out.println("case default");
            }
                
            
        }
        
        
    }
     private static void createTable(Statement st) throws SQLException
     {
        String q1 = "CREATE TABLE `demo`.`emptable` (\n" +
                    "  `emp_id` INT NOT NULL,\n" +
                    "  `emp_name` VARCHAR(45) NULL,\n" +
                    "  `emp_salary` INT NULL,\n" +
                    "  PRIMARY KEY (`emp_id`));"; 
        boolean b = st.execute(q1);
        if(b== true)
             System.out.println("Table created");
        else 
             System.out.println("Error :Table not created");
        System.out.println("\n\n\n");  
     }
     
     private static void insertData(Statement st) throws SQLException
     {
        int id;
        String name;
        int sal;
        Scanner sc = new Scanner(System.in);
             System.out.println("Enter Employee ID :");
             id = sc.nextInt();
             System.out.println("Enter Employee Salary :");
             sal = sc.nextInt();
             System.out.println("Enter Employee Name :");
             name = sc.next();
             String q2 = "INSERT INTO `demo`.`emptable` (`emp_id`, `emp_name`, `emp_salary`) VALUES ('"+id+"', '"+name+"', '"+sal+"')";
             int i= st.executeUpdate(q2);
             if(i>0)
             System.out.println("Record inserted");
             else
             System.out.println("Error :Record inserted");
        System.out.println("\n\n\n");  
     }
     
     private static void showData(Connection con) throws SQLException
     {
            String q3 = "SELECT * FROM demo.emptable";
            PreparedStatement ps = con.prepareStatement(q3);
            ResultSet resultSet = ps.executeQuery(q3);
                 System.out.println("Employee Id  Employee Name  Employee Slaray");
                 while (resultSet.next()) {
                     
                     int column1Value = resultSet.getInt("emp_id");
                        String column2Value = resultSet.getString("emp_name");
                        int column3Value = resultSet.getInt("emp_salary");
                        System.out.println(column1Value + "\t\t" + column2Value +"\t\t" + column3Value);
            }
            System.out.println("\n\n\n");            
     }

}
           