// List Handling
import java.awt.*;
import java.awt.event.*;

class MyFrame extends Frame implements ItemListener
{
List li;
int i;
		MyFrame()
		{
			setBackground(Color.CYAN);
			setSize(600,600);
			setVisible(true);

			li = new List(3);
			li.add("TYCO1");
			li.add("TYCO2");
			li.add("SYCO1");
			li.add("SYCO2");

			setLayout(new FlowLayout());
			add(li);

		}

		public void itemStateChanged(ItemEvent ei){
			 i = li.getSelectedIndex();
			repaint();
		}

		public void paint(Graphics g)
		{
			//g.drawString("Total element:" + li.getCount(),10,250);
			g.drawString("Selected Index is :" + i,20,280);
			g.drawString("Selected Item is :" + li.getItem(i),10,310);

		}

}

public class fListH
{
	public static void main(String[] args) 
	{
		MyFrame f = new MyFrame();
	}
}
