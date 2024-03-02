// Craeting a Simple Panel
import java.awt.*;
public class program1_3 extends Frame
{

    program1_3()
    {
       
        Frame f = new Frame("Panel example");
        Panel panel = new Panel();
        panel.setBounds(20, 40, 200, 200);
        panel.setBackground(Color.cyan);
        f.add( panel);
        f.setSize(440, 400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        
        program1_3 d = new program1_3();
    }
}