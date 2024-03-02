import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.applet.Applet;

public class additionApplet extends Applet implements ActionListener 
{
    TextField t1 = new TextField(10);
    TextField t2 = new TextField(10);
    TextField t3 = new TextField(10);

    Label l1 = new Label("First No :");
    Label l2 = new Label("Second No :");
    Label l3 = new Label("Sum :");

    Button b = new Button("ADD");

    public void init(){

        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(l3);
        add(t3);
        add(b);

        b.addActionListener(this);
        
    }

    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() ==b){

            int n1 = Integer.parseInt(t1.getText());
            int n2 = Integer.parseInt(t2.getText());

            t3.setText(" " + (n1 + n2));
        }
    }

}