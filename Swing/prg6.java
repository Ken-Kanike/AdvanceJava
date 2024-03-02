import java.awt.*;
import javax.swing.*;
class MyFrame extends JFrame{
JCheckBox c1,c2;
Container c;

			 MyFrame()
			{
				 c = getContentPane();
				 c.setBackground(Color.YELLOW);

			     c1 = new JCheckBox("Red");
			      c2 = new JCheckBox("Blue");

				c.setLayout(new FlowLayout());

				c.add(c1);
				c.add(c2);
			}		
} 
// ------------------------------------------------------- MyFrame Closed

class prg6
{
public static void main(String [] args)
{
MyFrame f = new MyFrame();
			f.setSize(400,400);
			f.setVisible(true);
}
}
