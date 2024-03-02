
package ajpprexam_qbsoln;

import java.awt.*;
import java.awt.event.*;

public class Q4 extends Frame{
    
    public Q4(){
   
       add(new Button("North"),BorderLayout.NORTH);
       add(new Button("South"),BorderLayout.SOUTH);
       add(new Button("East"),BorderLayout.EAST);
       add(new Button("West"),BorderLayout.WEST);
       add(new Button("Center"),BorderLayout.CENTER);
       
       addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
  
    }

    public static void main(String[] args) {
     
    Q4 myFrame = new Q4();
    myFrame.setSize(300,300);
    myFrame.setVisible(true);
    myFrame.setLayout(new BorderLayout());
    myFrame.setTitle("Border Layout Example");
    myFrame. setBackground(Color.CYAN);
   
    }   
}


//        Button northButton = new Button("North");
//        Button southButton = new Button("South");
//        Button eastButton = new Button("East");
//        Button westButton = new Button("West");
//        Button centerButton = new Button("Center");
//
//        // Set background color for buttons
//        northButton.setBackground(Color.RED);
//        southButton.setBackground(Color.GREEN);
//        eastButton.setBackground(Color.BLUE);
//        westButton.setBackground(Color.YELLOW);
//        centerButton.setBackground(Color.ORANGE);
//
//        add(northButton, BorderLayout.NORTH);
//        add(southButton, BorderLayout.SOUTH);
//        add(eastButton, BorderLayout.EAST);
//        add(westButton, BorderLayout.WEST);
//        add(centerButton, BorderLayout.CENTER);
