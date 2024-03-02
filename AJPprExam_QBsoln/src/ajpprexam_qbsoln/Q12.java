
package ajpprexam_qbsoln;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
  <applet code="Q12" width="300" height="300" > </applet>
*/
public class Q12 extends Applet implements KeyListener {
String msg="";
int x=10 , y = 20;

    public void init() {
        setSize(300, 300);
        setBackground(new Color(0, 255, 255)); // CYAN color
        addKeyListener(this);
        requestFocus();
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        msg += e.getKeyChar();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        showStatus("Key Pressed!");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        showStatus("Key Released!");
    }

    @Override
    public void paint(Graphics g) {
      g.drawString(msg,x,y);
    }
    
    
}
