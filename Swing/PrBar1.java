import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PrBar1 extends JFrame{
JProgressBar pb1;
JButton b1;
Timer t;
int v = 0;

    public PrBar1() {
        //setTitle("Progress Bar Example");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

       pb1 = new JProgressBar(0, 100);
        pb1.setValue(0);
        pb1.setStringPainted(true);

        b1 = new JButton("Start");

        b1.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                b1.setEnabled(false);
                t.start();
            }
        });

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        
            public void run() {
                PrBar1 example = new PrBar1();
                example.setVisible(true);
            }
        });
    }
}
