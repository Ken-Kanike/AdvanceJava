import java.awt.*;
import javax.swing.*;
class MyFrame extends JFrame{
JRadioButton r1,r2;
ButtonGroup g1;
Container c;
			 MyFrame(){
				 c = getContentPane();
				 c.setBackground(Color.YELLOW);

                  g1 = new ButtonGroup();
			     r1 = new JRadioButton("Male",true);
			      r2 = new JRadioButton("Female");

				  g1.add(r1);
				  g1.add(r2);

				c.setLayout(new FlowLayout());

				c.add(r1);
				c.add(r2);
			}		
} 
// ------------------------------------------------------- MyFrame Closed
class prg7{
public static void main(String [] args){
MyFrame f = new MyFrame();
			f.setSize(400,400);
			f.setVisible(true);
}
}
