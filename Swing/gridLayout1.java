import javax.swing.*;
import java.awt.*;

public class gridLayout1 {
    public static void main(String[] args) {
        fun1();
    }

    private static void fun1() {
        JFrame frame = new JFrame("GridLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2));

        for (int i = 1; i <= 30; i++) {
            panel.add(new JButton("Button " + i));
        }
		
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
        JScrollPane scrollPane = new JScrollPane(panel, v, h);
        
        // Add the scrollPane to the center of the frame
        frame.add(scrollPane,"Center");

        frame.pack();
        frame.setVisible(true);
    }
}
