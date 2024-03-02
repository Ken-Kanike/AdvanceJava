// pgno10 xii 2
import java.awt.*;
import java.applet.*;

/* <applet code="pr2xiii2" width="600" height="600"></applet> */
public class pr2xiii2 extends Applet 
{
List liNews;


	public void init()
	{
		setBackground(Color.cyan);
		
		liNews = new List(2,true);

		liNews.addItem("Times of India");
		liNews.addItem("Economics Times");
		liNews.addItem("Kaseri");
		liNews.addItem("Indian Express");
		

		 add(liNews);

	}

}


