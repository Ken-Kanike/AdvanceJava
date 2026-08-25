import java.sql.*;  // .Connection , .DriverManager , .Statement ;
//import javax.swing.JOptionPane;

public class CreateDB 
{
    public static void main(String[] args) 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306";

            String dbName ="db";
            String user = "root";
            String pass = "password123";

            Connection con = DriverManager.getConnection(url, user, pass);

            String sql = "CREATE DATABASE " + dbName;

            Statement s = con.createStatement();
            s.executeUpdate(sql);
            s.close();
            System.out.println("Database created successfully");
           // JOptionPane.showMessageDialog(null, "Database has been created .", "MySql", 0);
            
        }
        catch (Exception e) 
        {
           e.printStackTrace();
        }
    }
}
