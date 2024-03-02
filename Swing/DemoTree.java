
import javax.swing.*;
class MyFrame extends JFrame
{
   
    JTree jt = new JTree();
    MyFrame()
    { 
        getContentPane().add(jt);
    }
    
}

public class DemoTree {

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setSize(440, 400);
        f.setVisible(true);
    }
    
}
