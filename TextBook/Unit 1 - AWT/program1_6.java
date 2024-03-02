// Craeting a Simple Button
import java.awt.*;
class MyFrame extends Frame 
{
    Button ok , cancle;
    MyFrame(String s)
    {
        super(s);
        setLayout(null);
        setVisible(true);
        setSize(500, 300);
        ok = new Button("OK");
        cancle = new Button("CANCLE");
        ok.setBounds(50, 50, 50, 50);
        cancle.setBounds(120, 50, 100, 50);
        add(ok);
        add(cancle);
    }
}
public class program1_6
{

    public static void main(String[] args) {
        MyFrame f = new MyFrame("Demonstrating Frame");
    }   
}
