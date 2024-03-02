
package ajpprexam_qbsoln;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MyFrame15 extends JFrame{
    
JTextField usernameField;
JPasswordField passwordField;
Container c;

    MyFrame15(){
        c = getContentPane();
        setTitle("User Authentication");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        // Create layout
        setLayout(new GridLayout(3, 2, 5, 5)); // 5,5-pixel gaps

        // Add components to the frame
        c.add(usernameLabel);
        c. add(usernameField);
        c. add(passwordLabel);
        c.add(passwordField);
        
        c.add(new JLabel()); // Empty label for spacing
        c.add(loginButton);

        setLocationRelativeTo(null); // Center the frame on the screen
    }
    
    private void authenticateUser() {
        String username = usernameField.getText();
        char[] passwordChars = passwordField.getPassword();
        String password = new String(passwordChars);

        // For demonstration purposes, let's use a simple authentication check
        if (username.equals("user") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Authentication Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Authentication Failed. Please check your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Clear the password field after authentication attempt
        passwordField.setText("");
    }

    
}
public class Q15 {
    public static void main(String[] args) {
        MyFrame15 f = new MyFrame15();
        f.setVisible(true);
    }
    
}
