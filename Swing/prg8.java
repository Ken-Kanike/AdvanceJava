import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class MyFrame extends JFrame implements ActionListener
{
JRadioButton r1,r2;
ButtonGroup g1;
Container c;
String s = "";
JTextField t1;

			 MyFrame()
			{
				 c = getContentPane();
				 c.setBackground(Color.YELLOW);

                  g1 = new ButtonGroup();
			     r1 = new JRadioButton("Male",true);
			      r2 = new JRadioButton("Female");

				  t1 = new JTextField(30);

				  g1.add(r1);
				  g1.add(r2);

				c.setLayout(new FlowLayout());

				r1.addActionListener(this);
				r2.addActionListener(this);

				c.add(r1);
				c.add(r2);
				c.add(t1);
			}		

			public void actionPerformed(ActionEvent ae)
			{
			     String  t = ae.getActionCommand();
				 t1.setText(t);
			}
} 
// ------------------------------------------------------- MyFrame Closed

class prg8
{
public static void main(String [] args)
{
MyFrame f = new MyFrame();
			f.setSize(400,400);
			f.setVisible(true);
}
}
