// pgno7 x 1
import java.awt.*;
import java.applet.*;

/* <applet code="pr2x1" width="600" height="600"></applet> */
public class pr2x1 extends Applet 
{
List liSeasons;


	public void init()
	{
		setBackground(Color.cyan);
		
		liSeasons = new List(3);

		liSeasons.addItem("Summer");
		liSeasons.addItem("Winter");
		liSeasons.addItem("Rainy");

		 add(liSeasons);

	}

}


