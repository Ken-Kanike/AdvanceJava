import javax.swing.*;
import java.awt.Dimension;

public class Jscrollpane1 {
    public static void main(String[] args) {
        fun1();
    }

    private static void fun1() {
        JFrame f = new JFrame("Scroll Pane Example");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea("Java is a high-level, object-oriented programming language developed by Sun Microsystems (now owned by Oracle Corporation). It was first released in 1995 and has since become one of the most widely used programming languages in the world. Java's popularity is attributed to its platform independence, strong community support, and versatility.");

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
         int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;;
        JScrollPane scrollPane = new JScrollPane(textArea, v, h);

        // Set the preferred size of the scroll pane
        scrollPane.setPreferredSize(new Dimension(80, 80));

        f.add(scrollPane);
        f.pack();
        f.setVisible(true);
    }
}
