import javax.swing.*;

public class gridLayout1 {
    public static void main(String[] args) {
        fun1();
    }

    private static void fun1() {
        JFrame frame = new JFrame("GridLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        for (int i = 1; i <= 6; i++) {
            panel.add(new JButton("Button " + i));
        }

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}