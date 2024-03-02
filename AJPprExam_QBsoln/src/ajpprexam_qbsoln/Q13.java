
package ajpprexam_qbsoln;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
  <applet code="Q13" width="300" height="300" > </applet>
*/
public class Q13 extends Applet implements MouseListener{
    String msg ="";
    int mouseX = 3 , mouseY = 0;

    public void init() {
        setSize(600, 400);
        //setBackground(new Color(0, 255, 255)); // CYAN color
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseX = 0 ;
        mouseY = 10;
        msg ="Red";
        setBackground(Color.RED);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mouseX = 0 ;
        mouseY = 10;
        msg ="Cyan";
        setBackground(Color.CYAN);  
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mouseX = 0 ;
        mouseY = 10;
        msg ="White";
        setBackground(Color.WHITE);  
    }

    @Override
    public void paint(Graphics g) {
       g.drawString(msg, mouseX, mouseY);
    } 
    
}
