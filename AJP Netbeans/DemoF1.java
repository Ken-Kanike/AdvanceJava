// package javaapplication1;
import java.awt.*;
class MyFrame extends Frame
{
Button b1=new Button("Center");
Button b2=new Button("North");
Button b3=new Button("South");
Button b4=new Button("East");
Button b5=new Button("West");
Button b6=new Button("extra");
Panel p1=new Panel();
    
    MyFrame(){
    setLayout(new FlowLayout());
    add(b1);
    add(b2,BorderLayout.NORTH);
    add(p1,BorderLayout.SOUTH);
    add(b4,BorderLayout.EAST);
    add(b5,BorderLayout.WEST);
    p1.setBackground(Color.yellow);
    p1.add(b3);
    p1.add(b6);
    
  
    }
    public void paint(Graphics g) {
        super.paint(g); 
        g.drawString("hello", 100, 100);
    }
    
}
public class DemoF1 {
    public static void main(String[] args) {
        MyFrame f=new MyFrame();
        f.setVisible(true);
        f.setSize(300, 300);
    }
    
}
