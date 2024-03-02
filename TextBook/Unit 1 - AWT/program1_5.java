// Craeting a Simple Panel
import java.awt.*;
public class program1_5 extends Frame
{

    program1_5(String s)
    {  
        super(s);  
        setSize(500, 300);
        setLayout(null);
        setVisible(true);

        Label one = new Label("Label one");
        Label two = new Label("Label two");
        Label three = new Label("Label three");

        // set positions of controls 

        one.setBounds(50,50,100,100);
        two.setBounds(150,50,100,100);
        three.setBounds(250,50,100,100);

        add(one);
        add(two);
        add(three);
    }

    public static void main(String[] args) {
        
        program1_5 d = new program1_5("Labels");
    }
}