// pgno 4 xiii 1
import java.awt.*;
import java.applet.*;

/* <applet code="pr1xiii1" width="600" height="600"></applet> */
public class pr1xiii1 extends Applet 
{
Label l1;
Font f;


	public void init()
	{
		setBackground(Color.cyan);
		f = new Font("Arial",Font.BOLD,16);

		l1 = new Label("Welcome to Java"); l1.setFont(f); l1.setForeground(Color.RED);
	
		 add(l1);

	}

}