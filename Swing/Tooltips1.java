import javax.swing.*;

public class Tooltips1 {
    public static void main(String[] args) {
        fun1();
    }

    private static void fun1() {
        JFrame f = new JFrame("Tooltip Example");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);


        JButton button = new JButton("Hover me!");
        button.setToolTipText("This is a tooltip text.");

        f.add(button);
        f.pack();
        f.setVisible(true);
    }
}
