import java.awt.*;
import javax.swing.*;
class MyFrame extends JFrame
{
JButton b1,b2;
Container c;

			 MyFrame()
			{
				 c = getContentPane();
				 c.setBackground(Color.YELLOW);

				b1 = new JButton("Yes");
			    b2 = new JButton("No");

				c.setLayout(new FlowLayout());

				c.add(b1);
				c.add(b2);
			}		
} 
// ------------------------------------------------------- MyFrmame Closed
class prg3
{
public static void main(String [] args)
{
MyFrame f = new MyFrame();
			f.setSize(400,400);
			f.setVisible(true);
}
}
