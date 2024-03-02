import java.awt.*;

class MyFrame extends Frame {
    Button b1;
    Font f ;

    MyFrame(){

        setBackground(Color.MAGENTA);
        setSize(600,600);
        setVisible(true);
        f = new Font("Arial",Font.BOLD,24);

        b1 = new Button("OK"); b1.setForeground(Color.BLUE);b1.setFont(f);b1.setBackground(Color.PINK);

        setLayout(new FlowLayout());
        add(b1); 
    }
}
public class frameButton  {
    public static void main(String[] args) {
        MyFrame f = new MyFrame();
    }    
}
