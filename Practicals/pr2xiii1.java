// pgno10 xii 1
import java.awt.*;
import java.applet.*;

/* <applet code="pr2xiii1" width="600" height="600"></applet> */
public class pr2xiii1 extends Applet 
{
List liCities;


	public void init()
	{
		setBackground(Color.cyan);
		
		liCities = new List(5);

		liCities.addItem("Mumbai");
		liCities.addItem("Surat");
		liCities.addItem("Pune");
		liCities.addItem("Raigad");
		liCities.addItem("Ahemdabad");
		liCities.addItem("Chennai");
		liCities.addItem("Panji");
		liCities.addItem("Shrinagar");
		liCities.addItem("Mumbai");
		liCities.addItem("Mumbai");

		 add(liCities);

	}

}


