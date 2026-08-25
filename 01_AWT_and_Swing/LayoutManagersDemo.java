package awt_and_swing;

import java.awt.*;
import javax.swing.*;

/**
 * <h1>Layout Managers Showcase</h1>
 * <p>
 * Demonstrates the 6 fundamental layout managers in Java Swing & AWT:
 * <ul>
 *   <li><b>BorderLayout</b>: 5 cardinal regions (North, South, East, West, Center)</li>
 *   <li><b>FlowLayout</b>: Sequential horizontal row arrangement with auto-wrap</li>
 *   <li><b>GridLayout</b>: Uniform 2D grid matrix of equal cells</li>
 *   <li><b>GridBagLayout</b>: Complex flexible grid with spanning and weight controls</li>
 *   <li><b>CardLayout</b>: Stacked cards visible one at a time (wizard/tab style)</li>
 *   <li><b>BoxLayout</b>: Single row or column linear arrangement</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class LayoutManagersDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Java Layout Managers Comprehensive Guide");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(850, 600);
            frame.setLocationRelativeTo(null);

            JTabbedPane tabs = new JTabbedPane();
            tabs.addTab("BorderLayout", createBorderLayoutPanel());
            tabs.addTab("FlowLayout", createFlowLayoutPanel());
            tabs.addTab("GridLayout", createGridLayoutPanel());
            tabs.addTab("GridBagLayout", createGridBagLayoutPanel());
            tabs.addTab("CardLayout", createCardLayoutPanel());
            tabs.addTab("BoxLayout", createBoxLayoutPanel());

            frame.add(tabs);
            frame.setVisible(true);
        });
    }

    private static JPanel createBorderLayoutPanel() {
        JPanel p = new JPanel(new BorderLayout(8, 8));
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        p.add(new JButton("NORTH (Header / Toolbar)"), BorderLayout.NORTH);
        p.add(new JButton("SOUTH (Status Bar / Actions)"), BorderLayout.SOUTH);
        p.add(new JButton("EAST (Sidebar / Info)"), BorderLayout.EAST);
        p.add(new JButton("WEST (Navigation Tree)"), BorderLayout.WEST);
        p.add(new JButton("CENTER (Main Workspace Content)"), BorderLayout.CENTER);
        return p;
    }

    private static JPanel createFlowLayoutPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        for (int i = 1; i <= 8; i++) {
            p.add(new JButton("Item " + i + " (Auto-Flow)"));
        }
        return p;
    }

    private static JPanel createGridLayoutPanel() {
        JPanel p = new JPanel(new GridLayout(3, 3, 10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        String[] keys = {"7", "8", "9", "4", "5", "6", "1", "2", "3"};
        for (String k : keys) {
            JButton btn = new JButton(k);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            p.add(btn);
        }
        return p;
    }

    private static JPanel createGridBagLayoutPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.BOTH;

        // Row 0: Full width banner
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3; gbc.weightx = 1.0; gbc.weighty = 0.2;
        p.add(new JButton("Row 0: Spans 3 columns (gridwidth=3)"), gbc);

        // Row 1: Left column
        gbc.gridy = 1; gbc.gridx = 0; gbc.gridwidth = 1; gbc.weightx = 0.3; gbc.weighty = 0.8;
        p.add(new JButton("Row 1: Col 0 (30% weight)"), gbc);

        // Row 1: Right two columns
        gbc.gridx = 1; gbc.gridwidth = 2; gbc.weightx = 0.7;
        p.add(new JButton("Row 1: Col 1 & 2 (70% weight, spans 2 cols)"), gbc);

        return p;
    }

    private static JPanel createCardLayoutPanel() {
        JPanel container = new JPanel(new BorderLayout());
        CardLayout cardLayout = new CardLayout();
        JPanel cards = new JPanel(cardLayout);

        // Card 1
        JPanel card1 = new JPanel(new GridBagLayout());
        card1.setBackground(new Color(236, 240, 241));
        card1.add(new JLabel("Step 1: Welcome to the Installation Wizard"));

        // Card 2
        JPanel card2 = new JPanel(new GridBagLayout());
        card2.setBackground(new Color(214, 234, 248));
        card2.add(new JLabel("Step 2: Configure Database & Network Settings"));

        // Card 3
        JPanel card3 = new JPanel(new GridBagLayout());
        card3.setBackground(new Color(213, 245, 227));
        card3.add(new JLabel("Step 3: Setup Completed Successfully!"));

        cards.add(card1, "card1");
        cards.add(card2, "card2");
        cards.add(card3, "card3");

        // Navigation controls
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnPrev = new JButton("Previous Step");
        JButton btnNext = new JButton("Next Step");
        btnPrev.addActionListener(e -> cardLayout.previous(cards));
        btnNext.addActionListener(e -> cardLayout.next(cards));
        controls.add(btnPrev);
        controls.add(btnNext);

        container.add(cards, BorderLayout.CENTER);
        container.add(controls, BorderLayout.SOUTH);
        return container;
    }

    private static JPanel createBoxLayoutPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        p.add(new JLabel("Vertical Box Layout Arrangement:"));
        p.add(Box.createRigidArea(new Dimension(0, 10)));
        p.add(new JButton("Primary Action Button"));
        p.add(Box.createRigidArea(new Dimension(0, 10)));
        p.add(new JButton("Secondary Action Button"));
        p.add(Box.createRigidArea(new Dimension(0, 10)));
        p.add(new JButton("Settings & Preferences"));
        p.add(Box.createVerticalGlue()); // Absorbs excess vertical space
        p.add(new JLabel("Footer pinned to bottom via Box.createVerticalGlue()"));

        return p;
    }
}
