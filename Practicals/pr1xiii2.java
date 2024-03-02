// pgno 4 xiii 2
import java.awt.*;
import java.applet.*;

/* <applet code="pr1xiii2" width="600" height="600"></applet> */
public class pr1xiii2 extends Applet 
{
List liLanguage;


	public void init()
	{
		setBackground(Color.cyan);
		
		 liLanguage = new List(3,true);

		 liLanguage.addItem("Marathi");
		 liLanguage.addItem("Hindi");
		 liLanguage.addItem("English");
		 liLanguage.addItem("Sanskrit");


		 add(liLanguage);

	}

}


