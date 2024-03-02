import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

class MyFrame extends JFrame {
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
        panel2 = new Panel2();
        panel3 = new Panel3();
        
        jtb.add("Panel 1", panel1);
        jtb.add("Panel 2", panel2);
        jtb.add("Panel 3", panel3);

        c.setLayout(new BorderLayout());
        c.add(jtb, BorderLayout.CENTER);
    }
    
    public String getValueFromPanel1() {
        return panel1.getTextFieldValue();
    }
    
   
    
    public String getValueFromPanel3() {
        return panel3.getTextFieldValue();
    }
    public void setTextToPanel3(String text) {
        panel3.setTextFieldText(text);
    }
    
    public void setTableData(String[] columnHeaders, String[][] data) {
        panel2.setTableData(columnHeaders, data);
    }
}


class Panel1 extends JPanel {
    private JTextField t1;

    Panel1() {
        JLabel l1 = new JLabel("Enter Data");
        l1.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton b1 = new JButton("Submit");
        b1.setFont(new Font("Arial", Font.PLAIN, 20));
        t1 = new JTextField(40);
        t1.setFont(new Font("Arial", Font.PLAIN, 20));
        add(l1);
        add(t1);
        add(b1);
        
//        b1.addActionListener(this); 
//            
//        public void actionPerformed(ActionEvent e){
//            String value = t1.getText();
//            System.out.println("Value from Panel 1: " + value);
//        }
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = t1.getText();
                System.out.println("Value from Panel 1: " + value);
            }
        });
    }
    
    public String getTextFieldValue() {
        return t1.getText();
    }
}

class Panel2 extends JPanel {
    private JTable jt;
    private DefaultTableModel model;

    Panel2() {
        JLabel l2 = new JLabel("Table Data");
        l2.setFont(new Font("Arial", Font.PLAIN, 20));
        jt = new JTable();
        model = new DefaultTableModel();
        jt.setModel(model);
        JScrollPane js = new JScrollPane(jt);
        add(l2);
        add(js);
    }

    public void setTableData(String[] columnHeaders, String[][] data) {
        model.setDataVector(data, columnHeaders);
    }
    
}

class Panel3 extends JPanel {
    private JTextField t3;
     private JTextField t4;

    Panel3() {
        JLabel l3 = new JLabel("Enter Data");
        l3.setFont(new Font("Arial", Font.PLAIN, 20));
        JButton b3 = new JButton("Submit");
        b3.setFont(new Font("Arial", Font.PLAIN, 20));
        t3 = new JTextField( 40);
        t3.setFont(new Font("Arial", Font.PLAIN, 20));
        t4 = new JTextField( 40);
        t4.setFont(new Font("Arial", Font.PLAIN, 20));
        add(l3);
        add(t3);
        add(b3);
        add(t4);
        
        b3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = t3.getText();
                System.out.println("Value from Panel 3: " + value);
            }
        });
    }
    
    public String getTextFieldValue() {
        return t3.getText();
    }
    public void setTextFieldText(String text) {
        t4.setText(text);
    }
}

public class Jdemo1 {

    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setSize(2000, 1500);
        f.setVisible(true);
        
        // Access the values from the panels and print them
        String valueFromPanel1 = f.getValueFromPanel1();
        String valueFromPanel3 = f.getValueFromPanel3();
        
        System.out.println("Value from Panel 1: " + valueFromPanel1);
        System.out.println("Value from Panel 3: " + valueFromPanel3);
        
        String s = "reply from main" + valueFromPanel1;
        
        // Set the text in Panel3
        f.setTextToPanel3(s);
        
        String[] collHead = {"Srno", "name"};
        String[][] data = {
            { "1", "Ram" },
            { "2", "Karan" },
            { "3", "John" },
            // ... Add more data ...
         };
    
        
       // Set the table data in Panel2
        f.setTableData(collHead, data);
            
            
        
    }
}
