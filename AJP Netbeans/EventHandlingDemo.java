
// package javaapplication1;

import java.awt.*;
import java.awt.event.*;

class MyFrame4 extends Frame implements ActionListener , WindowListener {
    Button b = new Button("hit me");
    TextField t = new TextField(40);
    Dialog d = new Dialog(this,"MyDialogue",false); // true for model dialogue box 

    public MyFrame4() {
        
        add(t);
        add(b);
        setLayout(new FlowLayout());
        t.addActionListener(this);
        b.addActionListener(this);
        addWindowListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       t.setText("Button Clicked");
       b.setBackground(Color.red);
    }

    @Override
    public void windowOpened(WindowEvent e) {
        setBackground(Color.yellow);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
        d.setVisible(true);
        d.setSize(100,50);
        d.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //d.dispose();
                System.exit(0);
            }

//            @Override
//            public void windowDeactivated(WindowEvent e) {
//                System.exit(0);
//            }

            
            
            
        });
        
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
       
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
    
    
            
}

public class EventHandlingDemo {
    
    public static void main(String[] args) {
        MyFrame4 f = new MyFrame4();
        f.setVisible(true);
        f.setSize(600,600);
       
    }
    
}
