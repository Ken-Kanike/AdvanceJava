
package ajpprexam_qbsoln;

import java.awt.*;

public class Q5 extends Frame{
    
    public Q5(){
        
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(grid);
        
        gbc.fill =GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy =0;
        this.add(new Button("Button One"),gbc);
        gbc.gridx= 1; 
        gbc.gridy = 0; 
        this.add(new Button("Button two"), gbc); 
        gbc. fill =GridBagConstraints.HORIZONTAL; 
        gbc.ipady = 20; 
        gbc.gridx = 0; 
        gbc.gridy = 1; 
        this.add(new Button("Button Three"), gbc); 
        gbc.gridx = 1; 
        gbc.gridy = 1; 
        this.add(new Button("Button Four"), gbc);
        gbc.gridx = 0; 
        gbc.gridy = 2; 
        gbc.fill=GridBagConstraints.HORIZONTAL; 
        gbc.gridwidth = 2; 
        this.add(new Button("Button Five"), gbc);
        
        setPreferredSize(getSize());
  
    }

    public static void main(String[] args) {
     
    Q5 myFrame = new Q5();
    myFrame.setSize(400,400);
    myFrame.setVisible(true);
    myFrame.setTitle("GridBagLayout Example");
    myFrame. setBackground(Color.CYAN);
   
    }   
}
