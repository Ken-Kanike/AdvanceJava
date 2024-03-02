import java.awt.*;

class MyFrame extends Frame
{
Button b1,b2;

			 MyFrame()
			{
				setBackground(Color.YELLOW);

				b1 = new Button("Yes");
			    b2 = new Button("No");

				setLayout(new FlowLayout());

				add(b1);
				add(b2);
			}		
} 
// ------------------------------------------------------- MyFrmame Closed

class prg2
{
public static void main(String [] args)
{
MyFrame f = new MyFrame();
			f.setSize(400,400);
			f.setVisible(true);
}
}
