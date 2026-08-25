import java.awt.*;
import java.awt.event.*;

class MyFrame extends Frame {
    CheckboxGroup cbg;
    Checkbox cbJava, cbPython, cbCpp;
    Checkbox rbMale, rbFemale;
    Button b1;
    Font f;

    MyFrame() {
        setTitle("AWT Checkbox & Radio Button Demo");
        setBackground(new Color(240, 240, 240));
        setSize(450, 300);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
        f = new Font("Arial", Font.BOLD, 14);

        // Standard Checkboxes (Multiple selection)
        cbJava = new Checkbox("Java", true);
        cbPython = new Checkbox("Python", false);
        cbCpp = new Checkbox("C++", false);
        cbJava.setFont(f);
        cbPython.setFont(f);
        cbCpp.setFont(f);

        // Radio Buttons (Single selection via CheckboxGroup)
        cbg = new CheckboxGroup();
        rbMale = new Checkbox("Male", cbg, true);
        rbFemale = new Checkbox("Female", cbg, false);
        rbMale.setFont(f);
        rbFemale.setFont(f);

        b1 = new Button("Submit");
        b1.setFont(f);
        b1.setBackground(new Color(70, 130, 180));
        b1.setForeground(Color.WHITE);

        add(new Label("Select Skills: "));
        add(cbJava);
        add(cbPython);
        add(cbCpp);

        add(new Label("Select Gender: "));
        add(rbMale);
        add(rbFemale);

        add(b1);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }
}

public class frameCheckBox {
    public static void main(String[] args) {
        new MyFrame();
    }
}

