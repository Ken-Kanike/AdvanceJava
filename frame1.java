import java.awt.*;

class frame1 
{
     

    public static void main(String[] args) {
        
        Frame f = new Frame("My First Frame");

        f.setBackground(Color.YELLOW);
        f.setSize(400,400);
        f.setVisible(true);

        Button  b1 = new Button("Yes");
        Button  b2 = new Button("No");

        f.setLayout(new FlowLayout());

        f.add(b1);
        f.add(b2);
    }
}
