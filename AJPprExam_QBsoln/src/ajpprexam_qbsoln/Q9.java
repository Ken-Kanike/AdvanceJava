
package ajpprexam_qbsoln;

import javax.swing.*;
import java.awt.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class Q9 extends JFrame {
  JTree jt;
    public Q9(){
         
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

    public static void main(String[] args) {
        Q9 myFrame = new Q9();
        myFrame.setSize(400, 400);
        myFrame.setTitle("JTree Example");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }
    
}
