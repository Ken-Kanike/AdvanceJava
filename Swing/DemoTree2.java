import javax.swing.*;
import javax.swing.tree.*;

class MyFrame extends JFrame {

    JTree jt;
    MyFrame() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Computer");
        DefaultMutableTreeNode firstYear = new DefaultMutableTreeNode("First Year");
        DefaultMutableTreeNode secondYear = new DefaultMutableTreeNode("Second Year");
        DefaultMutableTreeNode thirdYear = new DefaultMutableTreeNode("Third Year");
        DefaultMutableTreeNode sem1 = new DefaultMutableTreeNode("Semester 1");
        DefaultMutableTreeNode sem2 = new DefaultMutableTreeNode("Semester 2");
        DefaultMutableTreeNode sem3 = new DefaultMutableTreeNode("Semester 3");
        DefaultMutableTreeNode sem4 = new DefaultMutableTreeNode("Semester 4");
        DefaultMutableTreeNode sem5 = new DefaultMutableTreeNode("Semester 5");
        DefaultMutableTreeNode sem6 = new DefaultMutableTreeNode("Semester 6");

        firstYear.add(sem1);
        firstYear.add(sem2);

        secondYear.add(sem3);
        secondYear.add(sem4);

        thirdYear.add(sem5);
        thirdYear.add(sem6);

        root.add(firstYear);
        root.add(secondYear);
        root.add(thirdYear);

        jt = new JTree(root);
        getContentPane().add(new JScrollPane(jt));
    }
}

public class DemoTree2 {

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setSize(440, 400);
        f.setVisible(true);
    }
}
