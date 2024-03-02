// pgno 4 xiii 3
import java.awt.*;
import java.applet.*;

/* <applet code="pr1xiii3" width="600" height="600"></applet> */
public class pr1xiii3 extends Applet 
{
Button b1,b2,b3;
Font f;


	public void init()
	{
		setBackground(Color.cyan);
		f = new Font("Arial",Font.BOLD,16);

		b1 = new Button("OK"); b1.setFont(f);
		b2 = new Button("RESET"); b2.setFont(f);
		b3 = new Button("CANCEL"); b3.setFont(f);

		

		 add(b1);
		 add(b2);
		 add(b3);


	}

}


