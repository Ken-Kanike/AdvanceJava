
package ajpprexam_qbsoln;

import javax.swing.*;
import java.awt.*;

class MyFrame10 extends JFrame {
    
JTable jt;

    MyFrame10(){
        String[] collHead = {"Sr.no","Student Name","Percentage","Grade"};
        String[] data[] = {
                                        { "1","Jeff","92%","A"},
                                        { "2","Jeff","92%","A"},
                                        { "3","Jeff","92%","A"},
                                        { "4","Jeff","92%","A"},
                                        { "5","Jeff","92%","A"},
                                        { "6","Jeff","92%","A"},
                                        { "7","Jeff","92%","A"},
                                        { "8","Jeff","92%","A"},
                                        { "9","Jeff","92%","A"},
                                        { "10","Jeff","92%","A"},
                                    };
        
        jt = new JTable(data,collHead);
        JScrollPane js = new JScrollPane(jt);
        getContentPane().add(js);
    }
}
public class Q10 {

    public static void main(String[] args) {
        MyFrame10 myFrame = new MyFrame10();
        myFrame.setSize(400, 400);
        myFrame.setTitle("JTable Example");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }
    
}
