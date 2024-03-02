import java.awt.*;
import java.applet.*;
import javax.swing.*;

/*
<applet code="prg12" width=400  height=400>
</applet>
*/
public class prg12 extends JApplet 
{
JTabbedPane jtb;
Container c;

			public void init()
			{
				c = getContentPane();
				c.setBackground(Color.YELLOW);

				jtb = new JTabbedPane();
				jtb.addTab("Cities",new Button("YES"));
				jtb.addTab("Color",new Button("NO"));
				jtb.addTab("Taste",new Button("CANCEL"));
			    

				c.setLayout(new FlowLayout());

				c.add(jtb);
				
			}		
}
