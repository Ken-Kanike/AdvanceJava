import java.awt.*;
import java.applet.*;

/*
<applet code="prg1" width=400  height=400>
</applet>
*/
public class prg1 extends Applet 
{
Button b1,b2;

			public void init()
			{
				setBackground(Color.YELLOW);

				b1 = new Button("Yes");
			    b2 = new Button("No");

				setLayout(new FlowLayout());

				add(b1);
				add(b2);
			}		
}
