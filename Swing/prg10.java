import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyFrame extends JFrame implements ItemListener
{
JComboBox ch ;
JTextField t1;

			 MyFrame()
			{
				setBackground(Color.YELLOW);

				ch = new JComboBox();
			    ch.addItem("Comp1");ch.addItem("Comp2");ch.addItem("Comp3");ch.addItem("Comp4");
                ch.addItem("Comp5");ch.addItem("Comp6");ch.addItem("Comp7");ch.addItem("Comp8");
				setLayout(new FlowLayout());
				t1=new JTextField(30);

				add(ch);
				add(t1);
				ch.addItemListener(this);
	
				}
				public void itemStateChanged(ItemEvent ie)
	          {
					t1.setText((String)ch.getSelectedItem());
	           }
} 
// ------------------------------------------------------- MyFrmame Closed

class prg10
{
public static void main(String [] args)
{
MyFrame f = new MyFrame();
			f.setSize(400,400);
			f.setVisible(true);
}
}
