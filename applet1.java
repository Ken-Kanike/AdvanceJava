import java.awt.*;
import java.applet.*;

/* 
 * <applet code="applet1" height="600" width="600"> </applet> 
 */
class applet1 extends Applet{

    Button b1,b2;

    public void init(){

        setBackground(Color.MAGENTA);

        b1 = new Button("Previous");
        b2 = new Button("Next");
        
        add(b1);
        add(b2);
    }
}