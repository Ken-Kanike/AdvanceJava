import java.awt.*;
import java.applet.*;
import javax.swing.*;

/*
<applet code="prg13" width=400  height=400>
</applet>
*/
public class prg13 extends JApplet 
{
JTabbedPane jtb;
Container c;

			public void init()
			{
				c = getContentPane();
				c.setBackground(Color.YELLOW);

				jtb = new JTabbedPane();
				jtb.addTab("Cities",new Panel1());
				jtb.addTab("Color",new Panel2());
				jtb.addTab("Taste",new Panel3());
			    

				c.setLayout(new FlowLayout());

				c.add(jtb);
				
			}		
}
class Panel1 extends Panel
  {
	                   Panel1()
						   {
					         JButton b1=new JButton("VASAI");
							   JButton b2=new JButton("VIRAR");
							     JButton b3=new JButton("BORIVALI");
						   add(b1);
						   add(b2);
						   add(b3);
						   }
						   
}
class Panel2 extends Panel
  {
	                   Panel2()
						   {
					         JButton b1=new JButton("RED");
							   JButton b2=new JButton("BLUE");
							     
						   add(b1);
						   add(b2);
						   
						   }
						   
}

class Panel3 extends Panel
  {
	                   Panel3()
						   {
					         JButton b1=new JButton("CHOCOLATE");
							   JButton b2=new JButton("VANILLA");
							     JButton b3=new JButton("BUTTER");
						      add(b1);
						      add(b2);
						      add(b3);
						  }
						   
}