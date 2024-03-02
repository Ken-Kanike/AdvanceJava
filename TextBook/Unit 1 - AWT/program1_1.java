// Craeting a Frame by instantiating Frame class
import java.awt.*;
public class program1_1
{

    program1_1()
    {
        Frame f = new Frame();
        Label l = new Label("Hello World");
        l.setBounds(10, 10, 100, 50);
        f.add(l);
        f.setSize(300, 300);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        
        program1_1 d = new program1_1();
    }
}