// Craeting a Frame by extending Frame class
import java.awt.*;
public class program1_2 extends Frame
{

    program1_2()
    {
       setLayout(null);
       setSize(500, 500);
       setTitle("Hello");
       setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
    g.drawString("Welcome to Java",250,250 );
    }

    public static void main(String[] args) {
        
        program1_2 d = new program1_2();
    }
}