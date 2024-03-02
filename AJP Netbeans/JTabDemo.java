
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

class JunPanel1 extends JPanel //implements ActionListener
{
    JButton b = new JButton("Save");
    JTextField t = new JTextField(40);

    public JunPanel1() {
        add(b);
        add(t);
        //b1.addActionListener(this);
        
 
    }
    
    
}
  

class MyFrame2 extends JFrame
{
    JTabbedPane jtp = new JTabbedPane(JTabbedPane.LEFT);
    JunPanel1 p1 = new JunPanel1();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    Container c;

    public MyFrame2() {
        c = getContentPane();
        p1.setBackground(Color.red);
        p2.setBackground(Color.green);
        p3.setBackground(Color.blue);
        
        jtp.addTab("Red", p1);
        jtp.addTab("Green", p2);
        jtp.addTab("Blue", p3);
        
        c.add(jtp);
        
        
        
    }
    
}
public class JTabDemo {
        public static void main(String[] args) {
        MyFrame2 f = new  MyFrame2();
        f.setSize(600,600);
        f.setVisible(true);
  
    }
    
    
}
