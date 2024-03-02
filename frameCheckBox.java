import java.awt.*;

class MyFrame extends Frame {
    CheckBoxGroup cbg1;
    CheckBox cb1 , cb2;
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
public class frameCheckBox {
    public static void main(String[] args) {
        MyFrame f = new MyFrame();
    }    
}
