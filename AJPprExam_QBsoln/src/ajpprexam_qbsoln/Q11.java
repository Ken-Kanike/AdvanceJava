
package ajpprexam_qbsoln;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.Timer;

class MyFrame extends JFrame implements ActionListener{
JProgressBar pb1;
JButton b1;
Timer t;
int v = 0;

    MyFrame(){
        
        setSize(300, 150);
        setLocationRelativeTo(null);

        pb1 = new JProgressBar(0, 100);
        pb1.setValue(0);
        pb1.setStringPainted(true);

        b1 = new JButton("Start");
        b1.addActionListener(this);
        
//        b1.addActionListener(new ActionListener() {
//            
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                  b1.setEnabled(false);
//                   t.start();
//                }
//        });

        t = new Timer(100, new ActionListener() 
	{
        
                              public void actionPerformed(ActionEvent e) 
                              {
		if (v >= 100) 
		{
		    t.stop();
		    b1.setEnabled(true);
		}
		else 
		{
		   v += 5; // Simulate progress
		   pb1.setValue(v);
		}
                              }
                    });

        setLayout(new BorderLayout());
        add(pb1, BorderLayout.CENTER);
       add(b1, BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       b1.setEnabled(false);
       t.start();
    }

    
}
public class Q11 {

    public static void main(String[] args) {
        MyFrame myFrame = new MyFrame();
        myFrame.setTitle("JProgressBar Example");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }
    
}
