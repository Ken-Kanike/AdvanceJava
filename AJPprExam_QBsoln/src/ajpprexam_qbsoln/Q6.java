
package ajpprexam_qbsoln;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Q6 extends Frame implements ActionListener {

    private MenuItem blackMenuItem;

    public Q6() {
        MenuBar menuBar = new MenuBar();
        Menu colorMenu = new Menu("Colors");

        MenuItem redMenuItem = new MenuItem("Red");
        MenuItem greenMenuItem = new MenuItem("Green");
        MenuItem blueMenuItem = new MenuItem("Blue");
        blackMenuItem = new MenuItem("Black");

        // Disable the black menu item initially
        blackMenuItem.setEnabled(false);

        redMenuItem.addActionListener(this);
        greenMenuItem.addActionListener(this);
        blueMenuItem.addActionListener(this);
        blackMenuItem.addActionListener(this);

        colorMenu.add(redMenuItem);
        colorMenu.add(greenMenuItem);
        colorMenu.add(blueMenuItem);
        colorMenu.add(blackMenuItem);

        menuBar.add(colorMenu);
        setMenuBar(menuBar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Red":
                setBackground(Color.RED);
                break;
            case "Green":
                setBackground(Color.GREEN);
                break;
            case "Blue":
                setBackground(Color.BLUE);
                break;
            case "Black":
                setBackground(Color.BLACK);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Q6 myFrame = new Q6();
        myFrame.setSize(400, 400);
        myFrame.setBackground(Color.CYAN); // Set background color before making it visible
        myFrame.setTitle("Menu Example");
        myFrame.setVisible(true);
    }
}

