
package ajpprexam_qbsoln;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class NewApplet extends Applet {

    @Override
    public void paint(Graphics g) {
       g.setColor(Color.red);
       g.drawString("Hello World", 100, 100);
    }
  
}
