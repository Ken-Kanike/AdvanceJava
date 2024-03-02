import java.awt.*;
import java.awt.event.*;

class MyFrame extends Frame implements ItemListener
{
Choice ch ;
TextField t1;

			 MyFrame()
			{
				setBackground(Color.YELLOW);

				ch = new Choice();
			    ch.add("Comp1");ch.add("Comp2");ch.add("Comp3");ch.add("Comp4");
                ch.add("Comp5");ch.add("Comp6");ch.add("Comp7");ch.add("Comp8");
				setLayout(new FlowLayout());
				t1=new TextField(40);

				add(ch);
				add(t1);
				ch.addItemListener(this);
	
				}
				public void itemStateChanged(ItemEvent ie)
	          {
					t1.setText(ch.getSelectedItem());
	           }
} 
// ------------------------------------------------------- MyFrmame Closed

class prg9
{
public static void main(String [] args)
{
MyFrame f = new MyFrame();
			f.setSize(400,400);
			f.setVisible(true);
}
}
