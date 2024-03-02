
package ajpprexam_qbsoln;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Q7 extends JFrame { 
    
    
   JComboBox stateComboBox;
   JLabel l1 ; 
   Container c;   
    public Q7(){
        c = getContentPane();
        c.setBackground(Color.CYAN);
        
        // Create an array of states
        String[] states = {"Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
                "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand",
                "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur",
                "Meghalaya", "Mizoram", "Nagaland", "Odisha", "Punjab",
                "Rajasthan", "Sikkim", "Tamil Nadu", "Telangana", "Tripura",
                "Uttar Pradesh", "Uttarakhand", "West Bengal"};

        stateComboBox = new JComboBox(states);

        // Set default selected state
        stateComboBox.setSelectedIndex(0);
        
        
         l1 = new JLabel();
 
        // Add action listener to handle state selection
        stateComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected state
                String selectedState = (String) stateComboBox.getSelectedItem();

                // Display the selected state in the console
                //System.out.println("Selected State: " + selectedState);
                l1.setText("Selected State : " + selectedState);
            }
        });

       c.add(stateComboBox);
       c.add(l1);
       
  
    }

    public static void main(String[] args) {
        Q7 myFrame = new Q7();
        myFrame.setSize(400, 400);
        myFrame.setTitle("JComboBox Example");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLayout(new FlowLayout());
        myFrame.setVisible(true);
    }
    
}
