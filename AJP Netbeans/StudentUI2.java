import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import java.awt.event.*;
import javax.swing.table.DefaultTableModel;


class MyFrame extends JFrame {
     private String InsertQueryData;
    JTabbedPane jtb;
    Container c;
    Panel1 panel1;
    Panel2 panel2;
    Panel3 panel3;
 
    MyFrame() {
        c = getContentPane();
        c.setBackground(Color.CYAN);

        jtb = new JTabbedPane();
        jtb.setFont(new Font("Arial", Font.PLAIN, 20));
        panel1 = new Panel1();
        panel1.setLayout(null);
        panel2 = new Panel2();
        panel2.setLayout(new GridLayout());
        panel3 = new Panel3();
        panel3.setLayout(null);
        
        jtb.add("Panel 1", panel1);
        jtb.add("Panel 2", panel2);
        jtb.add("Panel 3", panel3);

        c.setLayout(new BorderLayout());
        c.add(jtb, BorderLayout.CENTER);
    }

    // Add a method to retrieve the InsertQueryData value
    public String getInsertQueryData() {
        return InsertQueryData;
    }

    public void setTableData(String[] columnHeaders, String[][] data) {
        panel2.setTableData(columnHeaders, data);
    }
}


class Panel1 extends JPanel {
String InsertQueryData;
JTextField stud_roll_no;
JTextField stud_name;
JTextField section;
JTextField subject;
JTextField CLASS;
JTextField pr_marks;
JTextField ct1_marks;
JTextField ct2_marks;
JTextField tut_marks;
JTextField manual_marks;
public JButton b1;

    Panel1(){

        JLabel lHead = new JLabel("Enter Student Details");lHead.setFont(new Font
        ("Arial", Font.PLAIN, 30));

        JLabel l1 = new JLabel("Roll No");l1.setFont(new Font
        ("Arial", Font.PLAIN, 20));
        JLabel l2 = new JLabel("Student Name");l2.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel l3 = new JLabel("Section");l3.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel l4 = new JLabel("Subject");l4.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel l5 = new JLabel("Class");l5.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel l6 = new JLabel("Practical Exam Makrs");l6.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel l7 = new JLabel("CT1 marks");l7.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel l8 = new JLabel("CT2 Marks");l8.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel l9 = new JLabel("Tutorial Marks");l9.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel l10 = new JLabel("Manual Marks");l10.setFont(new Font("Arial", Font.PLAIN, 20));


        stud_roll_no = new JTextField(40);stud_roll_no.setFont(new Font("Arial", Font.PLAIN, 20));
        stud_name = new JTextField(40);stud_name.setFont(new Font("Arial", Font.PLAIN, 20));
        section = new JTextField(40);section.setFont(new Font("Arial", Font.PLAIN, 20));
        subject= new JTextField(40);subject.setFont(new Font("Arial", Font.PLAIN, 20));
        CLASS = new JTextField(40);CLASS.setFont(new Font("Arial", Font.PLAIN, 20));
        pr_marks = new JTextField(40);pr_marks.setFont(new Font("Arial", Font.PLAIN, 20));
        ct1_marks = new JTextField(40);ct1_marks.setFont(new Font("Arial", Font.PLAIN, 20));
        ct2_marks = new JTextField(40);ct2_marks.setFont(new Font("Arial", Font.PLAIN, 20));
        tut_marks= new JTextField(40);tut_marks.setFont(new Font("Arial", Font.PLAIN, 20));
        manual_marks = new JTextField(40);manual_marks.setFont(new Font("Arial", Font.PLAIN, 20));

        lHead.setBounds(700,10,500,30);
        l1.setBounds(10,100,250,20);
        stud_roll_no.setBounds(250,100,250,20);
        l2.setBounds(10,150,250,20);       
        stud_name.setBounds(250,150,250,20);  
        l3.setBounds(10,200,250,20);
        section.setBounds(250,200,250,20);
        l4.setBounds(10,250,250,20);
        subject.setBounds(250,250,250,20);
        l5.setBounds(10,300,250,20);
        CLASS.setBounds(250,300,250,20);
        l6.setBounds(10,350,250,20);
        pr_marks.setBounds(250,350,250,20);
        l7.setBounds(10,400,250,20);
        ct1_marks.setBounds(250,400,250,20);
        l8.setBounds(10,459,250,20);
        ct2_marks.setBounds(250,450,250,20);
        l9.setBounds(10,500,250,20);
        tut_marks.setBounds(250,500,250,20);
        l10.setBounds(10,550,200,20);
        manual_marks.setBounds(250,550,250,20);
       
        b1 = new JButton("Submit");
        b1.setFont(new Font("Arial", Font.PLAIN, 20));
        b1.setBounds(700,750,250,20);

        add(lHead);
        add(l1);
        add(stud_roll_no);
        add(l2);
        add(stud_name);
        add(l3);
        add(section);
        add(l4);
        add(subject);
        add(l5);
        add(CLASS);
        add(l6);
        add(pr_marks);
        add(l7);
        add(ct1_marks);
        add(l8);
        add(ct2_marks);
        add(l9);
        add(tut_marks);
        add(l10);
        add(manual_marks);


        add(b1);
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value1 = stud_roll_no.getText();
                String value2 = stud_name.getText();
                String value3 = section.getText();
                String value4 = subject.getText();
                String value5 = CLASS.getText();
                String value6 = pr_marks.getText();
                String value7 = ct1_marks.getText();
                String value8 = ct2_marks.getText();
                String value9 = tut_marks.getText();
                String value10 =manual_marks.getText(); 

                // Update the InsertQueryData variable
                InsertQueryData = "'" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "','" + value5 + "','" + value6 + "','" + value7 + "','" + value8 + "','" + value9 + "','" + value10 + "'";
                // Print the value (for verification)
                System.out.println("InsertQueryData updated: " + InsertQueryData);
            }
        });
    }
    
}

class Panel2 extends JPanel {
    private JTable jt;
    private DefaultTableModel model;

     Panel2() 
     {
        jt = new JTable();
        model = new DefaultTableModel();
        jt.setModel(model);
        JScrollPane js = new JScrollPane(jt);

        // Add some empty borders to the JScrollPane and set the border thickness of the main panel
        js.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50)); // Adjust the numbers as needed for the desired spacing

        // Set a border for the JTable
        Border tableBorder = BorderFactory.createLineBorder(Color.BLACK, 1); // You can adjust the color and thickness
        jt.setBorder(tableBorder);

        // Use a layout manager (e.g., BorderLayout) to manage the components
        setLayout(new BorderLayout());

        // Add the JScrollPane to the center
        add(js, BorderLayout.CENTER);
     }

    public void setTableData(String[] columnHeaders, String[][] data) {
        model.setDataVector(data, columnHeaders);
    }
    
}

class Panel3 extends JPanel {

    JTextField stud_roll_no;
    JTextArea studTa;
    
    Panel3() {

         JLabel lHead = new JLabel("Get Student Details");lHead.setFont(new Font
        ("Arial", Font.PLAIN, 30));
        JLabel l1 = new JLabel("Enter Student Roll No :");l1.setFont(new Font
        ("Arial", Font.PLAIN, 20));
        JLabel l2 = new JLabel("");l2.setFont(new Font("Arial", Font.PLAIN, 20));
        
      
        stud_roll_no = new JTextField(40);stud_roll_no.setFont(new Font("Arial", Font.PLAIN, 20));
        studTa = new JTextArea("",5, 10);stud_roll_no.setFont(new Font("Arial", Font.PLAIN, 20));
        
        lHead.setBounds(700,10,500,30);
        l1.setBounds(10,100,250,20);
        stud_roll_no.setBounds(250,100,250,20);
        studTa.setBounds(250, 130, 1000,60);
        l2.setBounds(300,250,250,20);       
       
    
       
        JButton b1 = new JButton("Submit");
        b1.setFont(new Font("Arial", Font.PLAIN, 20));
        b1.setBounds(700,750,250,30);

        JButton b2 = new JButton("Clear Database");
        b2.setFont(new Font("Arial", Font.PLAIN, 20));
        b2.setBounds(10,250,200,30);

        add(lHead);
        add(l1);
        add(stud_roll_no);
        add(studTa);
        add(l2);
  
        add(b1);
        add(b2);
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value1 = stud_roll_no.getText();
                //String value2 = stud_name.getText();
                System.out.println("Value from Panel 1: " + value1 );
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               l2.setText("Data deleted");
            }
        });
    
    }
}

public class StudentUI2 {

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setSize(2000, 1500);
        f.setVisible(true);
  
        String[] collHead = {"roll", "name" ,"section","subject","class","pr","ct1","ct2","tut","manual"};
        String[][] data = {
            { "roll", "name" ,"section","subject","class","pr","ct1","ct2","tut","manual" },
            { "roll", "name" ,"section","subject","class","pr","ct1","ct2","tut","manual" },
            {"roll", "name" ,"section","subject","class","pr","ct1","ct2","tut","manual"},
      
         };
        f.setTableData(collHead, data);

       
        
        

        f.panel1.b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String insertData = f.getInsertQueryData();
                System.out.println("InsertQueryData: " + insertData);
            }
        });
        
  
    }
}
