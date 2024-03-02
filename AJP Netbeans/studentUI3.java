import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

class MyFrame extends JFrame {
    private String InsertQueryData;
    private JTabbedPane jtb;
    private Container c;
    private Panel1 panel1;
    private Panel2 panel2;
    private Panel3 panel3;

    MyFrame() {
        setTitle("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        c = getContentPane();
        c.setBackground(new Color(210, 240, 250));

        jtb = new JTabbedPane();
        jtb.setFont(new Font("Arial", Font.PLAIN, 20));
        jtb.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));

        panel1 = new Panel1();
        panel2 = new Panel2();
        panel3 = new Panel3();

        jtb.add("Add Student", panel1);
        jtb.add("Student List", panel2);
        jtb.add("Search Student", panel3);

        add(jtb, BorderLayout.CENTER);
    }

    public String getInsertQueryData() {
        return InsertQueryData;
    }

    public void setTableData(String[] columnHeaders, String[][] data) {
        panel2.setTableData(columnHeaders, data);
    }
}

class Panel1 extends JPanel {
    private String InsertQueryData;
    private JTextField stud_roll_no;
    private JTextField stud_name;
    private JTextField section;
    private JTextField subject;
    private JTextField CLASS;
    private JTextField pr_marks;
    private JTextField ct1_marks;
    private JTextField ct2_marks;
    private JTextField tut_marks;
    private JTextField manual_marks;
    private JButton b1;

    Panel1() {
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));

        JLabel lHead = new JLabel("Enter Student Details");
        lHead.setFont(new Font("Arial", Font.PLAIN, 30));
        lHead.setBounds(350, 30, 400, 40);

        JLabel l1 = new JLabel("Roll No");
        l1.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel l2 = new JLabel("Student Name");
        l2.setFont(new Font("Arial", Font.PLAIN, 20);
        // Add labels for other fields...

        stud_roll_no = createTextField();
        stud_name = createTextField();
        // Create text fields for other fields...

        b1 = new JButton("Submit");
        b1.setFont(new Font("Arial", Font.PLAIN, 20));
        b1.setBounds(350, 520, 100, 40);
        b1.setBackground(new Color(50, 150, 50));
        b1.setForeground(Color.WHITE);

        // Add components to the panel...
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the submit action...
            }
        });
    }
    
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setBounds(350, 100, 200, 30);
        return textField;
    }
}

class Panel2 extends JPanel {
    private JTable jt;
    private DefaultTableModel model;

    Panel2() {
        setLayout(new BorderLayout());

        jt = new JTable();
        model = new DefaultTableModel();
        jt.setModel(model);
        JScrollPane js = new JScrollPane(jt);

        js.setBorder(BorderFactory.createEmptyBorder(50, 50, 10, 50));
        Border tableBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        jt.setBorder(tableBorder);

        add(js, BorderLayout.CENTER);
    }

    public void setTableData(String[] columnHeaders, String[][] data) {
        model.setDataVector(data, columnHeaders);
    }
}

class Panel3 extends JPanel {
    private JTextField stud_roll_no;
    private JTextArea studTa;

    Panel3() {
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));

        JLabel lHead = new JLabel("Get Student Details");
        lHead.setFont(new Font("Arial", Font.PLAIN, 30));
        lHead.setBounds(350, 30, 400, 40);

        JLabel l1 = new JLabel("Enter Student Roll No:");
        l1.setFont(new Font("Arial", Font.PLAIN, 20);

        stud_roll_no = createTextField();
        stud_roll_no.setBounds(350, 100, 200, 30);

        studTa = new JTextArea(5, 10);
        studTa.setFont(new Font("Arial", Font.PLAIN, 20);
        studTa.setBounds(350, 150, 400, 200);

        JButton b1 = createButton("Submit");
        b1.setBounds(350, 400, 100, 40);

        JButton b2 = createButton("Clear Database");
        b2.setBounds(350, 450, 150, 40);
        
        // Add components to the panel...

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the submit action...
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the clear database action...
            }
        });
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBackground(new Color(50, 150, 50));
        button.setForeground(Color.WHITE);
        return button;
    }
}

public class StudentUI3 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MyFrame f = new MyFrame();

                String[] collHead = {"Roll No", "Student Name", "Section", "Subject", "Class",
                        "Practical Exam Marks", "CT1 Marks", "CT2 Marks", "Tutorial Marks", "Manual Marks"};

                String[][] data = {
                        {"12345", "John Doe", "A", "Math", "10A", "95", "88", "92", "78", "85"},
                        {"67890", "Jane Smith", "B", "Science", "10B", "88", "92", "85", "90", "92"},
                };

                f.setTableData(collHead, data);

                f.setVisible(true);
            }
        });
    }
}
