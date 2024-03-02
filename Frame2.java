import java.awt.*;
import java.awt.event.*;

class myFrame extends Frame 
{
    myFrame(String s)
    {
        super(s);
        setBackground(Color.BLUE);
        setSize(400,400);
        setVisible(true);

        // // frame window closing logic 
        // addWindowListner(new WindowAdapter()
        // {
        //     public void windowClosing(WindowEvent we)
        //     {
        //         System.exit(0);
        //     }
        // });
    }

    public void paint(Graphics g)
    {
        Font f = new Font("Arial",Font.BOLD,24);
        g.setFont(f);
        g.setColor(Color.YELLOW);
        g.drawString("My first program",50,200);
    }
}

class Frame2
{
    public static void main(String[] args) {

        myFrame f = new myFrame("Frame program");

    }
}