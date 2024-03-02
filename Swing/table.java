package Swing2;
import java.awt.*;
import javax.swing.*;
class MyFrame extends JFrame
{
    String[] collHead = {"Srno","name"};
    String[] data[] = {
        { "1","Ram"},
        { "2","Karan"},
        { "3" ,"John"}

    };
    JTable jt = new JTable(data,collHead);
    JScrollPane js = new JScrollPane(jt);
    MyFrame()
    {
        getContentPane().add(js);
    }
    
}

public class table {

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setSize(440, 400);
        f.setVisible(true);
    }
    
}
