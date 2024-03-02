// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
// package javaapplication1;

import java.awt.Container;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 *
 * @author junai
 */
class  MyFrame1 extends JFrame
{
    JProgressBar jb = new JProgressBar(0, 200);
    Container c;
    int i =0;
    public   MyFrame1() throws HeadlessException {
        c = getContentPane();
        c.setLayout(null);
        jb.setBounds(40, 40, 200, 40);
        jb.setValue(0);
        jb.setStringPainted(true);
        c.add(jb);
        jb.setToolTipText("Progress bar");
    }
    
    public void iterate()
    {
        while(i<=200)
        {
            jb.setValue(i);
            i = i +10;
            try{  //interrupted 
                Thread.sleep(150);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
}
public class ProgressDemo {
    public static void main(String[] args) {
         MyFrame1 f = new  MyFrame1();
        f.setSize(400,400);
        f.setVisible(true);
        f.iterate();
    }
    
}
