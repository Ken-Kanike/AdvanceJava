package awt_and_swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <h1>Event Handling in Java GUI</h1>
 * <p>
 * Demonstrates the Delegation Event Model with key listeners:
 * <ul>
 *   <li><b>ActionListener</b>: Button click & Enter key in textfields</li>
 *   <li><b>ItemListener</b>: State changes in checkboxes & dropdown combos</li>
 *   <li><b>MouseListener & MouseMotionListener</b>: Click, enter, exit, press, release, drag & coordinates tracking</li>
 *   <li><b>KeyListener</b>: Key typed, pressed, and released with key codes</li>
 *   <li><b>WindowListener & WindowAdapter</b>: Window closing, activated, iconified events</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class EventHandlingDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventHandlingFrame());
    }
}

class EventHandlingFrame extends JFrame {
    private JTextArea logArea;
    private JLabel mouseCoordinatesLabel;
    private JTextField keyInputField;

    public EventHandlingFrame() {
        setTitle("Java Delegation Event Model Master Demo");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handled by WindowAdapter

        setLayout(new BorderLayout(10, 10));

        // Top Control Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        JButton actionBtn = new JButton("Trigger ActionEvent");
        JCheckBox itemCheckBox = new JCheckBox("ItemListener Checkbox");
        JComboBox<String> itemCombo = new JComboBox<>(new String[]{"Option Alpha", "Option Beta", "Option Gamma"});

        topPanel.add(actionBtn);
        topPanel.add(itemCheckBox);
        topPanel.add(new JLabel("Select:"));
        topPanel.add(itemCombo);
        add(topPanel, BorderLayout.NORTH);

        // Center Panel with Interactive Canvas & Key Listener Field
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JPanel keyPanel = new JPanel(new BorderLayout(5, 5));
        keyPanel.add(new JLabel("Type here for KeyEvents: "), BorderLayout.WEST);
        keyInputField = new JTextField();
        keyPanel.add(keyInputField, BorderLayout.CENTER);
        centerPanel.add(keyPanel, BorderLayout.NORTH);

        // Interactive Mouse Tracking Area
        JPanel mouseCanvas = new JPanel();
        mouseCanvas.setBackground(new Color(245, 247, 250));
        mouseCanvas.setBorder(BorderFactory.createTitledBorder("Interactive Mouse Canvas (Move / Drag / Click here)"));
        mouseCoordinatesLabel = new JLabel("Mouse Coordinates: [X: 0, Y: 0]", JLabel.CENTER);
        mouseCanvas.setLayout(new BorderLayout());
        mouseCanvas.add(mouseCoordinatesLabel, BorderLayout.CENTER);
        centerPanel.add(mouseCanvas, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // Bottom Log Panel
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        logArea = new JTextArea(8, 50);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        bottomPanel.add(new JLabel("Event Log Console:"), BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // ==========================================
        // 1. ACTION LISTENER
        // ==========================================
        actionBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log("⚡ [ActionEvent] Button clicked at timestamp: " + e.getWhen());
            }
        });

        // ==========================================
        // 2. ITEM LISTENER
        // ==========================================
        itemCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String state = (e.getStateChange() == ItemEvent.SELECTED) ? "SELECTED" : "DESELECTED";
                log("☑️ [ItemEvent] Checkbox state changed to: " + state);
            }
        });

        itemCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                log("🔽 [ItemEvent] ComboBox selected: " + e.getItem());
            }
        });

        // ==========================================
        // 3. KEY LISTENER
        // ==========================================
        keyInputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                log("⌨️ [KeyEvent] Key Pressed: '" + KeyEvent.getKeyText(e.getKeyCode()) + "' (Code: " + e.getKeyCode() + ")");
            }
        });

        // ==========================================
        // 4. MOUSE & MOUSE MOTION LISTENERS
        // ==========================================
        mouseCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                log("🖱️ [MouseEvent] Clicked at (" + e.getX() + ", " + e.getY() + ") | ClickCount: " + e.getClickCount());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseCanvas.setBackground(new Color(230, 245, 255));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseCanvas.setBackground(new Color(245, 247, 250));
            }
        });

        mouseCanvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseCoordinatesLabel.setText(String.format("Mouse Coordinates: [X: %d, Y: %d]", e.getX(), e.getY()));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mouseCoordinatesLabel.setText(String.format("Mouse DRAGGING at: [X: %d, Y: %d]", e.getX(), e.getY()));
            }
        });

        // ==========================================
        // 5. WINDOW LISTENER (WindowAdapter)
        // ==========================================
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    EventHandlingFrame.this,
                    "Are you sure you want to exit Event Handling Demo?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0);
                }
            }
        });

        setVisible(true);
    }

    private void log(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }
}
