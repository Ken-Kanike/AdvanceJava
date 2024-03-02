import java.awt.*;
import java.applet.*;
import javax.swing.*;

/*
<applet code="prg4" width=400  height=400>
</applet>
*/
public class prg4 extends JApplet 
{
JButton b1,b2;
Container c;

			public void init()
			{
				c = getContentPane();
				c.setBackground(Color.YELLOW);

				b1 = new JButton("Yes");
			    b2 = new JButton("No");

				c.setLayout(new FlowLayout());

				c.add(b1);
				c.add(b2);
			}		
}
