package awt_and_swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * <h1>Swing Components Demo</h1>
 * <p>
 * Demonstrates the full range of core Swing GUI components in Java:
 * <ul>
 *   <li>JFrame, JPanel, JTabbedPane, JScrollPane</li>
 *   <li>JButton, JLabel, JTextField, JPasswordField, JTextArea</li>
 *   <li>JCheckBox, JRadioButton, ButtonGroup</li>
 *   <li>JComboBox, JList, JSlider, JProgressBar</li>
 *   <li>JTable (DefaultTableModel), JTree (DefaultMutableTreeNode)</li>
 * </ul>
 * </p>
 *
 * @author AdvanceJava Architecture
 * @version 1.0
 */
public class SwingComponentsDemo {

    public static void main(String[] args) {
        // Run GUI construction on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Advanced Java - Swing Components Master Showcase");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        frame.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        // 1. Basic Controls Tab
        tabbedPane.addTab("Basic Controls", createBasicControlsPanel());

        // 2. Selectors & Ranges Tab
        tabbedPane.addTab("Selectors & Ranges", createSelectorsPanel());

        // 3. Tables & Trees Tab
        tabbedPane.addTab("Tables & Trees", createTableAndTreePanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    /**
     * Panel demonstrating Labels, TextFields, PasswordField, TextArea, Buttons
     */
    private static JPanel createBasicControlsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel header = new JLabel("Basic Input & Control Components", JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panel.add(header, gbc);

        // Username
        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        JTextField txtUsername = new JTextField(20);
        panel.add(txtUsername, gbc);

        // Password
        gbc.gridy = 2; gbc.gridx = 0;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        JPasswordField txtPassword = new JPasswordField(20);
        panel.add(txtPassword, gbc);

        // Comments / Bio (TextArea in ScrollPane)
        gbc.gridy = 3; gbc.gridx = 0;
        panel.add(new JLabel("Bio / Notes:"), gbc);
        gbc.gridx = 1;
        JTextArea txtBio = new JTextArea(4, 20);
        txtBio.setLineWrap(true);
        JScrollPane bioScroll = new JScrollPane(txtBio);
        panel.add(bioScroll, gbc);

        // Submit Button & Feedback Label
        gbc.gridy = 4; gbc.gridx = 0; gbc.gridwidth = 2;
        JButton btnSubmit = new JButton("Submit Details");
        btnSubmit.setBackground(new Color(41, 128, 185));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFocusPainted(false);
        panel.add(btnSubmit, gbc);

        gbc.gridy = 5;
        JLabel lblStatus = new JLabel("Fill the form and click Submit.", JLabel.CENTER);
        lblStatus.setForeground(new Color(100, 100, 100));
        panel.add(lblStatus, gbc);

        btnSubmit.addActionListener(e -> {
            String user = txtUsername.getText().trim();
            String pass = new String(txtPassword.getPassword());
            if (user.isEmpty() || pass.isEmpty()) {
                lblStatus.setText("⚠️ Please provide both Username and Password.");
                lblStatus.setForeground(Color.RED);
            } else {
                lblStatus.setText("✓ Welcome, " + user + "! Data accepted.");
                lblStatus.setForeground(new Color(39, 174, 96));
            }
        });

        return panel;
    }

    /**
     * Panel demonstrating Checkboxes, RadioButtons, ComboBox, Slider, ProgressBar
     */
    private static JPanel createSelectorsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 1. Checkboxes
        JPanel checkPanel = new JPanel(new GridLayout(4, 1));
        checkPanel.setBorder(BorderFactory.createTitledBorder("Multi-Select Checkboxes"));
        JCheckBox cbJava = new JCheckBox("Core Java & OOP", true);
        JCheckBox cbAdvJava = new JCheckBox("Advance Java & JDBC", true);
        JCheckBox cbSpring = new JCheckBox("Spring Boot & Microservices");
        checkPanel.add(cbJava);
        checkPanel.add(cbAdvJava);
        checkPanel.add(cbSpring);
        panel.add(checkPanel);

        // 2. Radio Buttons
        JPanel radioPanel = new JPanel(new GridLayout(4, 1));
        radioPanel.setBorder(BorderFactory.createTitledBorder("Single-Select Radio Group"));
        ButtonGroup group = new ButtonGroup();
        JRadioButton rbBeginner = new JRadioButton("Beginner Level");
        JRadioButton rbIntermediate = new JRadioButton("Intermediate Level", true);
        JRadioButton rbAdvanced = new JRadioButton("Advanced Architect Level");
        group.add(rbBeginner);
        group.add(rbIntermediate);
        group.add(rbAdvanced);
        radioPanel.add(rbBeginner);
        radioPanel.add(rbIntermediate);
        radioPanel.add(rbAdvanced);
        panel.add(radioPanel);

        // 3. ComboBox & List
        JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboPanel.setBorder(BorderFactory.createTitledBorder("Dropdown (JComboBox) & List"));
        String[] databases = {"MySQL", "PostgreSQL", "Oracle DB", "H2 In-Memory", "MongoDB"};
        JComboBox<String> dbCombo = new JComboBox<>(databases);
        comboPanel.add(new JLabel("Select DB:"));
        comboPanel.add(dbCombo);
        panel.add(comboPanel);

        // 4. Slider and Progress Bar
        JPanel sliderPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        sliderPanel.setBorder(BorderFactory.createTitledBorder("Slider & Dynamic ProgressBar"));
        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setValue(60);
        progressBar.setStringPainted(true);

        JSlider slider = new JSlider(0, 100, 60);
        slider.setMajorTickSpacing(25);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(e -> progressBar.setValue(slider.getValue()));

        sliderPanel.add(new JLabel("Adjust Progress:"));
        sliderPanel.add(slider);
        sliderPanel.add(new JLabel("Progress Level:"));
        sliderPanel.add(progressBar);
        panel.add(sliderPanel);

        return panel;
    }

    /**
     * Panel demonstrating JTable with DefaultTableModel and JTree with hierarchical nodes
     */
    private static JPanel createTableAndTreePanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // JTree
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Advance Java Curriculum");
        DefaultMutableTreeNode swingNode = new DefaultMutableTreeNode("GUI & Swing");
        swingNode.add(new DefaultMutableTreeNode("Layout Managers"));
        swingNode.add(new DefaultMutableTreeNode("Event Listeners"));
        swingNode.add(new DefaultMutableTreeNode("Custom Painting"));

        DefaultMutableTreeNode jdbcNode = new DefaultMutableTreeNode("JDBC Database");
        jdbcNode.add(new DefaultMutableTreeNode("PreparedStatement"));
        jdbcNode.add(new DefaultMutableTreeNode("Transactions"));
        jdbcNode.add(new DefaultMutableTreeNode("CallableStatement"));

        DefaultMutableTreeNode netNode = new DefaultMutableTreeNode("Networking");
        netNode.add(new DefaultMutableTreeNode("TCP Sockets"));
        netNode.add(new DefaultMutableTreeNode("UDP Datagrams"));

        root.add(swingNode);
        root.add(jdbcNode);
        root.add(netNode);

        JTree tree = new JTree(root);
        JPanel treePanel = new JPanel(new BorderLayout());
        treePanel.setBorder(BorderFactory.createTitledBorder("Course Tree (JTree)"));
        treePanel.add(new JScrollPane(tree), BorderLayout.CENTER);
        panel.add(treePanel);

        // JTable
        String[] columns = {"ID", "Name", "Department", "Performance"};
        Object[][] data = {
            {"101", "Alice Johnson", "Engineering", "95%"},
            {"102", "Bob Smith", "Data Science", "88%"},
            {"103", "Charlie Davis", "Security", "92%"},
            {"104", "Diana Prince", "Cloud Infra", "97%"}
        };
        DefaultTableModel model = new DefaultTableModel(data, columns);
        JTable table = new JTable(model);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Employee Data (JTable)"));
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(tablePanel);

        return panel;
    }
}
