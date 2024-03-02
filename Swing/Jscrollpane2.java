import javax.swing.*;
import java.awt.*;
public class Jscrollpane2 {
    public static void main(String[] args) {
        fun1();
    }

    private static void fun1() {
        JFrame f = new JFrame("Scroll Pane Example");
		Container c = f.getContentPane();
		c.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

		f.setLayout(new GridLayout(25,25,10,10));

		for(int i=1;i<=25;i++)
			for(int j=1;j<=25;j++)
				f.add(new JButton(j+" "));
       
       
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;;
        JScrollPane scrollPane = new JScrollPane(f, v, h);

      
        c.add(scrollPane,"Center");
        f.pack();
        f.setVisible(true);
    }
}
