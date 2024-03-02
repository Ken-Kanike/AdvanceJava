
package ajpprexam_qbsoln;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Q18 {

    public static void main(String[] args)  {               // throws ClassNotFoundException, SQLException 
        
        String q1 = "CREATE TABLE `demo`.`employee` ( `emp_id` INT NOT NULL, `emp_name` VARCHAR(45) NULL , PRIMARY KEY (`emp_id`));";
       
        try {
                    Class.forName("com.mysql.cj.jdbc.Driver");   // throws ClassNotFoundException
                    System.out.println("Driver loaded");
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","JUNAID@shaikh1240");  // throws  SQLException
                    System.out.println("Dtabase connected");
                    Statement st = con.createStatement();
                    System.out.println("statement created");

                    boolean result  = st.execute(q1);

                    if(result){

                          System.out.println("Error :Table not created");  
                    }
                    else {
                          System.out.println("Table created");
                    }  
                    
        } 
        catch (Exception e) {
            
           e.printStackTrace();
           
        }
        
    }
    
}
      