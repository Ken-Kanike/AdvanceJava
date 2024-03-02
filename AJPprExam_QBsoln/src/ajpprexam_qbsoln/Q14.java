
package ajpprexam_qbsoln;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/*
  <applet code="Q13" width="300" height="300" > </applet>
*/
public class Q14 extends Applet implements MouseMotionListener{
  
int mouseX, mouseY;
Color c;

    public void init() {
        setSize(600, 400);
        //setBackground(new Color(0, 255, 255)); // CYAN color
        addMouseMotionListener(this);
    }

    public void paint(Graphics g) {
        // Draw a circle at the current mouse position
        g.setColor(c);
        g.fillOval(mouseX - 10, mouseY - 10, 20, 20);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
         // Update mouse coordinates and color during dragging 
        mouseX = e.getX();
        mouseY = e.getY();
        c = Color.RED;
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
         // Update mouse coordinates and color during movement
        mouseX = e.getX();
        mouseY = e.getY();
        c = Color.BLUE;
        repaint();
    }
    public void update(Graphics g) {
        // Override the update method to prevent flickering
        paint(g);
    }

   
}
