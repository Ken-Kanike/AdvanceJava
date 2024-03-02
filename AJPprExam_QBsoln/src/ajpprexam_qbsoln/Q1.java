
package ajpprexam_qbsoln;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/*
  <applet code="Q1" width="300" height="300" > </applet>
*/
public class Q1 extends Applet implements ItemListener{
    
CheckboxGroup rG1;
Checkbox rBtn1, rBtn2;
Checkbox cB1, cB2, cB3;
String msg="";

    public void init() {
        
        setSize(300, 300);
        setBackground(Color.CYAN);
        
        // Set up the checkboxes
        cB1 = new Checkbox("C++", false);
        cB2 = new Checkbox("Java", false);
        cB3 = new Checkbox("Python", false);

        
        
       // Set up the radio buttons
        rG1 = new CheckboxGroup();
        rBtn1 = new Checkbox("Male", rG1, false);
        rBtn2 = new Checkbox("Female", rG1, false);

     
        // Add item listeners to handle events
        rBtn1.addItemListener(this);
        rBtn2.addItemListener(this);
        cB1.addItemListener(this);
        cB2.addItemListener(this);
        cB3.addItemListener(this);

        // Add components to the applet
        add(new Label("Select languages:"));
        add(cB1);
        add(cB2);
        add(cB3);
        add(new Label("Select Gender:"));
        add(rBtn1);
        add(rBtn2);
       
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if(cB1.getState() && cB2.getState() && cB3.getState()){
            msg =  cB1.getLabel() +" , "+ cB2.getLabel()+" ,  "+ cB3.getLabel();
        }
        else if(cB1.getState() && cB2.getState()){
            msg =  cB1.getLabel() +" , "+ cB2.getLabel();
        }
        else  if(cB1.getState()  && cB3.getState()){
            msg =   cB1.getLabel() +" , "+ cB3.getLabel();
        }
        else if( cB2.getState() && cB3.getState()){
            msg = cB2.getLabel()+" , "+ cB3.getLabel();
        }
        else if(cB1.getState()){
            msg =  cB1.getLabel();
        }
        else if(cB2.getState()){
            msg =  cB2.getLabel();
        }
        else if(cB3.getState()){
            msg = cB3.getLabel();
        }
        else {
            msg = "";
        }
       
        
        if(rBtn1.getState() == true){
            showStatus("Gender selected: " + rBtn1.getLabel());
        }
        else if(rBtn2.getState() == true){
            showStatus("Gender selected: " + rBtn2.getLabel());
        }
        else {
           showStatus(" ");
        }
        
        
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.drawString("Subject Selected : " +  msg, 60, 150);
    }
    
    
    
    
}
