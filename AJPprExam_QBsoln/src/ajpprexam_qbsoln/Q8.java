
package ajpprexam_qbsoln;
import javax.swing.*;
import java.awt.*;

public class Q8 extends JFrame {
 Container c;  
    public Q8() {
        c = getContentPane();
        //c.setBackground(Color.YELLOW);
        
        // Create a JTextArea
        JTextArea textArea = new JTextArea();
        //textArea.setLineWrap(true);
        //textArea.setWrapStyleWord(true);

        // Create a JScrollPane and add the JTextArea to it
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        

        // Add the JScrollPane to the frame
        c.add(scrollPane);

        setSize(400, 400);
        setTitle("ScrollPane Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    public static void main(String[] args) {
            Q8 myFrame = new Q8();
            myFrame.setVisible(true);
    }
}
