// Craeting a Simple Checkbox
import java.awt.*;
class MyFrame extends Frame 
{
    Checkbox cb1 , cb2;
    String mssg="";
    MyFrame()
    {
     
        setLayout(null);
        setVisible(true);
        setSize(500, 300);
        setTitle("Checkbox");
        cb1 = new Checkbox("Java",false);
        cb1.setBounds(100, 100, 50, 50);
        add(cb1);
         cb2 = new Checkbox("C++",false);
        cb2.setBounds(100, 200, 50, 50);
        add(cb2);
    }
}
public class program1_7
{
    public static void main(String[] args) {
        MyFrame f = new MyFrame();
    }   
}
