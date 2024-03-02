
package ajpprexam_qbsoln;

import java.applet.Applet;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/*
  <applet code="Q1" width="300" height="300" > </applet>
*/
public class Q2 extends Applet implements ActionListener{
Label l1,l2;
TextField t1;
TextArea t2;
Button b1;
String Name="" , Address="";

    public void init() {
       setSize(300, 300);
       setBackground(new Color(0, 255, 255)); // CYAN color
       setLayout(null);
        
        l1 = new Label("Name:");
        l1.setBounds(30, 30, 50, 20);
        add(l1);

        t1 = new TextField();
        t1.setBounds(90, 30, 150, 20);
        add(t1);

        l2 = new Label("Address:");
        l2.setBounds(30, 70, 50, 20);
        add(l2);

        t2 = new TextArea();
        t2.setBounds(90, 70, 150, 80);
        add(t2);

        b1 = new Button("Submit");
        b1.setBounds(120, 180, 60, 30);
        add(b1);

        b1.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
                showStatus("Details Submited!");
                Name = t1.getText();
                Address = t2.getText();
                repaint(); 
        }    
    }
    
    
       @Override
    public void paint(Graphics g) {
        g.drawString(" Name :" + Name, 30, 230);
        g.drawString(" Address :" + Address, 30, 250);
    }
}
